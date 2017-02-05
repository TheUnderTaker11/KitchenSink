package com.theundertaker11.kitchensink.event;

import com.theundertaker11.kitchensink.ksitems.Itemsss;
import com.theundertaker11.kitchensink.ksitems.ProtectionCharm;

import baubles.api.cap.BaublesCapabilities;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OnEntityHurt {
	
	@SubscribeEvent
	public void onHurt(LivingHurtEvent event)
	{
		if(event.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)event.getEntity();
			
			if(player.getHealth()<=(player.getMaxHealth()*0.3))
			{
				//Checks baubles first to reduce lag
				boolean finished = false;
				try{
					finished = doBaubles(player, event);
				}catch(NoSuchMethodError e){
		    	
				}
				if(!finished)
				{
					for(int i=0;i<player.inventory.getSizeInventory();++i)
					{
						ItemStack itemstack = player.inventory.getStackInSlot(i);
						if(itemstack!=null&&itemstack.getItem()==Itemsss.ProtectionCharm)
						{
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
	
	@Optional.Method(modid="Baubles")
	private boolean doBaubles(EntityPlayer player, LivingHurtEvent event)
	{
		IBaublesItemHandler baubles = player.getCapability(BaublesCapabilities.CAPABILITY_BAUBLES, player.getHorizontalFacing());
		for(int i=0;i<baubles.getSlots();++i)
		{
			ItemStack itemstack = baubles.getStackInSlot(i);
			if(itemstack!=null&&itemstack.getItem()==Itemsss.ProtectionCharm)
			{							 
				if(itemstack.getTagCompound()!=null&&itemstack.getTagCompound().getInteger("dur")>10)
				{
					event.setAmount(0);
					ProtectionCharm.DamageCharm(itemstack, 10);
					event.setCanceled(true);
					return true;
				}
			}
		}
		return false;
	}
}
