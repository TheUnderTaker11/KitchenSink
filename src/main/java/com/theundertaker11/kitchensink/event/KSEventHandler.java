package com.theundertaker11.kitchensink.event;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class KSEventHandler {
	
	public static int worldticktimer = 0;
	
	private static boolean toggle;
	
	public static void init()
	{
		MinecraftForge.EVENT_BUS.register(new KSEventHandler());
		MinecraftForge.EVENT_BUS.register(new WorldTick());
		MinecraftForge.EVENT_BUS.register(new OnMobDeath());
		MinecraftForge.EVENT_BUS.register(new OnEntityHurt());
	}
	@SubscribeEvent
	public void GameTick(TickEvent event)
	{
		if(toggle) ++worldticktimer;
		toggle=!toggle;
	}
}	
