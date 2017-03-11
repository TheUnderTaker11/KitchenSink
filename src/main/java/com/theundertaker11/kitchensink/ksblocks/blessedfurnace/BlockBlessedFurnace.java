package com.theundertaker11.kitchensink.ksblocks.blessedfurnace;

import javax.annotation.Nullable;

import com.theundertaker11.kitchensink.KitchenSink;
import com.theundertaker11.kitchensink.gui.GuiHelper;
import com.theundertaker11.kitchensink.ksblocks.KSBlocks;
import com.theundertaker11.kitchensink.ksblocks.StorageBlockBase;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class BlockBlessedFurnace extends StorageBlockBase{

	
	public BlockBlessedFurnace(String name){
		super(name);
	}
	public static final PropertyBool ACTIVE= PropertyBool.create("ks_active");
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
    {
		return new KSTileEntityBlessedFurnace();
    }

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(worldIn.isRemote) return true;
		TileEntity tEntity = worldIn.getTileEntity(pos);
		
		if(tEntity!=null&&tEntity instanceof KSTileEntityBlessedFurnace&&hand==EnumHand.MAIN_HAND)
		{
			playerIn.openGui(KitchenSink.instance, GuiHelper.BlessedFurnaceGui, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        TileEntity tile = worldIn.getTileEntity(pos);
        if (tile instanceof IInventory) {
			InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory)tile);
		}
        /*
        //All the null checks are just in case and probably not actually needed
        if (tile!=null&&tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP)!=null&&tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN)!=null
        	&&tile instanceof KSTileEntityBlessedFurnace)
        {
        	KSTileEntityBlessedFurnace tileentity = (KSTileEntityBlessedFurnace)tile;
        	IItemHandler input = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
        	IItemHandler output = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);
        	if(input.getStackInSlot(0)!=null)
        	{
        		ItemStack inputstack = input.getStackInSlot(0);
        		EntityItem entityinput = new EntityItem(tileentity.getWorld(), tileentity.getPos().getX(), tileentity.getPos().getY(), tileentity.getPos().getZ(), inputstack);
        		tileentity.getWorld().spawnEntityInWorld(entityinput);
        	}
        	if(output.getStackInSlot(0)!=null)
        	{
        		ItemStack outputstack = output.getStackInSlot(0);
        		EntityItem entityoutput = new EntityItem(tileentity.getWorld(), tileentity.getPos().getX(), tileentity.getPos().getY(), tileentity.getPos().getZ(), outputstack);
        		tileentity.getWorld().spawnEntityInWorld(entityoutput);
        	}
        }*/
        super.breakBlock(worldIn, pos, state);
    }
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
	{
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if (worldIn.getBlockState(pos)==KSBlocks.BlessedFurnace&&tileEntity instanceof KSTileEntityBlessedFurnace) {
			KSTileEntityBlessedFurnace tile = (KSTileEntityBlessedFurnace)tileEntity;
			boolean isburning = tile.isBurning;
			return getDefaultState().withProperty(ACTIVE, isburning);
		}
		return state;
	}
	
	@Override
	protected BlockStateContainer createBlockState()
    {
		return new BlockStateContainer(this, new IProperty[]{FACING,ACTIVE});
    }
}
