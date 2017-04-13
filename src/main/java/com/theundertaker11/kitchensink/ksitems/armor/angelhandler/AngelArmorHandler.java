package com.theundertaker11.kitchensink.ksitems.armor.angelhandler;

import java.util.ArrayList;
import java.util.List;

import com.theundertaker11.kitchensink.event.KSEventHandler;
import com.theundertaker11.kitchensink.ksitems.Itemsss;
import com.theundertaker11.kitchensink.ksitems.armor.AngelArmorRing;
import com.theundertaker11.kitchensink.ksitems.armor.KSArmor;

import baubles.api.cap.BaublesCapabilities;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

public class AngelArmorHandler {

	public static List<String> flightlist = new ArrayList<String>();
	public static List<String> generallist = new ArrayList<String>();
	public static List<String> hasarmorlist = new ArrayList<String>();
	
	@SubscribeEvent
	public void worldTick(WorldTickEvent event)
	{
		if(event.world.isRemote||event.world.provider.getDimension()!=0||KSEventHandler.Angelticktimer<35)
		{
			return;
		}
		else if(KSEventHandler.Angelticktimer>35)
		{
			KSEventHandler.Angelticktimer = 0;
			for(EntityPlayerMP player: event.world.getMinecraftServer().getPlayerList().getPlayerList())
			{
				if(!KSArmor.hasFullAngelSet(player))
				{
					if(generallist.contains(player.getName())) removeAllAngelEffects(player);
					removeArmor(player);
				}
				else
				{
					tickPlayer(player);
				}
			}
		}
	}
	/**
	 * Called by the WorldTickEvent above, ticks the player for all things related to my
	 * angel armor set.
	 * @param player
	 */
	public void tickPlayer(EntityPlayer player)
	{
		if(player.getEntityWorld().isRemote) return;
		if(!generallist.contains(player.getName())) generallist.add(player.getName());
		if(!hasarmorlist.contains(player.getName())) addArmor(player);
		ItemStack chest = null;
		for(ItemStack stack : player.inventory.armorInventory)
		{
			if(stack!=null&&stack.getTagCompound()!=null)
			{
				if(stack.getItem()==Itemsss.blessedChestplate) chest = stack;
			}
		}
		if(chest!=null){
			AngelHelmetHandler.handle(player, chest);
			AngelChestHandler.handle(player, chest);
			AngelLegHandler.handle(player, chest);
			AngelBootsHandler.handle(player, chest);
		}
	}
	/**
	 * Removes all effects the angel armor gives.
	 * @param player
	 */
	public static void removeAllAngelEffects(EntityPlayer player)
	{
		if(flightlist.contains(player.getName()))
		{
			player.capabilities.allowFlying=false;
			player.capabilities.isFlying=false;
			player.sendPlayerAbilities();
			flightlist.remove(player.getName());
		}
		final IAttributeInstance attribute = player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
		attribute.removeModifier(AngelLegHandler.MODIFIER_SPEED_ID);
	}
	/**
	 * Adds blessed armor to the player's armor slots, if all slots are not free it de-activates any rings
	 * and tells the player in chat.
	 * @param player
	 */
	public static void addArmor(EntityPlayer player)
	{
		for(ItemStack stack : player.inventory.armorInventory)
		{
			if(stack!=null&&!(stack.getItem() instanceof KSArmor))
			{
				player.addChatMessage(new TextComponentString("Not enough room in armor slots"));
				return;
			}
		}
		ItemStack ring = getActiveRing(player);
		if(ring==null) return;
		if(!hasarmorlist.contains(player.getName())) hasarmorlist.add(player.getName());
		
		ItemStack helm = new ItemStack(Itemsss.blessedHelmet);
		ItemStack chest = new ItemStack(Itemsss.blessedChestplate);
		ItemStack legs = new ItemStack(Itemsss.blessedLeggings);
		ItemStack boots = new ItemStack(Itemsss.blessedBoots);
		
		NBTTagCompound ringtag = ring.copy().getTagCompound();
		chest.setTagCompound(ringtag);
		legs.setTagCompound(ringtag);
		
		chest.getTagCompound().setBoolean("flight", ringtag.getBoolean("flight"));
		if(ringtag.hasKey("speed")) chest.getTagCompound().setInteger("speed", ringtag.getInteger("speed"));
		
		for(int i=0; i<player.inventory.getSizeInventory();i++)
		{
			ItemStack stack = player.inventory.getStackInSlot(i);
			if(i>35&&stack!=null&&!(stack.getItem() instanceof KSArmor)) player.addChatMessage(new TextComponentString("There has been an error in addArmor, report to TheUnderTaker11's Github"));
			if(i==36)
			{
				player.inventory.setInventorySlotContents(i, boots);
			}
			if(i==37)
			{
				player.inventory.setInventorySlotContents(i, legs);
			}
			if(i==38)
			{
				player.inventory.setInventorySlotContents(i, chest);
			}
			if(i==39)
			{
				player.inventory.setInventorySlotContents(i, helm);
			}
		}
	}
	
