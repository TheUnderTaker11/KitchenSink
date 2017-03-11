package com.theundertaker11.kitchensink.ksblocks.blessedfurnace;

import java.util.ArrayList;
import java.util.List;

import com.theundertaker11.kitchensink.tileentity.TileEntityContainerBase;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class KSTileEntityBlessedFurnace extends TileEntityContainerBase{
	
	private int ticksCooking;
	private static final int TICKS_NEEDED = 40;
	public boolean isBurning;
	public KSTileEntityBlessedFurnace(){super();}

	@Override
	public void update()
	{
		if (this.worldObj.getTileEntity(this.pos)!=null&&canSmelt()) 
		{
			this.isBurning=true;
			this.ticksCooking++;
			int lavacount = getLavaCount(this.getPos());
			if(ticksCooking>=((double)TICKS_NEEDED/(Math.pow(2, lavacount)-1)))
			{
				ticksCooking=0;
				smeltItem();
			}
		}
		else{
			ticksCooking=0;
			this.isBurning=false;
		}
	}
	
	public int getLavaCount(BlockPos pos)
	{
		int count = 0;
		List<IBlockState> set = new ArrayList<IBlockState>();
		set.add(this.getWorld().getBlockState(new BlockPos(this.getPos().getX(),this.getPos().getY()+1,this.getPos().getZ())));
		set.add(this.getWorld().getBlockState(new BlockPos(this.getPos().getX(),this.getPos().getY()-1,this.getPos().getZ())));
		set.add(this.getWorld().getBlockState(new BlockPos(this.getPos().getX()+1,this.getPos().getY(),this.getPos().getZ())));
		set.add(this.getWorld().getBlockState(new BlockPos(this.getPos().getX()-1,this.getPos().getY(),this.getPos().getZ())));
		set.add(this.getWorld().getBlockState(new BlockPos(this.getPos().getX(),this.getPos().getY(),this.getPos().getZ()+1)));
		set.add(this.getWorld().getBlockState(new BlockPos(this.getPos().getX(),this.getPos().getY(),this.getPos().getZ()-1)));
		for(IBlockState state:set)
		{
			if(state!=null&&state.getBlock()==Blocks.LAVA||state.getBlock()==Blocks.FLOWING_LAVA) count++;
		}
		return count;
	}
	public static ItemStack getSmeltingResultForItem(ItemStack stack)
	{
		return FurnaceRecipes.instance().getSmeltingResult(stack);
	}
	
	private boolean canSmelt() {return smeltItem(false);}

	private void smeltItem() {smeltItem(true);}
	
	private boolean smeltItem(boolean performSmelt)
	{
		IItemHandler inventory = this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		IItemHandler inventoryoutput = this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);

		if(getLavaCount(this.getPos())>0)
		{
			if (inventory != null&&inventory.getStackInSlot(0)!=null) 
			{
				ItemStack inputSlotStack = inventory.getStackInSlot(0);
				ItemStack result = getSmeltingResultForItem(inputSlotStack).copy();
				if (result != null)
				{
					ItemStack outputSlotStack = inventoryoutput.getStackInSlot(0);
					if (outputSlotStack == null)
					{
						if(inventoryoutput.insertItem(0, result, !performSmelt)==null)
						{
							inventory.extractItem(0, 1, !performSmelt);
							markDirty();
							return true;
						}
					}else{
						if(inventoryoutput.insertItem(0, result, true)!=null)
						{
							return false;
						}
						else
						{
							inventoryoutput.insertItem(0, result, !performSmelt);
							inventory.extractItem(0, 1, !performSmelt);
							markDirty();
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public double percComplete()
	{
		int lavacount = getLavaCount(this.getPos());
		return (double)this.ticksCooking/((double)TICKS_NEEDED/(Math.pow(2, lavacount)-1));
	}
	
	private static final byte TICKS_COOKING_FIELD_ID = 0;
	
	private static final byte NUMBER_OF_FIELDS = 1;

	public int getField(int id) {
		if (id == TICKS_COOKING_FIELD_ID) return ticksCooking;
		System.err.println("Invalid field ID in GRTileEntity.getField:" + id);
		return 0;
	}

	public void setField(int id, int value)
	{
		if (id == TICKS_COOKING_FIELD_ID) {
			ticksCooking = (short)value;
		}
	}
	public int getFieldCount(){
		return NUMBER_OF_FIELDS;
	}
}
