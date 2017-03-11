package com.theundertaker11.kitchensink.tileentity;

import javax.annotation.Nullable;

import com.theundertaker11.kitchensink.ksblocks.StorageBlockBase;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityContainerBase extends TileEntity implements ITickable{

	public TileEntityContainerBase(){super();}

	@Override
	public void update(){}
	
	public boolean isUseableByPlayer(EntityPlayer playerIn) {
        // If we are too far away from this tile entity you cannot use it
        return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
    }
	
	@Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
        super.writeToNBT(compound);
        compound.setTag("inputitem", itemStackHandler.serializeNBT());
        compound.setTag("outputitem", itemStackHandlerOutput.serializeNBT());
        return compound;
    }
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		if (compound.hasKey("inputitem")){
            itemStackHandler.deserializeNBT((NBTTagCompound) compound.getTag("inputitem"));
        }
		if (compound.hasKey("outputitem")){
            itemStackHandlerOutput.deserializeNBT((NBTTagCompound) compound.getTag("outputitem"));
        }
	}
	private ItemStackHandler itemStackHandler = 
    		new ItemStackHandler(1){
        @Override
        protected void onContentsChanged(int slot){
            markDirty();
        }
    };
    private ItemStackHandler itemStackHandlerOutput = 
    		new ItemStackHandler(1){
        @Override
        protected void onContentsChanged(int slot){
            markDirty();
        }
    };
    
	@Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY&&this.getWorld().getBlockState(this.pos).getBlock() instanceof StorageBlockBase) {
        	EnumFacing rightSide = this.getWorld().getBlockState(this.pos).getValue(StorageBlockBase.FACING).rotateAround(Axis.Y).getOpposite();
            if(facing==EnumFacing.DOWN||facing==rightSide) return (T)itemStackHandlerOutput;
            else return (T) itemStackHandler;
        }
        return super.getCapability(capability, facing);
    }
    
    @Override
    @Nullable
    public SPacketUpdateTileEntity getUpdatePacket()
    {
      NBTTagCompound updateTagDescribingTileEntityState = getUpdateTag();
      final int METADATA = 0;
      return new SPacketUpdateTileEntity(this.pos, METADATA, updateTagDescribingTileEntityState);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
      NBTTagCompound updateTagDescribingTileEntityState = pkt.getNbtCompound();
      handleUpdateTag(updateTagDescribingTileEntityState);
    }

    /* Creates a tag containing the TileEntity information, used by vanilla to transmit from server to client
       Warning - although our getUpdatePacket() uses this method, vanilla also calls it directly, so don't remove it.
     */
    @Override
    public NBTTagCompound getUpdateTag()
    {
  		NBTTagCompound nbtTagCompound = new NBTTagCompound();
  		writeToNBT(nbtTagCompound);
      return nbtTagCompound;
    }

    /* Populates this TileEntity with information from the tag, used by vanilla to transmit from server to client
     Warning - although our onDataPacket() uses this method, vanilla also calls it directly, so don't remove it.
   */
    @Override
    public void handleUpdateTag(NBTTagCompound tag)
    {
      this.readFromNBT(tag);
    }
    
    @Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate)
	{
	    return (oldState.getBlock() != newSate.getBlock());
	}
}
