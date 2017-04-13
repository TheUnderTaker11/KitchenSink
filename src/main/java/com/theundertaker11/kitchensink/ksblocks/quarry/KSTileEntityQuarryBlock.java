package com.theundertaker11.kitchensink.ksblocks.quarry;

import java.util.UUID;

import com.mojang.authlib.GameProfile;
import com.theundertaker11.kitchensink.KitchenSink;
import com.theundertaker11.kitchensink.util.ModUtils;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class KSTileEntityQuarryBlock extends TileEntity implements ITickable{
	public static int ticksNeeded = KitchenSink.QuarryTicksBetweenOperations;
	public static int radius = KitchenSink.QuarryRadius;
	
	private final String FAKE_PLAYER_NAME = "[KitchenSink]";
	private final UUID FAKE_PLAYER_ID = null;
	
	public static final int SIZE = 1;
	private boolean replaceBlocks;
	private int Energy;
	private int currentX;
	private int currentY;
	private int currentZ;
	private boolean isOff;
	//Vars below this don't need to be saved
	private int tickTimer;
	
	
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
			tryToMineBlock(new BlockPos(currentX,currentY,currentZ), world);
		}
		
		//This Auto turns any coal in the inventory into energy.
		if(quarry.getStackInSlot(0)!=null)
		{
			ItemStack item = quarry.getStackInSlot(0);
			if(item.getItem()==Items.COAL)
			{
				int num = item.stackSize;
				this.addEnergy(num*8);
				quarry.extractItem(0, num, false);
			}
			if(item.getItem() instanceof ItemBlock&&Block.getBlockFromItem(item.getItem())==Blocks.COAL_BLOCK)
			{
				int num = item.stackSize;
				this.addEnergy(num*72);
				quarry.extractItem(0, num, false);
			}
		}
		//This auto-exports items out the top of the quarry to any inventory that is there.
		if(quarry.getStackInSlot(0)!=null && ModUtils.getIItemHandlerAtPos(world, this.getPos().getX(), this.getPos().getY()+1, this.getPos().getZ(), EnumFacing.DOWN)!=null)
		{
			IItemHandler upInventory = ModUtils.getIItemHandlerAtPos(world, this.getPos().getX(), this.getPos().getY()+1, this.getPos().getZ(), EnumFacing.DOWN);
			if(quarry.getStackInSlot(0)!=null&&quarry.getStackInSlot(0).getItem()!=null) // Hey can't hurt to check twice right? Make extra sure??
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
	/**
	 * This will attempt to mine a block given the x,y,z. If there is no room in the quarry's inventory
	 * it won't change the currentX,Y, and Z ints, this makes it so I don't have to worry about calling this as many times as I like.
	 * @param blockPos BlockPos of block you want mined
	 * @param world 
	 */
	public void tryToMineBlock(BlockPos blockPos, World world)
	{
		if(world.isRemote) return;
		IItemHandler quarry = world.getTileEntity(this.getPos()).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
		
		EntityPlayerMP fakeplayer = FakePlayerFactory.get((WorldServer) worldObj, new GameProfile(FAKE_PLAYER_ID, FAKE_PLAYER_NAME));
		
		int localx;
		int localy;
		int localz;
		if(quarry.getStackInSlot(0)!=null) return;
		
		if(this.currentY==0)
		{
			this.currentX=(this.getPos().getX()-this.radius);
			this.currentY=(this.getPos().getY()-1);
			this.currentZ=(this.getPos().getZ()-this.radius);
			this.markDirty();
			return;
		}
		//The logic here is I do all Y's down then move across the X axis, then after I hit a side scoot the Z over 1 and start the 
		//x and y mining again.
		else
		{
			//Big stuff will only happen after it tried to mine the last Y yea?
				if(this.currentY==1)
				{
					localy=(this.getPos().getY()-1);
					//If x is at max then reset it back to the lowest value.
					if(this.currentX==(this.getPos().getX()+this.radius))
					{
						localx=(this.getPos().getX()-this.radius);
						//If Z is max then the quarry is done.
						if(this.currentZ==(this.getPos().getZ()+this.radius))
						{
							//This will make it start over, sure it will keep running with no blocks, but if its air
							//it skips over it, and doing this makes it work with FunkyLocomotion or other mods that move
							// Tile entities
							localz=(this.getPos().getZ()-this.radius);
						}
						//If X is finished, add 1 to Z(given its not already at max, checked above)
						else localz = (this.currentZ+1);
					}
					//If Y is finished, add 1 to X(given its not already at max, checked above)
					else
					{
						localx=(this.currentX+1);
						localz=this.currentZ;
					}
					
				}
				else{
					localy=(this.currentY-1);
					localx=this.currentX;
					localz=this.currentZ;
				}
				this.markDirty();
		}
		IBlockState stateToMine = world.getBlockState(blockPos);
		Block blockToMine = stateToMine.getBlock();
		if(blockToMine!=Blocks.AIR)
		{
			BreakEvent event = new BreakEvent(this.getWorld(), blockPos, stateToMine, fakeplayer);
			MinecraftForge.EVENT_BUS.post(event);
			if(event.getResult() == Result.ALLOW)
			{
				if(blockToMine==Blocks.WATER||blockToMine==Blocks.LAVA||blockToMine==Blocks.FLOWING_LAVA||blockToMine==Blocks.FLOWING_WATER)
				{
					if(this.replaceBlocks) world.setBlockState(blockPos, Blocks.STONE.getDefaultState(), 3);
					else world.setBlockToAir(blockPos);
				}
				else
				{
					this.tickTimer=0;
					this.removeEnergy(1);
				
					if(blockToMine.getItem(this.getWorld(), blockPos, stateToMine)!=null&&quarry.getStackInSlot(0)==null//&&blockToMine!=Blocks.BEDROCK
						&&stateToMine.getBlock().getHarvestLevel(stateToMine)>-1)
					{
						if(this.replaceBlocks) 
							world.setBlockState(blockPos, Blocks.STONE.getDefaultState(), 3);
						else 
							world.destroyBlock(blockPos, false);
						
						quarry.insertItem(0, blockToMine.getItem(this.getWorld(), blockPos, stateToMine), false);
					}
					else return;
				}
			}
			else return;
		}
		this.currentX = localx;
		this.currentY = localy;
		this.currentZ = localz;
		this.markDirty();
	}
	
	public void addEnergy(int amount)
	{
		this.Energy = (this.Energy+amount);
		this.markDirty();
	}
	
	public void setReplaceBlocks(boolean bool)
	{
		this.replaceBlocks = bool;
	}
	
	public boolean getReplaceBlocks()
	{
		return this.replaceBlocks;
	}
	
	public int getEnergy()
	{
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
	
	public boolean getIsOff()
	{
		return this.isOff;
	}
	
	@Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
        super.writeToNBT(compound);
        compound.setTag("items", itemStackHandler.serializeNBT());
        compound.setBoolean("replaceBlocks", this.replaceBlocks);
        compound.setInteger("Energy", this.Energy);
        compound.setInteger("currentX", this.currentX);
        compound.setInteger("currentY", this.currentY);
        compound.setInteger("currentZ", this.currentZ);
        compound.setBoolean("isOff", this.isOff);
        return compound;
        
    }
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		if (compound.hasKey("items")) {
            itemStackHandler.deserializeNBT((NBTTagCompound) compound.getTag("items"));
        }
		this.replaceBlocks = compound.getBoolean("replaceBlocks");
		this.Energy = compound.getInteger("Energy");
		this.currentX = compound.getInteger("currentX");
		this.currentY = compound.getInteger("currentY");
		this.currentZ = compound.getInteger("currentZ");
		this.isOff = compound.getBoolean("isOff");
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
