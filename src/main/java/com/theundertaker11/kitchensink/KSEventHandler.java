package com.theundertaker11.kitchensink;

import java.util.ArrayList;
import java.util.Iterator;
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
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

import com.theundertaker11.kitchensink.ksitems.DemonicSword;
import com.theundertaker11.kitchensink.ksitems.HealthTPitem;
import com.theundertaker11.kitchensink.ksitems.Itemsss;
import com.theundertaker11.kitchensink.ksitems.LevelPick;
import com.theundertaker11.kitchensink.ksitems.ProtectionCharm;
import com.theundertaker11.kitchensink.ksitems.SkeletonSword;

import baubles.api.BaublesApi;
import baubles.api.cap.BaublesCapabilities;
import baubles.api.cap.IBaublesItemHandler;

public class KSEventHandler {
	
	private int ticktimer = 0;
	
	List<String> PlayersWithFlight = new ArrayList<String>();
	List<String> PlayersWithSpeed = new ArrayList<String>();
	@SubscribeEvent
	public void GameTick(TickEvent event)
	{
		++ticktimer;
	}

	@SubscribeEvent
	public void WorldTick(WorldTickEvent event)
	{
		if(ticktimer>1000&&event.world.provider.getDimension()==0&&!event.world.isRemote)	
		{
			ticktimer = 0;
			for(EntityPlayerMP player: event.world.getMinecraftServer().getPlayerList().getPlayerList())
			{
				/**
				 * Gives and Takes flight based on the blessed rock.
				 */
				IBaublesItemHandler baubles = player.getCapability(BaublesCapabilities.CAPABILITY_BAUBLES, player.getHorizontalFacing());
				String username = player.getGameProfile().getName();
				boolean hasRock=false;
				boolean hasSpeedRing=false;
				//Looks in bauble slots
				//To save on how many times I use loops, I also check if the protection charm is in the baubles slots
				//with this same loop
				 for(int i=0;i<baubles.getSlots();++i)
				 {
					 if(baubles.getStackInSlot(i)!=null)
				 		{
				 			ItemStack stack = baubles.getStackInSlot(i);
				 			//This is where I repair the protection charm.
				 			if(stack.getItem()==Itemsss.ProtectionCharm)
				 			{
				 				ProtectionCharm.RepairCharm(stack, 3);
				 			}
				 			//This if checks for the rock.
				 			if(stack.getItem()==Itemsss.blessedRock)
				 			{
				 				hasRock=true;
				 			}
				 			/*I'm just throwing stuff in here at this point.
				 			if(stack.getItem().equals(Itemsss.speedForceRing));
				 			{
				 				//player.capabilities.setFlySpeed(3.0F);
				 				//player.capabilities.setPlayerWalkSpeed(3.0F);
				 				//if(stack.getItemDamage()==1)
				 				//{
				 				//	player.stepHeight = 1.0F;
				 				//}
				 				hasSpeedRing=true;
				 				//if(!PlayersWithSpeed.contains(username)) PlayersWithSpeed.add(username);
				 			}*/
				 		}
				 }
				 /*if(!hasSpeedRing&&PlayersWithSpeed.contains(username))
				 {
					 player.capabilities.setFlySpeed(0.1F);
		 			 player.capabilities.setPlayerWalkSpeed(0.2F);
					 player.stepHeight = 0.5F;
				 }*/
				 if(!player.capabilities.isCreativeMode)
				 { 
					 //Looks in normal inventory(Only if not in baubles, to save lag.
					 if(!hasRock)
					 {
					 for(int i=0;i<player.inventory.getSizeInventory();++i)
						{
						 	if(player.inventory.getStackInSlot(i)!=null)
						 		{
						 			ItemStack stack = player.inventory.getStackInSlot(i);
						 			if(stack.getItem()==Itemsss.blessedRock)
						 			{
						 				hasRock=true;
						 				break;
						 			}
						 		}
						}
					 }
					 
					 if(hasRock)
					 {
						 player.capabilities.allowFlying = true;
						 player.sendPlayerAbilities();
						 if(!PlayersWithFlight.contains(username)) PlayersWithFlight.add(username);
					 }	
					 if (PlayersWithFlight.contains(username)&&!hasRock)
						{
							player.capabilities.allowFlying = false;
							player.capabilities.isFlying = false;
							player.fallDistance = 0;
							player.sendPlayerAbilities();
							PlayersWithFlight.remove(username);
						}
				 }
				
				 /**
				  * Repairs the protection charm, Lapis Pick, and Immortal Coward charm
				  */
				 if(player.inventory.hasItemStack(new ItemStack(Itemsss.ProtectionCharm))||player.inventory.hasItemStack(new ItemStack(Itemsss.HealthTPitem))
						 ||player.inventory.hasItemStack(new ItemStack(Itemsss.LevelPick)))
				 {
					 for(int i=0;i<player.inventory.getSizeInventory();++i)
						{
						 if(player.inventory.getStackInSlot(i)!=null)
						 {
							ItemStack itemstack = player.inventory.getStackInSlot(i);
							//Repairs protection charm
							if(player.inventory.getStackInSlot(i).getItem()==Itemsss.ProtectionCharm)
							{
								ProtectionCharm.RepairCharm(itemstack, 3);
							}
							//Repairs the HealthTPitem
							if(player.inventory.getStackInSlot(i).getItem()==Itemsss.HealthTPitem)
							{
								if(itemstack.getTagCompound()!=null)
								{
									HealthTPitem.TimerRepair(itemstack);
								}
								
							}
							//Repairs my Lapis Pickaxe
							if(player.inventory.getStackInSlot(i).getItem()==Itemsss.LevelPick)
							{
								if(itemstack.getTagCompound()!=null)
								{
									if(itemstack.getTagCompound().hasKey("dur")&&itemstack.getTagCompound().hasKey("maxdur")&&itemstack.getTagCompound().hasKey("autorepair"))
									{
										LevelPick.addDur(itemstack, player);	
									}
								}
								
							}
							
						 }
					    } 
				 }
				 
			}
		}
	}
	
			
	//Makes the wither drop death nuggets
	@SubscribeEvent
	public void onMobDrop(LivingDropsEvent event) 
	{
		if(!event.getEntity().worldObj.isRemote)
		{
			if(event.getEntityLiving() instanceof EntityWither)
			{
				event.getEntityLiving().dropItem(Itemsss.deathNugget, 4);
			}
		}
	}
	 

