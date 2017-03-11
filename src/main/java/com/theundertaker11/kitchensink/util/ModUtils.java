package com.theundertaker11.kitchensink.util;

import java.util.Iterator;
import java.util.List;

import com.theundertaker11.kitchensink.ksitems.Itemsss;

import baubles.api.cap.BaublesCapabilities;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public final class ModUtils
{
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
	
	public static void doMagnet(EntityPlayer player, World world, double range)
	{
		if(world.isRemote||player==null) return;
		// items
		Iterator iterator = getEntitiesInRange(EntityItem.class, world, player.posX, player.posY, player.posZ, range).iterator();
				while (iterator.hasNext()) {
					EntityItem itemToGet = (EntityItem) iterator.next();

					EntityItemPickupEvent pickupEvent = new EntityItemPickupEvent(player, itemToGet);
					MinecraftForge.EVENT_BUS.post(pickupEvent);
					ItemStack itemStackToGet = itemToGet.getEntityItem();
					int stackSize = itemStackToGet.stackSize;

					if (pickupEvent.getResult() == Result.ALLOW || stackSize <= 0
							|| player.inventory.addItemStackToInventory(itemStackToGet)) {
						player.onItemPickup(itemToGet, stackSize);
						world.playSound(player, player.getPosition(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.AMBIENT,
								0.15F, ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
					}
				}

				// xp
				iterator = getEntitiesInRange(EntityXPOrb.class, world, player.posX, player.posY, player.posZ,
						range).iterator();
				while (iterator.hasNext()) {
					EntityXPOrb xpToGet = (EntityXPOrb) iterator.next();

					if (xpToGet.isDead || xpToGet.isInvisible()) {
						continue;
					}
					player.xpCooldown = 0;
					xpToGet.delayBeforeCanPickup=0;
					xpToGet.setPosition(player.posX,player.posY,player.posZ);
					PlayerPickupXpEvent xpEvent = new PlayerPickupXpEvent(player, xpToGet);
					MinecraftForge.EVENT_BUS.post(xpEvent);
					if(xpEvent.getResult()==Result.ALLOW){
						xpToGet.onCollideWithPlayer(player);
					}
					
				}
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
			playerIn.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 0.8F, 0.5F);
			//playerIn.playSound(SoundEvents.BLOCK_ANVIL_LAND, 3.0F, 0.1F);
		}
		else if(itemStackIn.getTagCompound()!=null)
		{
			itemStackIn.getTagCompound().setDouble("x", playerIn.posX);
			itemStackIn.getTagCompound().setDouble("y", playerIn.posY);
			itemStackIn.getTagCompound().setDouble("z", playerIn.posZ);
			itemStackIn.getTagCompound().setInteger("dim", playerIn.dimension);
			playerIn.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 0.8F, 0.5F);
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
	
	public static String getRomanNum(int num)
	{
		String romanNum = "Error, report my Github!";
		if(num==1) romanNum="I";
		if(num==2) romanNum="II";
		if(num==3) romanNum="III";
		if(num==4) romanNum="IV";
		if(num==5) romanNum="V";
		return romanNum;
	}
}
