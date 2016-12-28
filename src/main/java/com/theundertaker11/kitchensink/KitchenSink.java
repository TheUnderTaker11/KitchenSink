package com.theundertaker11.kitchensink;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
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
	
	public static boolean enableDeathHand;
	public static boolean enableGodsTool;
	public static boolean enableProtectionCharm;
	
	public static boolean enableHealthStation;
	public static int HealingBlockRange;
	public static float HealingBlockHealthHealed;
	
	public static int QuarryTicksBetweenOperations;
	public static int QuarryRadius;
	
	public static CreativeTabs KStab = new CreativeTabKS(CreativeTabs.getNextID(), "KStab");
	@SidedProxy(clientSide = Refernce.CLIENTPROXY, serverSide = Refernce.SERVERPROXY)
	public static CommonProxy proxy;
	
	public static boolean debugMode = false;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		config.addCustomCategoryComment("Items", "Set any values to false to disable the recipe(s) for this item.");
		config.addCustomCategoryComment("Health Station", "Configuration for Health Station");
		config.addCustomCategoryComment("Quarry", "Configuration for Quarry");
		enableDeathHand = config.getBoolean("Enable Death Hand Recipe","Items",true,"");
		enableGodsTool = config.getBoolean("Enable God's Tool Recipe","Items",true,"");
		enableProtectionCharm = config.getBoolean("Enable Protection Charm Recipe","Items",true,"");
		
		enableHealthStation = config.getBoolean("Enable Healing Station Recipe", "Health Station", true, "");
		HealingBlockRange = config.getInt("Range for the Healing Beacon", "Health Station", 5, 1, 20, "Radius for Health Station");
		HealingBlockHealthHealed = config.getFloat("Health Healed each second by Health Station", "Health Station", 4, 1, 10, "This will not affect lag.");
		
		QuarryTicksBetweenOperations = config.getInt("Ticks between each operation of the Quarry", "Quarry", 10, 1, 1000, "There is 20 ticks in a second");
		QuarryRadius = config.getInt("Radius of Quarry", "Quarry", 8, 3, 50, "Changes the radius of the Quarry(Mines to bedrock in that radius)");
		
		config.save();
		
		Itemsss.itemprops();
		KSBlocks.createBlocks();
		KSTileEntity.regTileEntitys();
		

	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		CraftingManager.Crecipes();
		proxy.registerRenders();
		MinecraftForge.EVENT_BUS.register(new KSEventHandler());
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
	

}