	 /**
	  * Adds kills to skeleton and demonic swords.
	  * 
	  */
	 @SubscribeEvent
	 public void onDeath(LivingDeathEvent event)
	 {  
		 if(!event.getEntityLiving().worldObj.isRemote &&event.getSource().getEntity() instanceof EntityPlayer)
		 {
			 EntityPlayer player = (EntityPlayer) event.getSource().getEntity();
			 if(player.getHeldItem(EnumHand.MAIN_HAND).getItem()!=null&&player.getHeldItem(EnumHand.MAIN_HAND).getItem() == Itemsss.SkeletonSword)
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
			 
			 if(player.getHeldItem(EnumHand.MAIN_HAND).getItem()!=null&&player.getHeldItem(EnumHand.MAIN_HAND).getItem() == Itemsss.DemonicSword)
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
	 //I use this to work the Protection Charm.
	 @SubscribeEvent
	 public void onHurt(LivingHurtEvent event)
	 {
		 if(event.getEntity() instanceof EntityPlayer)
		 {
			 EntityPlayer player = (EntityPlayer)event.getEntity();
			 IBaublesItemHandler baubles = player.getCapability(BaublesCapabilities.CAPABILITY_BAUBLES, player.getHorizontalFacing());
			 if(player.getHealth()<=(player.getMaxHealth()*0.3))
			 {
				 if(player.inventory.hasItemStack(new ItemStack(Itemsss.ProtectionCharm))||ModUtils.baublesHasItemStack(player, new ItemStack(Itemsss.ProtectionCharm)))
				 {
					 boolean finished = false;
					 for(int i=0;i<baubles.getSlots();++i)
					 {
						 if(baubles.getStackInSlot(i)!=null&&baubles.getStackInSlot(i).getItem()==Itemsss.ProtectionCharm)
						 {							 
								 ItemStack itemstack = baubles.getStackInSlot(i);
								 if(itemstack.getTagCompound()!=null&&itemstack.getTagCompound().getInteger("dur")>10)
								 {
									 event.setAmount(0);
									 ProtectionCharm.DamageCharm(itemstack, 10);
									 finished = true;
									 event.setCanceled(true);
									 break;
								 }
						 }
					 }
					 if(!finished)
					 {
					 for(int i=0;i<player.inventory.getSizeInventory();++i)
					 {
						 if(player.inventory.getStackInSlot(i)!=null&&player.inventory.getStackInSlot(i).getItem()==Itemsss.ProtectionCharm)
						 {
								 ItemStack itemstack = player.inventory.getStackInSlot(i);
								 if(itemstack.getTagCompound()!=null&&itemstack.getTagCompound().getInteger("dur")>10)
								 {
									 event.setAmount(0);
									 ProtectionCharm.DamageCharm(itemstack, 10);
									 event.setCanceled(true);
									 break;
								 }
						 }
					 }
					 }
				 }
			 }
		 }
		 
	 }
}	
