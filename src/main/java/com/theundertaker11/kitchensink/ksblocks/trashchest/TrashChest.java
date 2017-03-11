package com.theundertaker11.kitchensink.ksblocks.trashchest;

import java.util.List;

import javax.annotation.Nullable;

import com.theundertaker11.kitchensink.KitchenSink;
import com.theundertaker11.kitchensink.Refernce;
import com.theundertaker11.kitchensink.ksblocks.StorageBlockBase;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TrashChest extends StorageBlockBase{
	public static final int GUI_ID = 1;
	
	private final static AxisAlignedBB hitbox = new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 1.0D, 0.875D);
	public TrashChest(String name) {
		super(name);
		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
    {
		return new KSTileEntityTrashChest();
    }
	@Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side,
                float hitX, float hitY, float hitZ) {
        // Only execute on the server
        if (world.isRemote) {
            return true;
        }
        TileEntity te = world.getTileEntity(pos);
        if (!(te instanceof KSTileEntityTrashChest)) {
            return false;
        }
        player.openGui(KitchenSink.instance, GUI_ID, world, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }
	@Override
	public boolean isFullCube(IBlockState state)
    {
        return false;
    }
	@Override
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return hitbox;
    }
}
