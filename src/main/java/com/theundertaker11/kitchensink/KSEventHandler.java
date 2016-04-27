package com.theundertaker11.kitchensink;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

import com.theundertaker11.kitchensink.ksitems.DemonicSword;
import com.theundertaker11.kitchensink.ksitems.Itemsss;
import com.theundertaker11.kitchensink.ksitems.LevelPick;
import com.theundertaker11.kitchensink.ksitems.SkeletonSword;

public class KSEventHandler {
	
	private int ticktimer = 0;
	
	List<String> PlayersWithFlight = new ArrayList<String>();
	@SubscribeEvent
	public void pTickEvent(PlayerTickEvent event)
	{
		/*
		 * 
		 * Gives and Takes flight based on the blessed rock.
		 */
		String person = event.player.getGameProfile().getName();
		if (event.player != null)
		{
		 if(event.player.capabilities.isCreativeMode != true)
		 { 

				if(event.player.inventory.hasItemStack(new ItemStack(Itemsss.blessedRock)))
				{
					PlayersWithFlight.add(person);
					event.player.capabilities.allowFlying = true;
					event.player.sendPlayerAbilities();
				}
			else
			{
				if (PlayersWithFlight.contains(person) && !event.player.inventory.hasItemStack(new ItemStack(Itemsss.blessedRock)))
				{
					event.player.capabilities.allowFlying = false;
					event.player.capabilities.isFlying = false;
					event.player.fallDistance = 0;
					event.player.sendPlayerAbilities();
					PlayersWithFlight.remove(person);
				}
				
			}
		 }
		}
		/*
		 * Calls the add dur method based on my timer, it checks if it actually can do that in the class,
		 * so all I have to do is call it
		 */
		if(!event.player.worldObj.isRemote)
		{
		if(event.player.inventory.hasItemStack(new ItemStack(Itemsss.LevelPick)))
		{
			if(++ticktimer>160)
			{
				ticktimer = 0;
				for(int i=0;i<event.player.inventory.getSizeInventory();++i)
				{
				 if(event.player.inventory.getStackInSlot(i)!=null)
				 {
					if(event.player.inventory.getStackInSlot(i).getItem()==Itemsss.LevelPick)
					{
						ItemStack itemstack = event.player.inventory.getStackInSlot(i);
						if(itemstack.getTagCompound()!=null)
						{
							if(itemstack.getTagCompound().hasKey("dur")&&itemstack.getTagCompound().hasKey("maxdur")&&itemstack.getTagCompound().hasKey("autorepair"))
							{
								LevelPick.addDur(itemstack, event.player);	
							}
						}
						
					}
				 }
			    }
			}
		}
		}
	}
			
			
	/*
	 * 
	 * Makes the wither drop death nuggets
	 */
	@SubscribeEvent
	public void onMobDrop(LivingDropsEvent event) 
	{
		if(!event.getEntity().worldObj.isRemote)
		{
			if(event.getEntityLiving() instanceof EntityWither)
			{
				event.getEntityLiving().dropItem(Itemsss.deathNugget, 2);
			}
		}
	}
	 
	/*
	 * 
	 * Adds kills to skeleton and demonic swords.
	 */
	 @SubscribeEvent
	 public void onDeath(LivingDeathEvent event)
	 {
		 if(!event.getEntityLiving().worldObj.isRemote && event.getSource().getEntity() instanceof EntityPlayer )
		 {
			 EntityPlayer player = (EntityPlayer) event.getSource().getEntity();
			 if(player.getHeldItem(EnumHand.MAIN_HAND).getItem() == Itemsss.SkeletonSword)
			 {
				 ItemStack in = player.getHeldItem(EnumHand.MAIN_HAND);
				 if(event.getEntityLiving() instanceof EntitySkeleton)
				 {
				 	SkeletonSword.addKill(in, 0); 
				 }
				 if(event.getEntityLiving() instanceof EntityZombie)
				 {
				 	SkeletonSword.addKill(in, 1); 
				 }
				 if(event.getEntityLiving() instanceof EntityCreeper)
				 {
				 	SkeletonSword.addKill(in, 2); 
				 }
				 if(event.getEntityLiving() instanceof EntityEnderman)
				 {
				 	SkeletonSword.addKill(in, 3); 
				 }
			 }
			 
			 if(player.getHeldItem(EnumHand.MAIN_HAND).getItem() == Itemsss.DemonicSword)
			 {
				 ItemStack in = player.getHeldItem(EnumHand.MAIN_HAND);
				 if(event.getEntityLiving() instanceof EntitySkeleton)
				 {
					 DemonicSword.addKill(in, 0); 
				 }
				 if(event.getEntityLiving() instanceof EntityZombie)
				 {
					 DemonicSword.addKill(in, 1); 
				 }
				 if(event.getEntityLiving() instanceof EntityCreeper)
				 {
					 DemonicSword.addKill(in, 2); 
				 }
				 if(event.getEntityLiving() instanceof EntityEnderman)
				 {
					 DemonicSword.addKill(in, 3); 
				 }
				 
				 
			 }
		 }
		 
	 }
	 
	 /*
	  * Makes it so no blocks get harvested when dur is 0 on levelpick
	  * 
	  */
	 @SubscribeEvent
	 public void harvestBlocks(BreakEvent event)
	 {
		 if(event.getPlayer() !=null&&event.getPlayer().getHeldItem(EnumHand.MAIN_HAND).getItem()==Itemsss.LevelPick)
		 {
			 if(event.getPlayer().getHeldItem(EnumHand.MAIN_HAND).getTagCompound()!=null)
			 {
				 ItemStack item = event.getPlayer().getHeldItem(EnumHand.MAIN_HAND);
				 if(item.getTagCompound().getInteger("dur")==0)
				 {
					 event.setCanceled(true);
				 }
			 }
		 }
		 
	 }
}	
