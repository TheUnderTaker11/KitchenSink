package com.theundertaker11.kitchensink.ksblocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.theundertaker11.kitchensink.tileentity.KSTileEntityHealingBlock;
import com.theundertaker11.kitchensink.tileentity.KSTileEntityQuarryBlock;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class QuarryBlock extends BlockBase {

	public QuarryBlock(String name) {
		super(name);
		this.isBlockContainer=true;
	}

	@Override
	public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }
	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
    {
		return new KSTileEntityQuarryBlock();
    }
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		TileEntity tEntity = worldIn.getTileEntity(pos);
		
		if(!worldIn.isRemote&&tEntity!=null&&tEntity instanceof KSTileEntityQuarryBlock&&hand==EnumHand.MAIN_HAND)
		{
			KSTileEntityQuarryBlock quarry  = (KSTileEntityQuarryBlock)tEntity;
			if(heldItem!=null)
			{
				if(heldItem.getItem()==Items.COAL||Block.getBlockFromItem(heldItem.getItem())==Blocks.COAL_BLOCK)
				{
				int num = heldItem.stackSize;
				int energyMultiple = 0;
				if(heldItem.getItem()==Items.COAL)
				{
					energyMultiple = 8;
				}
				if(heldItem.getItem() instanceof ItemBlock&&Block.getBlockFromItem(heldItem.getItem())==Blocks.COAL_BLOCK)
				{
					energyMultiple = 72;
				}
				quarry.addEnergy(num*energyMultiple);
				playerIn.addChatMessage(new TextComponentString("Added "+(num*energyMultiple)+" SE"));
				heldItem.stackSize -= num;
				quarry.markDirty();
				}
			}
			else
			{
				if(quarry.getEnergy()==0)
				{
					int diam = ((KSTileEntityQuarryBlock.radius*2)+1);
					playerIn.addChatMessage(new TextComponentString("Right click with coal/charcoal or pipe items in to charge it(Blocks also work), be warned, it will use the whole stack you are holding."));
					playerIn.addChatMessage(new TextComponentString("The Quarry will mine a square with a diameter of "+diam+" with the quarry at the center."));
				}
			}
			playerIn.addChatMessage(new TextComponentString("Charge is "+quarry.getEnergy()+" SE"));
			if(playerIn.isSneaking())
			{
				quarry.setReplaceBlocks(!quarry.getReplaceBlocks());
			}
			if(!quarry.getReplaceBlocks())
			{
				playerIn.addChatMessage(new TextComponentString("Block Replacement is Disabled, shift right click to enable."));
			}
			else{
				playerIn.addChatMessage(new TextComponentString("Block Replacement is Enabled, shift right click to disable."));
			}
		}
		
        return true;
    }
	
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn)
    {

		TileEntity tEntity = worldIn.getTileEntity(pos);
		if (!worldIn.isRemote&&tEntity!=null&&tEntity instanceof KSTileEntityQuarryBlock)
        {
			KSTileEntityQuarryBlock quarry  = (KSTileEntityQuarryBlock)tEntity;
            if (worldIn.isBlockPowered(pos))
            {
            	quarry.setIsOff(true);
            }
            else{
            	if (!worldIn.isBlockPowered(pos))
                {
                    quarry.setIsOff(false);
                }
            }
        }
    }
}
