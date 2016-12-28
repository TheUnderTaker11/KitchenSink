package com.theundertaker11.kitchensink;


import java.util.List;

import com.theundertaker11.kitchensink.ksitems.Itemsss;

import baubles.api.cap.BaublesCapabilities;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public final class ModUtils
{
	/*//This will loops through all blocks in a range(Cube)
	 * 
	 * for(int x = loc.getX() - radius; x < loc.getx + radius; x++ )
		{
  			for(int y = loc.getY() - radius; y < loc.getY + radius; y++ )
  			{
      			for(int z = loc.getZ() - radius; z < loc.getZ + radius; z++ )
      			{
            //Block b = get block at (x,y,z)
      			}
  			}
		}
	 */
	public static int moveSpeed = 1;
	public static int moveSlowness = 2;
	public static int digSpeed = 3;
	public static int miningSlowDown = 4;
	public static int strength = 5;
	public static int jumpBoost = 8;
	public static int nausea = 9;
	public static int regeneration = 10;
	public static int resistance = 11;
	public static int fireResistance = 12;
	public static int waterBreathing = 13;
	public static int invisibility = 14;
	public static int blindness = 15;
	public static int nightVision = 16;
	public static int hunger = 17;
	public static int weakness = 18;
	public static int poison = 19;
	public static int wither = 20;
	//Start things by Jotato
	public static List<Entity> getEntitiesInRange(Class<? extends Entity> entityType, World world, double x, double y, double z, double radius) {
		return getEntitesInTange(entityType, world, x - radius, y - radius, z - radius, x + radius, y + radius,
				z + radius);
	}

	public static List<Entity> getEntitesInTange(Class<? extends Entity> entityType, World world, double x, double y, double z, double x2,
			double y2, double z2) {
		return world.getEntitiesWithinAABB(entityType, new AxisAlignedBB(x, y, z, x2, y2, z2));
	}
	
	public static EnumFacing getFacingFromEntity(BlockPos clickedBlock, EntityLivingBase entity) {
        return EnumFacing.getFacingFromVector(
             (float) (entity.posX - clickedBlock.getX()),
             (float) (entity.posY - clickedBlock.getY()),
             (float) (entity.posZ - clickedBlock.getZ()));
    }
	//End things by Jotato
	/**
	 * Gets the Players X, Y, and Z and writes them as doubles to the key's x, y, and z in NBT,
	 * Checks if the TagCompound is null so you don't have to worry about that.
	 * --ALSO SETS THE DIMENSION ID UNDER THE "dim" TAG--
	 * @param itemStackIn
	 * @param playerIn
	 */
	public static void WritePlayerXYZtoNBT(ItemStack itemStackIn, EntityPlayer playerIn)
	{
		if(itemStackIn.getTagCompound()==null)	
		{
			itemStackIn.setTagCompound(new NBTTagCompound());
			itemStackIn.getTagCompound().setDouble("x", playerIn.posX);
			itemStackIn.getTagCompound().setDouble("y", playerIn.posY);
			itemStackIn.getTagCompound().setDouble("z", playerIn.posZ);
			itemStackIn.getTagCompound().setInteger("dim", playerIn.dimension);
			playerIn.playSound(SoundEvents.BLOCK_ANVIL_LAND, 3.0F, 0.1F);
		}
		else if(itemStackIn.getTagCompound()!=null)
		{
			itemStackIn.getTagCompound().setDouble("x", playerIn.posX);
			itemStackIn.getTagCompound().setDouble("y", playerIn.posY);
			itemStackIn.getTagCompound().setDouble("z", playerIn.posZ);
			itemStackIn.getTagCompound().setInteger("dim", playerIn.dimension);
			playerIn.playSound(SoundEvents.BLOCK_ANVIL_LAND, 3.0F, 0.1F);
		}
		
	}
	
	/**
	 * Teleports a player, given the x, y, z, and dimension ID.
	 * Works cross-dimensionally(Hence needing dimension ID)
	 * @param player
	 * @param x
	 * @param y
	 * @param z
	 * @param dimension
	 */
	public static void TeleportPlayer(EntityPlayer player, double x, double y, double z, int dimension)
	{
		if(player.dimension==dimension)
		{
			player.setPositionAndUpdate(x, y, z);
		}
		else
		{
			player.changeDimension(dimension);
			player.setPositionAndUpdate(x, y, z);	
		}
	}
	
	/**
	 * Test if an ItemStack in in a persons bauble slots
	 * @param item ItemStack of whatever item you are testing for, if it has no special data just make a new ItemStack for this
	 */
	public static boolean baublesHasItemStack(EntityPlayer player, ItemStack item)
	{
		IBaublesItemHandler baubles = player.getCapability(BaublesCapabilities.CAPABILITY_BAUBLES, player.getHorizontalFacing());
		for(int i=0;i<baubles.getSlots();++i)
		 {
			 if(baubles.getStackInSlot(i)!=null)
		 		{
		 			ItemStack stack = baubles.getStackInSlot(i);
		 			if(stack.isItemEqual(item))
		 			{
		 				return true;
		 			}
		 		}
		 }
		return false;
	}
	/**
	 * Gets an IInventory at the given position
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public static IInventory getIInventoryAtPos(World world, int x, int y, int z)
	{
		IInventory inventory = null;
		BlockPos blockPos = new BlockPos(x,y,z);
		
		if(world.getTileEntity(blockPos)!=null)
		{
			TileEntity tileentity = world.getTileEntity(blockPos);
			 if (tileentity instanceof IInventory)
	            {
	                inventory = (IInventory)tileentity;
	            }
		}
		return inventory;
	}
	/**
	 * Gets the IItemHandler at a given position, which is a capability.
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param facing EnumFacing side.
	 * @return
	 */
	public static IItemHandler getIItemHandlerAtPos(World world, int x, int y, int z, EnumFacing facing)
	{
		IItemHandler inventory = null;
		BlockPos blockPos = new BlockPos(x,y,z);
		
		if(world.getTileEntity(blockPos)!=null)
		{
			TileEntity tileentity = world.getTileEntity(blockPos);
			if(tileentity.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing))
			{
				inventory = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing);
			}
		}
		return inventory;
	}
}
