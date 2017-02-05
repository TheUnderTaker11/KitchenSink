package com.theundertaker11.kitchensink.event;

import com.theundertaker11.kitchensink.ksitems.Itemsss;
import com.theundertaker11.kitchensink.ksitems.tools.DemonicSword;
import com.theundertaker11.kitchensink.ksitems.tools.SkeletonSword;

import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OnMobDeath {
	
	/**
	 * Makes wither drop death nuggets
	 * @param event
	 */
	@SubscribeEvent
	public void onMobDrop(LivingDropsEvent event) 
	{
		if(!event.getEntity().worldObj.isRemote)
		{
			if(event.getEntityLiving() instanceof EntityWither)
			{
				event.getEntityLiving().dropItem(Itemsss.deathNugget, 3);
			}
		}
	}
	
	/**
	  * Adds kills to skeleton and demonic swords.
	  */
	@SubscribeEvent
	public void onDeath(LivingDeathEvent event)
	{  
		if(!event.getEntityLiving().worldObj.isRemote && (event.getSource().getEntity()!=null) && event.getSource().getEntity() instanceof EntityPlayer
				&&!(event.getSource().getEntity() instanceof FakePlayer))
		{
			EntityPlayer player = (EntityPlayer) event.getSource().getEntity();
			ItemStack stack = player.getHeldItem(EnumHand.MAIN_HAND);
			if(player!=null&&stack!=null&&stack.getItem()==Itemsss.SkeletonSword&&stack.getTagCompound()!=null)
			{
				if(event.getEntityLiving() instanceof EntitySkeleton)
				{
					SkeletonSword.addKill(stack, "Skeleton"); 
				}
				if(event.getEntityLiving() instanceof EntityZombie)
				{
					SkeletonSword.addKill(stack, "Zombie"); 
				}
				if(event.getEntityLiving() instanceof EntityCreeper)
				{
					SkeletonSword.addKill(stack, "Creeper"); 
				}
				if(event.getEntityLiving() instanceof EntityEnderman)
				{
					SkeletonSword.addKill(stack, "Enderman"); 
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
}
