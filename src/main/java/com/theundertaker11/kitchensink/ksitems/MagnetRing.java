package com.theundertaker11.kitchensink.ksitems;

import java.util.Iterator;
import java.util.List;

import com.theundertaker11.kitchensink.KitchenSink;
import com.theundertaker11.kitchensink.ModUtils;
import com.theundertaker11.kitchensink.entity.IndestructibleEntityItem;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;

public class MagnetRing extends ItemBase implements IBauble{
	protected double distanceFromPlayer = 0;
	protected int teir;
	public MagnetRing(String name)
	{
		super(name);
		this.setMaxDamage(0);
		this.setMaxStackSize(1);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
		if(stack.getTagCompound()!=null)
		{
			if(stack.getTagCompound().getInteger("teir")==6) tooltip.add("Blessed Magnet");
			else tooltip.add("Item Magnet T"+(stack.getTagCompound().getInteger("teir")));
			tooltip.add("While sneaking it will not pull in items");
		}
		else 
			{
				tooltip.add("Craft with an Item Magnet teir to activate.");
				tooltip.add("While sneaking it will not pull in items.");
			}
    }

	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		return BaubleType.RING;
	}
	
	@Override
	public void onWornTick(ItemStack item, EntityLivingBase Player) {
		if(item.getTagCompound()!=null)
			{
			this.distanceFromPlayer = (item.getTagCompound().getDouble("range"));
			}
		else return;
		if (!(Player instanceof EntityPlayer))
			return;
		EntityPlayer player = (EntityPlayer)Player;
		World world = player.getEntityWorld();
		if (player.isSneaking())
			return;
		if (world.isRemote)
			return;
		// items
		Iterator iterator = ModUtils.getEntitiesInRange(EntityItem.class, world, player.posX, player.posY,
				player.posZ, this.distanceFromPlayer).iterator();
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
		iterator = ModUtils.getEntitiesInRange(EntityXPOrb.class, world, player.posX, player.posY, player.posZ,
				this.distanceFromPlayer).iterator();
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
	
	//Tinkers Code
		@Override
		  public boolean hasCustomEntity(ItemStack stack) {
		    return true;
		  }

		  @Override
		  public Entity createEntity(World world, Entity location, ItemStack itemstack) {
		    EntityItem entity = new IndestructibleEntityItem(world, location.posX, location.posY, location.posZ, itemstack);
		    if(location instanceof EntityItem) {
		      // workaround for private access on that field >_>
		      NBTTagCompound tag = new NBTTagCompound();
		      location.writeToNBT(tag);
		      entity.setPickupDelay(tag.getShort("PickupDelay"));
		    }
		    entity.motionX = location.motionX;
		    entity.motionY = location.motionY;
		    entity.motionZ = location.motionZ;
		    return entity;
		  }

}
