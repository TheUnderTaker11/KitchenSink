package com.theundertaker11.kitchensink.ksblocks;

import com.theundertaker11.kitchensink.KitchenSink;
import com.theundertaker11.kitchensink.tileentity.KSTileEntityHealingBlock;
import com.theundertaker11.kitchensink.tileentity.KSTileEntityQuarryBlock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;

public class StorageBlockBase extends BlockBase {
	
	public StorageBlockBase(String name, Material material, float hardness, float resistance) 
	{
		super(name, material, hardness, resistance);
		this.isBlockContainer=true;
	}
	public StorageBlockBase(String name)
	{
		this(name, Material.IRON, 0.5f, 0.5f);
	}

	@Override
	public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }
	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
    {
		//This should never actually be called since it should be overridden in every actual block code.
		return new KSTileEntityHealingBlock();
    }

	

}
