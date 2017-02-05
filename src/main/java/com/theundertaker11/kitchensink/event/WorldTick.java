package com.theundertaker11.kitchensink.event;

import java.util.ArrayList;
import java.util.List;

import com.theundertaker11.kitchensink.ksitems.HealthTPitem;
import com.theundertaker11.kitchensink.ksitems.Itemsss;
import com.theundertaker11.kitchensink.ksitems.ProtectionCharm;
import com.theundertaker11.kitchensink.ksitems.tools.LevelPick;

import baubles.api.cap.BaublesCapabilities;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

public class WorldTick {
	
	public static List<String> PlayersWithFlight = new ArrayList<String>();
	
	@SubscribeEvent
	public void onWorldTick(WorldTickEvent event)
	{
		if(KSEventHandler.worldticktimer>500&&event.world.provider.getDimension()==0&&!event.world.isRemote)	
		{
			KSEventHandler.worldticktimer = 0;
			for(EntityPlayerMP player: event.world.getMinecraftServer().getPlayerList().getPlayerList())
			{
				/**
				 * Gives and Takes flight based on the blessed rock.
				 */
				if(!player.capabilities.isCreativeMode)
				{
					String username = player.getGameProfile().getName();
					boolean hasRock=false;
					
				    try{
				    	hasRock = baublesHasRock(player);
				    }catch(NoSuchMethodError e){
				    	
				    }
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
				 for(int i=0;i<player.inventory.getSizeInventory();++i)
				 {
					 ItemStack itemstack = player.inventory.getStackInSlot(i);
					 if(itemstack!=null)
					 {
						 Item item = itemstack.getItem();
						 //Repairs protection charm
						 if(item==Itemsss.ProtectionCharm)
						 {
								ProtectionCharm.RepairCharm(itemstack, 3);
						 }
						 if(itemstack.getTagCompound()!=null)
						 {
							 NBTTagCompound tag=itemstack.getTagCompound();
							 //Repairs the HealthTPitem
							 if(item==Itemsss.HealthTPitem)
							 {
								 HealthTPitem.TimerRepair(itemstack);
							 }
							 //Repairs my Lapis Pickaxe
							 if(item==Itemsss.LevelPick)
							 {
								 if(tag.hasKey("dur")&&tag.hasKey("maxdur")&&tag.hasKey("autorepair"))
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
	@Optional.Method(modid="Baubles")
	public boolean baublesHasRock(EntityPlayer player)
	{
		IBaublesItemHandler baubles = player.getCapability(BaublesCapabilities.CAPABILITY_BAUBLES, player.getHorizontalFacing());
		//Looks in bauble slots
		//To save on how many times I use loops, I also check if the protection charm is in the baubles slots
		//with this same loop
		for(int i=0;i<baubles.getSlots();++i)
		{
			ItemStack stack = baubles.getStackInSlot(i);
			if(stack!=null)
			{
				//This is where I repair the protection charm.
				if(stack.getItem()==Itemsss.ProtectionCharm)
				{
					ProtectionCharm.RepairCharm(stack, 3);
				}
				//This if checks for the rock.
				if(stack.getItem()==Itemsss.blessedRock)
				{
					return true;
				}
			}
		}
		return false;
	}
}
