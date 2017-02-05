package com.theundertaker11.kitchensink.proxy;

import com.theundertaker11.kitchensink.ksblocks.trashchest.KSTileEntityTrashChest;
import com.theundertaker11.kitchensink.ksblocks.trashchest.TrashChestContainer;
import com.theundertaker11.kitchensink.ksblocks.trashchest.TrashChestGui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiProxy implements IGuiHandler{

	@Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof KSTileEntityTrashChest) {
            return new TrashChestContainer(player.inventory, (KSTileEntityTrashChest) te);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof KSTileEntityTrashChest) {
        	KSTileEntityTrashChest containerTileEntity = (KSTileEntityTrashChest) te;
            return new TrashChestGui(containerTileEntity, new TrashChestContainer(player.inventory, containerTileEntity));
        }
        return null;
    }

}
