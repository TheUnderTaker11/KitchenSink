package com.theundertaker11.kitchensink.tileentity;

import java.util.UUID;

import com.mojang.authlib.GameProfile;
import com.theundertaker11.kitchensink.KitchenSink;
import com.theundertaker11.kitchensink.ModUtils;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class KSTileEntityQuarryBlock extends TileEntity implements ITickable{
	public static int ticksNeeded = KitchenSink.QuarryTicksBetweenOperations;
	public static int radius = KitchenSink.QuarryRadius;
	
	public static final int SIZE = 1;
	//TODO Add the ability to replace the blocks broken with cobblestone or stone
	//private boolean replaceBlocks;
	private int Energy;
	private int x;
	private int y;
	private int z;
	//Vars below this don't need to be saved
	private int tickTimer;
	private boolean isOff;
	
	//TODO Implement a fake player to break the blocks the quarry breaks.
	//private static GameProfile KitchenSinkProfile = new GameProfile(UUID.fromString("a7d50290-b3f6-4dd9-afa1-d1de51ce3355"),"[KitchenSink]");
	//private FakePlayer KitchenSinkPlayer = new FakePlayer(this.getWorld().getMinecraftServer().worldServerForDimension(this.getWorld().provider.getDimension()),KitchenSinkProfile);
	
	public KSTileEntityQuarryBlock(){
		super();
	}
	
	private ItemStackHandler itemStackHandler = 
    		new ItemStackHandler(SIZE){
        @Override
        protected void onContentsChanged(int slot){
            KSTileEntityQuarryBlock.this.markDirty();
        }
    };
    
	@Override
	public void update()
	{ 
		if(this.getWorld().isRemote) return;
		//Makes sure the tick timer can go as high as it needs but still has a limit.
		if(this.tickTimer<(this.ticksNeeded+200))
		{
			++this.tickTimer;
		}

		World world = this.getWorld();
		IItemHandler quarry = world.getTileEntity(this.getPos()).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);

		//Pretty much all quarry mining code called here.
		if(this.tickTimer>=this.ticksNeeded&&this.Energy>0&&!this.isOff)
		{	
			tryToMineBlock(new BlockPos(x,y,z), world);
		}

		//This auto-exports items out the top of the quarry to any inventory that is there.
		if(quarry.getStackInSlot(0)!=null && ModUtils.getIItemHandlerAtPos(world, this.getPos().getX(), this.getPos().getY()+1, this.getPos().getZ(), EnumFacing.DOWN)!=null)
		{
			IItemHandler upInventory = ModUtils.getIItemHandlerAtPos(world, this.getPos().getX(), this.getPos().getY()+1, this.getPos().getZ(), EnumFacing.DOWN);
			if(quarry.getStackInSlot(0)!=null) // Hey can't hurt to check twice right? Make extra sure??
			{
				ItemStack quarrysItem = quarry.getStackInSlot(0).copy();
				for(int i=0;i<upInventory.getSlots();i++)
				{
					if(upInventory.insertItem(i, quarrysItem, false)==null)
					{
						quarry.extractItem(0, quarry.getStackInSlot(0).stackSize, false);
						break;
					}
				}
			}
			
		} 
	}
	//TODO Make it so the quarry actually gets the blocks it breaks and puts them in its inventory(Silk touch way)
	public void tryToMineBlock(BlockPos blockPos, World world)
	{
		if(world.isRemote) return;
		int localx;
		int localy;
		int localz;
		if(this.y==0)
		{
			localx=(this.getPos().getX()-this.radius);
			localy=(this.getPos().getY()-1);
			localz=(this.getPos().getZ()-this.radius);
			this.markDirty();
		}
		//The logic here is I do all Y's down then move across the X axis, then after I hit a side scoot the Z over 1 and start the 
		//x and y mining again.
		else
		{
			//Big stuff will only happen after it tried to mine the last Y yea?
				if(this.y==1)
				{
					localy=(this.getPos().getY()-1);
					//If x is at max then reset it back to the lowest value.
					if(this.x==(this.getPos().getX()+this.radius))
					{
						localx=(this.getPos().getX()-this.radius);
						//If Z is max then the quarry is done.
						if(this.z==(this.getPos().getZ()+this.radius))
						{
							//This will make it start over, sure it will keep running with no blocks, but if its air
							//it skips over it, and doing this makes it work with FunkLocomotion or other mods that move
							// Tile entities!
							localz=(this.getPos().getZ()-this.radius);
						}
						//If X is finished, add 1 to Z(given its not already at max, checked above)
						else localz = (this.z+1);
					}
					//If Y is finished, add 1 to X(given its not already at max, checked above)
					else
					{
						localx=(this.x+1);
						localz=this.z;
					}
					
				}
				else{
					localy=(this.y-1);
					localx=this.x;
					localz=this.z;
				}
				this.markDirty();
		}
		
		Block blockToMine = world.getBlockState(blockPos).getBlock();
			
		if(blockToMine!=Blocks.AIR)
		{
			if(blockToMine==Blocks.WATER)
			{
				world.setBlockToAir(blockPos);
			}
			else
			{
				this.tickTimer=0;
				this.removeEnergy(1);
				world.destroyBlock(blockPos, true);
			}
		}
		this.x = localx;
		this.y = localy;
		this.z = localz;
		this.markDirty();
	}
	
	public void addEnergy(int amount)
	{
		this.Energy = (this.Energy+amount);
		this.markDirty();
	}
	
	//public void setReplaceBlocks(boolean bool)
	//{
	//	this.replaceBlocks = bool;
	//}
	
	public int getEnergy()
	{
		System.out.println("Tile Entity Energy: "+ this.Energy);
		return this.Energy;
	}
	
	public void removeEnergy(int amount)
	{
		if(this.Energy>=amount) this.Energy = (this.Energy-amount);
		else this.Energy=0;
		this.markDirty();
	}
	
	public void setIsOff(boolean bool)
	{
		this.isOff = bool;
	}
	
	@Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
        super.writeToNBT(compound);
        System.out.println("Written to NBT");
        compound.setTag("items", itemStackHandler.serializeNBT());
        //compound.setBoolean("replaceBlocks", this.replaceBlocks);
        compound.setInteger("Energy", this.Energy);
        compound.setInteger("x", this.x);
        compound.setInteger("y", this.y);
        compound.setInteger("z", this.z);
        return compound;
        
    }
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		System.out.println("NBT Energy: "+ compound.getInteger("Energy"));
		if (compound.hasKey("items")) {
            itemStackHandler.deserializeNBT((NBTTagCompound) compound.getTag("items"));
        }
		//this.replaceBlocks = compound.getBoolean("replaceBlocks");
		this.Energy = compound.getInteger("Energy");
		this.x = compound.getInteger("x");
		this.y = compound.getInteger("y");
		this.z = compound.getInteger("z");
	}
	
	@Override
    public boolean shouldRefresh(final World world, final BlockPos pos, final IBlockState oldState, final IBlockState newState) {
        return oldState.getBlock() != newState.getBlock();
    }
	
	@Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return (T) itemStackHandler;
        }
        return super.getCapability(capability, facing);
    }

}
