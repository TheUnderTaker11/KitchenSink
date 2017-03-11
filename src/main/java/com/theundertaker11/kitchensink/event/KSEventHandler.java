package com.theundertaker11.kitchensink.event;

import com.theundertaker11.kitchensink.ksitems.armor.angelhandler.AngelArmorHandler;
import com.theundertaker11.kitchensink.ksitems.armor.angelhandler.AngelLegHandler;
import com.theundertaker11.kitchensink.ksitems.armor.deathhandler.DeathArmorHandler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class KSEventHandler {
	
	public static int worldticktimer = 0;
	public static int Angelticktimer = 0;
	private static boolean toggle;
	
	public static void init()
	{
		MinecraftForge.EVENT_BUS.register(new KSEventHandler());
		MinecraftForge.EVENT_BUS.register(new WorldTick());
		MinecraftForge.EVENT_BUS.register(new OnMobDeath());
		MinecraftForge.EVENT_BUS.register(new OnEntityHurt());
		MinecraftForge.EVENT_BUS.register(new AngelLegHandler());
		MinecraftForge.EVENT_BUS.register(new AngelArmorHandler());
	}
	@SubscribeEvent
	public void GameTick(TickEvent event)
	{
		if(toggle)
		{
			++worldticktimer;
			++Angelticktimer;
		}
		toggle=!toggle;
	}
}	