	public static ItemStack getActiveRing(EntityPlayer player)
	{
		ItemStack ring = null;
		for(int i=0; i<player.inventory.getSizeInventory();i++)
		{
			ItemStack invstack = player.inventory.getStackInSlot(i);
			if(invstack!=null&&invstack.getItem() instanceof AngelArmorRing)
			{
				ring = invstack;
				break;
			}
		}
		return ring;
	}
	
	/**
	 * Removes all blessed armor, checks all possibilities so can be called from anywhere.(Or at least it should)
	 * @param player
	 */
	public static void removeArmor(EntityPlayer player)
	{
		// De-activates all rings in the players inventory, and deletes any armor
		for(int i=0;i<player.inventory.getSizeInventory();i++)
		{
			ItemStack stack = player.inventory.getStackInSlot(i);
			if(stack!=null&&stack.getItem() instanceof AngelArmorRing&&stack.getItemDamage()==1)
			{
				stack.setItemDamage(0);
			}
			if(stack!=null&&stack.getItem() instanceof KSArmor)
			{
				player.inventory.setInventorySlotContents(i, null);
			}
		}
		try{
			baublesDeactivateRings(player);
	    }catch(NoSuchMethodError e){}
		
		//Removes any armor the player might be holding
		ItemStack mouseItem = player.inventory.getItemStack();
		if(mouseItem!=null&&mouseItem.getItem() instanceof KSArmor&&mouseItem.getTagCompound()!=null)
		{
			player.inventory.setItemStack(null);
		}
		if(mouseItem!=null&&mouseItem.getItem() instanceof AngelArmorRing)
			mouseItem.setItemDamage(0);
		if(hasarmorlist.contains(player.getName())) hasarmorlist.remove(player.getName());
	}
	@Optional.Method(modid="Baubles")
	public static void baublesDeactivateRings(EntityPlayer player)
	{
		IBaublesItemHandler baubles = player.getCapability(BaublesCapabilities.CAPABILITY_BAUBLES, player.getHorizontalFacing());
		for(int i=0;i<baubles.getSlots();++i)
		{
			ItemStack stack = baubles.getStackInSlot(i);
			if(stack!=null&&stack.getItemDamage()==1&&(stack.getItem()==Itemsss.AngelArmorRing||stack.getItem()==Itemsss.AngelArmorRing))
			{
				stack.setItemDamage(0);
			}
		}
	}
	/**
	 * Makes it so the player can't just drop the armor on the ground
	 * @param event
	 */
	@SubscribeEvent
	public void onDrop(ItemTossEvent event)
	{
		if(event.getEntityItem().getEntityItem().getItem() instanceof KSArmor) event.setCanceled(true);
	}
}
