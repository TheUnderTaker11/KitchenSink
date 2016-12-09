package com.theundertaker11.kitchensink;

import net.minecraft.client.Minecraft;
//import net.minecraft.client.renderer.entity.RenderItem;
//import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;
import com.theundertaker11.kitchensink.proxy.CommonProxy;
import com.theundertaker11.kitchensink.tileentity.KSTileEntity;
import com.theundertaker11.kitchensink.ksblocks.KSBlocks;
import com.theundertaker11.kitchensink.ksitems.ItemRenderRegistry;
import com.theundertaker11.kitchensink.ksitems.Itemsss;
import com.theundertaker11.kitchensink.ksitems.kscrafting.CraftingManager;
@Mod(modid = Refernce.MODID, version = Refernce.VERSION, name = Refernce.NAME)

public class KitchenSink {
	
	public static CreativeTabs KStab = new CreativeTabKS(CreativeTabs.getNextID(), "KStab");
	@SidedProxy(clientSide = Refernce.CLIENTPROXY, serverSide = Refernce.SERVERPROXY)
	public static CommonProxy proxy;
	
	public static boolean debugMode = false;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		
		Itemsss.itemprops();
		KSBlocks.createBlocks();
		KSTileEntity.regTileEntitys();
		

	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		CraftingManager.Crecipes();
		proxy.RegisterRenders();
		MinecraftForge.EVENT_BUS.register(new KSEventHandler());
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
	

}
