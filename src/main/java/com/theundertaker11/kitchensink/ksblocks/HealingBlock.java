package com.theundertaker11.kitchensink.ksblocks;

import com.theundertaker11.kitchensink.KitchenSink;
import com.theundertaker11.kitchensink.tileentity.KSTileEntityHealingBlock;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HealingBlock extends BlockBase{
	protected String Name;
	
	protected HealingBlock(String name) {
		super(name);
		this.isBlockContainer = true;
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }
	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
    {
		return new KSTileEntityHealingBlock();
    }
}
