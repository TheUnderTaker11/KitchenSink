package com.theundertaker11.kitchensink.ksblocks;

import com.theundertaker11.kitchensink.tileentity.KSTileEntityTrashChest;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TrashChest extends StorageBlockBase{

	public TrashChest(String name) {
		super(name);
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
    {
		return new KSTileEntityTrashChest();
    }
}
