package com.theundertaker11.kitchensink.ksblocks;

import com.theundertaker11.kitchensink.KitchenSink;
import com.theundertaker11.kitchensink.tileentity.KSTileEntityStorageBlock;

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

public class StorageBlockBase extends BlockContainer {
	
	public StorageBlockBase(Material materialIn, String name) 
	{
		super(materialIn);
		this.setRegistryName(name);
		this.setUnlocalizedName("CompChest");
		this.setCreativeTab(KitchenSink.KStab);
		this.setHardness(2.0F);
		this.setResistance(200.0F);
		this.setHarvestLevel("axe", 1);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) 
	{
		return new KSTileEntityStorageBlock();
	}

	

}
