package com.theundertaker11.kitchensink;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;
import com.theundertaker11.kitchensink.proxy.CommonProxy;
import com.theundertaker11.kitchensink.proxy.GuiProxy;
import com.theundertaker11.kitchensink.render.ItemRenderRegistry;
import com.theundertaker11.kitchensink.tileentity.KSTileEntityRegistry;
import com.theundertaker11.kitchensink.crafting.CraftingManager;
import com.theundertaker11.kitchensink.event.KSEventHandler;
import com.theundertaker11.kitchensink.ksblocks.KSBlocks;
import com.theundertaker11.kitchensink.ksitems.Itemsss;
@Mod(modid = Refernce.MODID, version = Refernce.VERSION, name = Refernce.NAME, dependencies="after:Baubles")

public class KitchenSink {
	
	public static boolean enableDeathHand;
	public static boolean enableGodsTool;
	public static boolean enableProtectionCharm;
	public static boolean enableBlessedFurnace;
	public static boolean enableAngelArmor;
	public static boolean enableDeathArmor;
	
	public static boolean enableHealthStation;
	public static int HealingBlockRange;
	public static float HealingBlockHealthHealed;
	
	public static int QuarryTicksBetweenOperations;
	public static int QuarryRadius;
	
	public static CreativeTabs KStab = new CreativeTabKS(CreativeTabs.getNextID(), "KStab");
	
	@Mod.Instance
    public static KitchenSink instance;
	
	@SidedProxy(clientSide = Refernce.CLIENTPROXY, serverSide = Refernce.SERVERPROXY)
	public static CommonProxy proxy;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		loadConfig(config);
		
		Itemsss.itemprops();
		KSBlocks.createBlocks();
		KSTileEntityRegistry.regTileEntitys();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		CraftingManager.init();
		proxy.registerRenders();
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiProxy());
		KSEventHandler.init();
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
	
	public static void loadConfig(Configuration config)
	{
		config.load();
		config.addCustomCategoryComment("Items", "Set any values to false to disable the recipe(s) for this item.");
		config.addCustomCategoryComment("Armor", "Various armor config options");
		config.addCustomCategoryComment("Health Station", "Configuration for Health Station");
		config.addCustomCategoryComment("Quarry", "Configuration for Quarry");
		enableDeathHand = config.getBoolean("Enable Death Hand Recipe","Items",true,"");
		enableGodsTool = config.getBoolean("Enable God's Tool Recipe","Items",true,"");
		enableProtectionCharm = config.getBoolean("Enable Protection Charm Recipe","Items",true,"");
		enableBlessedFurnace = config.getBoolean("Enable Blessed Furnace", "Items", true, "");
		
		enableHealthStation = config.getBoolean("Enable Healing Station Recipe", "Health Station", true, "");
		HealingBlockRange = config.getInt("Range for the Healing Beacon", "Health Station", 5, 1, 20, "Radius for Health Station");
		HealingBlockHealthHealed = config.getFloat("Health Healed each second by Health Station", "Health Station", 4, 1, 10, "This will not affect lag.");
		
		QuarryTicksBetweenOperations = config.getInt("Ticks between each operation of the Quarry", "Quarry", 10, 1, 1000, "There is 20 ticks in a second");
		QuarryRadius = config.getInt("Radius of Quarry", "Quarry", 8, 3, 50, "Changes the radius of the Quarry(Mines to bedrock in that radius)");
		
		enableAngelArmor = config.getBoolean("Enable Angel Alloy Armor", "Armor", true, "");
		enableDeathArmor = config.getBoolean("Enable Death Metal Armor", "Armor", true, "");
		
		config.save();
	}

}
