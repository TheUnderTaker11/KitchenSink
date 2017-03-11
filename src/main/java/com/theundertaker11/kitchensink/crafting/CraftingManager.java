package com.theundertaker11.kitchensink.crafting;

import com.theundertaker11.kitchensink.KitchenSink;
import com.theundertaker11.kitchensink.crafting.armorupgrades.FlightUpgradeCrafting;
import com.theundertaker11.kitchensink.crafting.armorupgrades.LegSpeedUpgradeCrafting;
import com.theundertaker11.kitchensink.crafting.armorupgrades.WaterBreathingUpgradeCrafting;
import com.theundertaker11.kitchensink.crafting.levelpick.LevelToolAutoRepair;
import com.theundertaker11.kitchensink.crafting.levelpick.LevelToolFixing;
import com.theundertaker11.kitchensink.crafting.levelpick.LevelToolMagnet;
import com.theundertaker11.kitchensink.crafting.levelpick.LevelToolSpeed;
import com.theundertaker11.kitchensink.crafting.levelpick.LevelToolUnbreakable;
import com.theundertaker11.kitchensink.crafting.levelpick.LevelToolXP;
import com.theundertaker11.kitchensink.ksblocks.KSBlocks;
import com.theundertaker11.kitchensink.ksitems.Itemsss;
import com.theundertaker11.kitchensink.ksitems.xpItem;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JEIPlugin;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class CraftingManager{
	public static void init(){

		//items
		addItemRecipes();
		addToolRecipes();
		addBlockRecipes();
		addArmorRecipes();
		
		GameRegistry.addShapelessRecipe(new ItemStack(Itemsss.deathNugget, 9), Itemsss.deathIngot);
		GameRegistry.addShapelessRecipe(new ItemStack(Itemsss.deathNugget, 1), Items.NETHER_STAR);
		
		addSoulAndDemonSwordRecipes();
		addMagnetRingRecipes();
		addLevelPickRecipe();
		addDummyRecipes();
		

		//Death Hand looting recipe
		ItemStack deathhand = new ItemStack(Itemsss.DeathHand);
		deathhand.setTagCompound(new NBTTagCompound());
		deathhand.getTagCompound().setBoolean("active", false);
		deathhand.getTagCompound().setString("owner", "Will be set after this is crafted, ignore below message.");
		deathhand.addEnchantment(Enchantments.LOOTING, 5);
		GameRegistry.addRecipe(deathhand, "yyy", "yxy", "yyy", 'x',Itemsss.DeathHand, 'y', Blocks.EMERALD_ORE);
		
		//These are what do the ACTUAL lapis tool recipes
		GameRegistry.addRecipe(new LevelToolFixing());
		GameRegistry.addRecipe(new LevelToolSpeed());
		GameRegistry.addRecipe(new LevelToolMagnet());
		GameRegistry.addRecipe(new LevelToolXP());
		GameRegistry.addRecipe(new LevelToolAutoRepair());
		GameRegistry.addRecipe(new LevelToolUnbreakable());
		
		GameRegistry.addRecipe(new FlightUpgradeCrafting());
		GameRegistry.addRecipe(new LegSpeedUpgradeCrafting());
		GameRegistry.addRecipe(new WaterBreathingUpgradeCrafting());
		
		//And actual death scythe/demon sword recipes
		GameRegistry.addRecipe(new CustomDeathCrafting());
		GameRegistry.addRecipe(new CustomSwordCrafting());
	}
	
	private static void addArmorRecipes()
	{
		if(KitchenSink.enableAngelArmor)
		{
			GameRegistry.addRecipe(new ItemStack(Itemsss.AngelArmorRing), "xzx", "xyx", "xxx", 'x', Itemsss.angelAlloy, 'y', Itemsss.godEssence, 'z', Itemsss.netherStarPlate);
		}
		/*
		if(KitchenSink.enableDeathArmor)
		{
			GameRegistry.addRecipe(new ItemStack(Itemsss.deathHelmet), "xxx", "x x", "   ", 'x', Itemsss.deathIngot);
			GameRegistry.addRecipe(new ItemStack(Itemsss.deathChestplate), "x x", "xxx", "xxx", 'x', Itemsss.deathIngot);
			GameRegistry.addRecipe(new ItemStack(Itemsss.deathLeggings), "xxx", "x x", "x x", 'x', Itemsss.deathIngot);
			GameRegistry.addRecipe(new ItemStack(Itemsss.deathBoots), "   ", "x x", "x x", 'x', Itemsss.deathIngot);
		}
		*/
	}
	private static void addToolRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(Itemsss.blessedPick), "xxx", " y ", " y ", 'x', Itemsss.angelAlloy, 'y', Itemsss.obsidianPlate);
		GameRegistry.addRecipe(new ItemStack(Itemsss.blessedShovel), " x ", " y ", " y ", 'x', Itemsss.angelAlloy, 'y', Itemsss.obsidianPlate);
		GameRegistry.addRecipe(new ItemStack(Itemsss.blessedSword), " x ", " x ", " y ", 'x', Itemsss.angelAlloy, 'y', Itemsss.obsidianPlate);
		GameRegistry.addRecipe(new ItemStack(Itemsss.blessedAxe), " xx", " yx", " y ", 'x', Itemsss.angelAlloy, 'y', Itemsss.obsidianPlate);
		GameRegistry.addRecipe(new ItemStack(Itemsss.blessedAxe), "xx ", "xy ", " y ", 'x', Itemsss.angelAlloy, 'y', Itemsss.obsidianPlate);
		GameRegistry.addRecipe(new ItemStack(Itemsss.blessedHoe), "xx ", " y ", " y ", 'x', Itemsss.angelAlloy, 'y', Itemsss.obsidianPlate);
		GameRegistry.addRecipe(new ItemStack(Itemsss.blessedHoe), " xx", " y ", " y ", 'x', Itemsss.angelAlloy, 'y', Itemsss.obsidianPlate);
		if(KitchenSink.enableGodsTool)
			GameRegistry.addRecipe(new ItemStack(Itemsss.godsTool), "   ", "ayb", "xzx", 'x', Itemsss.godEssence, 'y', Itemsss.blessedPick, 'a', Itemsss.blessedShovel, 'b', Itemsss.blessedAxe, 'z', Itemsss.blessedHoe);
		if(KitchenSink.enableDeathHand)
			GameRegistry.addRecipe(new ItemStack(Itemsss.DeathHand), " z ", "yxy", " z ", 'z',Itemsss.deathsSythe, 'y', Itemsss.godEssence, 'x', Itemsss.deathIngot);
	}
	
	private static void addBlockRecipes()
	{
		IRecipe black = new ShapedOreRecipe(new ItemStack(KSBlocks.BlackBlock, 6), new Object[] {" y ", "xxx", "xxx", 'x', Blocks.COBBLESTONE, 'y', "dyeBlack"});
		GameRegistry.addRecipe(black);
		IRecipe babyblue = new ShapedOreRecipe(new ItemStack(KSBlocks.BabyBlueBlock, 6), new Object[] {" y ", "xxx", "xxx", 'x', Blocks.COBBLESTONE, 'y', "dyeLightBlue"});
		GameRegistry.addRecipe(babyblue);
		IRecipe blue = new ShapedOreRecipe(new ItemStack(KSBlocks.BlueBlock, 6), new Object[] {" y ", "xxx", "xxx", 'x', Blocks.COBBLESTONE, 'y', "dyeBlue"});
		GameRegistry.addRecipe(blue);
		IRecipe brown = new ShapedOreRecipe(new ItemStack(KSBlocks.BrownBlock, 6), new Object[] {" y ", "xxx", "xxx", 'x', Blocks.COBBLESTONE, 'y', "dyeBrown"});
		GameRegistry.addRecipe(brown);
		IRecipe green = new ShapedOreRecipe(new ItemStack(KSBlocks.GreenBlock, 6), new Object[] {" y ", "xxx", "xxx", 'x', Blocks.COBBLESTONE, 'y', "dyeGreen"});
		GameRegistry.addRecipe(green);
		IRecipe orange = new ShapedOreRecipe(new ItemStack(KSBlocks.OrangeBlock, 6), new Object[] {" y ", "xxx", "xxx", 'x', Blocks.COBBLESTONE, 'y', "dyeOrange"});
		GameRegistry.addRecipe(orange);
		IRecipe purple = new ShapedOreRecipe(new ItemStack(KSBlocks.PurpleBlock, 6), new Object[] {" y ", "xxx", "xxx", 'x', Blocks.COBBLESTONE, 'y', "dyePurple"});
		GameRegistry.addRecipe(purple);
		IRecipe red = new ShapedOreRecipe(new ItemStack(KSBlocks.RedBlock, 6), new Object[] {" y ", "xxx", "xxx", 'x', Blocks.COBBLESTONE, 'y', "dyeRed"});
		GameRegistry.addRecipe(red);
		IRecipe white = new ShapedOreRecipe(new ItemStack(KSBlocks.WhiteBlock, 6), new Object[] {" y ", "xxx", "xxx", 'x', Blocks.COBBLESTONE, 'y', "dyeWhite"});
		GameRegistry.addRecipe(white);
		IRecipe yellow = new ShapedOreRecipe(new ItemStack(KSBlocks.YellowBlock, 6), new Object[] {" y ", "xxx", "xxx", 'x', Blocks.COBBLESTONE, 'y', "dyeYellow"});
		GameRegistry.addRecipe(yellow);
		
		IRecipe quarry = new ShapedOreRecipe(new ItemStack(KSBlocks.QuarryBlock),new Object[] {"aaa", "xyx", "aza", 'x', Blocks.PISTON, 'y', Items.DIAMOND_PICKAXE, 'z', "dustRedstone", 'a', "stone"});
		GameRegistry.addRecipe(quarry);
		IRecipe trashchest = new ShapedOreRecipe(new ItemStack(KSBlocks.TrashChest), new Object[] {" x ", "xyx", " x ", 'x', "cobblestone", 'y', Blocks.CHEST});
		GameRegistry.addRecipe(trashchest);
		
		if(KitchenSink.enableBlessedFurnace)
		{
			IRecipe blessedfurnace = new ShapedOreRecipe(new ItemStack(KSBlocks.BlessedFurnace), "xzx", "zyz", "xzx", 'x', "cobblestone", 'y', Blocks.FURNACE, 'z', Itemsss.angelAlloy);
			GameRegistry.addRecipe(blessedfurnace);
		}
		if(KitchenSink.enableHealthStation)
		{
			IRecipe healingblock = new ShapedOreRecipe(new ItemStack(KSBlocks.HealingBlock),"zxz", "xyx", "zxz", 'x', "dyeRed", 'z', "dyeWhite", 'y', Itemsss.angelAlloy);
			GameRegistry.addRecipe(healingblock);
		}
	}
	
	private static void addItemRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(Itemsss.obsidianPlate), "xxx", "xxx", "xxx", 'x', Blocks.OBSIDIAN);
		GameRegistry.addRecipe(new ItemStack(Itemsss.deathIngot), "xxx", "xxx", "xxx", 'x', Itemsss.deathNugget);
		GameRegistry.addRecipe(new ItemStack(Itemsss.diamondPlate), "xx", "xx", 'x', Itemsss.compDiamond);
		GameRegistry.addRecipe(new ItemStack(Itemsss.netherStarPlate), "xx", "xx", 'x', Items.NETHER_STAR);
		GameRegistry.addRecipe(new ItemStack(Itemsss.godEssence), "xax", "yzy", "xax", 'x', Itemsss.netherStarPlate, 'y', Itemsss.deathNugget, 'z', Items.WHEAT_SEEDS, 'a', Itemsss.obsidianPlate);
		GameRegistry.addRecipe(new ItemStack(Itemsss.angelAlloy), "axa", "xyx", "aza", 'z', Itemsss.diamondPlate, 'y', Blocks.LAPIS_BLOCK, 'x', Itemsss.compDiamond, 'a', Itemsss.obsidianPlate);
		GameRegistry.addRecipe(new ItemStack(Itemsss.ItemMagnetT2), " x ", "xyx", " x ", 'x', Itemsss.ItemMagnet, 'y', Itemsss.obsidianPlate);
		GameRegistry.addRecipe(new ItemStack(Itemsss.ItemMagnetT3), " x ", "xyx", " x ", 'x', Itemsss.ItemMagnetT2, 'y', Items.DIAMOND);
		GameRegistry.addRecipe(new ItemStack(Itemsss.ItemMagnetT4), " x ", "xyx", " x ", 'x', Itemsss.ItemMagnetT3, 'y', Itemsss.compDiamond);
		GameRegistry.addRecipe(new ItemStack(Itemsss.ItemMagnetT5), " x ", "xyx", " x ", 'x', Itemsss.ItemMagnetT4, 'y', Itemsss.diamondPlate);
		GameRegistry.addRecipe(new ItemStack(Itemsss.ItemMagnetT6), " x ", "xyx", " x ", 'x', Itemsss.ItemMagnetT5, 'y', Itemsss.angelAlloy);
		GameRegistry.addRecipe(new ItemStack(Itemsss.blessedRock), "zxz", "xyx", "zxz", 'x', Blocks.COBBLESTONE, 'y', Itemsss.angelAlloy, 'z', Items.NETHER_STAR);
		GameRegistry.addRecipe(new ItemStack(Itemsss.TPitem), "zzz", "xyx", "zzz", 'x', Items.DIAMOND, 'y', Itemsss.obsidianPlate, 'z', Items.ENDER_PEARL);
		GameRegistry.addRecipe(new ItemStack(Itemsss.HealthTPitem), "xzx", "aya", "xzx", 'x', Itemsss.netherStarPlate, 'y', Itemsss.TPitem, 'z', Itemsss.angelAlloy, 'a', Items.END_CRYSTAL);
		GameRegistry.addRecipe(new ItemStack(Items.NETHER_STAR), " x ", "xxx", " x ", 'x', Itemsss.deathNugget);
		
		IRecipe compdiamond = new ShapedOreRecipe(new ItemStack(Itemsss.compDiamond),new Object[] {"xx", "xx", 'x', "gemDiamond"});
		GameRegistry.addRecipe(compdiamond);
		IRecipe itemmagnett1 = new ShapedOreRecipe(new ItemStack(Itemsss.ItemMagnet),new Object[] {"x x", "x x", "xyx", 'x', "ingotIron", 'y', "gemDiamond"});
		GameRegistry.addRecipe(itemmagnett1);
		IRecipe xpitem = new ShapedOreRecipe(new ItemStack(Itemsss.xpItem), " z ", "xyx", "xxx", 'x', Items.ROTTEN_FLESH, 'y', Items.GLASS_BOTTLE, 'z', "gemDiamond");
		GameRegistry.addRecipe(xpitem);
		IRecipe magnetring = new ShapedOreRecipe(new ItemStack(Itemsss.MagnetRing), new Object[] {"yxy", "x x", "yxy", 'x', "ingotIron", 'y', "dustRedstone"});
		GameRegistry.addRecipe(magnetring);
		
		if(KitchenSink.enableProtectionCharm)
			GameRegistry.addRecipe(new ItemStack(Itemsss.ProtectionCharm), "xzx", "zyz", "xzx", 'x', Itemsss.obsidianPlate, 'y', Itemsss.godEssence, 'z', Itemsss.angelAlloy);
		
	}
	
	/**
	 * Dummy recipes mean recipes that can't actually be crafted but look just like recipes added by my custom classes.
	 */
	private static void addDummyRecipes()
	{
		ItemStack speedangelupgrade = new ItemStack(Itemsss.AngelArmorRing);
		speedangelupgrade.setTagCompound(new NBTTagCompound());
		speedangelupgrade.getTagCompound().setInteger("speed", -1);
		ItemStack potionspeed = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.STRONG_SWIFTNESS);
		GameRegistry.addRecipe(speedangelupgrade, "zyz", "yxy", "zyz",'z', Items.SUGAR, 'x', new ItemStack(Itemsss.AngelArmorRing,1,1), 'y', potionspeed);
		
		ItemStack flightangelupgrade = new ItemStack(Itemsss.AngelArmorRing);
		flightangelupgrade.setTagCompound(new NBTTagCompound());
		flightangelupgrade.getTagCompound().setBoolean("flight", true);
		GameRegistry.addRecipe(flightangelupgrade, "xy ", "   ", "   ",'y', new ItemStack(Itemsss.blessedRock,1,1), 'x', Itemsss.AngelArmorRing);
		
		ItemStack breathingangelupgrade = new ItemStack(Itemsss.AngelArmorRing);
		breathingangelupgrade.setTagCompound(new NBTTagCompound());
		breathingangelupgrade.getTagCompound().setBoolean("flight", true);
		IRecipe waterbreathing = new ShapedOreRecipe(breathingangelupgrade, new Object[] {"zaz", "yxy", "zaz",'y', "slimeball", 'x', Itemsss.AngelArmorRing, 'z', Items.GLASS_BOTTLE, 'a', new ItemStack(Itemsss.obsidianPlate, 1, 1)});
		GameRegistry.addRecipe(waterbreathing);
		
		ItemStack demonsword100kills = new ItemStack(Itemsss.DemonicSword);
		demonsword100kills.setTagCompound(new NBTTagCompound());
		NBTTagCompound demontag = demonsword100kills.getTagCompound();
		demontag.setInteger("zombiekills", 100);
		demontag.setInteger("skeletonkills", 100);
		demontag.setInteger("creeperkills", 100);
		demontag.setInteger("endermankills", 100);
		
		GameRegistry.addRecipe(new ItemStack(Itemsss.deathsSythe), "yxx", " zx", " xx", 'x', new ItemStack(Itemsss.deathIngot,1,1), 'y', Itemsss.netherStarPlate, 'z', demonsword100kills);
		
/////////Start demon sword///////////////////////////////////////////////////////////////////////////////////////////////
		ItemStack zombie50kills = new ItemStack(Itemsss.SkeletonSword);
		ItemStack skeleton50kills = new ItemStack(Itemsss.SkeletonSword);
		ItemStack creeper50kills = new ItemStack(Itemsss.SkeletonSword);
		ItemStack demonsword = new ItemStack(Itemsss.DemonicSword);
		
		zombie50kills.setTagCompound(new NBTTagCompound());
		skeleton50kills.setTagCompound(new NBTTagCompound());
		creeper50kills.setTagCompound(new NBTTagCompound());
		demonsword.setTagCompound(new NBTTagCompound());
		
		zombie50kills.getTagCompound().setString("mob", "Zombie");
		skeleton50kills.getTagCompound().setString("mob", "Skeleton");
		creeper50kills.getTagCompound().setString("mob", "Creeper");
		zombie50kills.getTagCompound().setInteger("kills", 50);
		skeleton50kills.getTagCompound().setInteger("kills", 50);
		creeper50kills.getTagCompound().setInteger("kills", 50);
		demonsword.getTagCompound().setInteger("zombiekills", 0);
		demonsword.getTagCompound().setInteger("skeletonkills", 0);
		demonsword.getTagCompound().setInteger("creeperkills", 0);
		demonsword.getTagCompound().setInteger("endermankills", 0);
		
		GameRegistry.addRecipe(demonsword, "xxx", "yxz", "aba", 'x', new ItemStack(Itemsss.deathIngot,1,1),'a', Itemsss.netherStarPlate, 'y', zombie50kills, 'b', skeleton50kills, 'z', creeper50kills);
	
/////////Start Lapis pick///////////////////////////////////////////////////////////////////////////////////////////////
		ItemStack levelPickRepairResult = new ItemStack(Itemsss.LevelPick);
		ItemStack levelPickMagnetResult = new ItemStack(Itemsss.LevelPick);
		ItemStack levelPickSpeedResult = new ItemStack(Itemsss.LevelPick);
		ItemStack levelPickXPResult = new ItemStack(Itemsss.LevelPick);
		ItemStack levelPickAutoRepairResult = new ItemStack(Itemsss.LevelPick);
		ItemStack levelPickUnbreakableResult = new ItemStack(Itemsss.LevelPick);
		
		ItemStack levelPickRepairStart = new ItemStack(Itemsss.LevelPick);
		ItemStack levelPickStart = new ItemStack(Itemsss.LevelPick);
		//I have a texture registered so Item Damage 8 looks like a regular lapis pick
		levelPickStart.setItemDamage(8);
		levelPickRepairStart.setItemDamage(8);
		levelPickRepairResult.setTagCompound(new NBTTagCompound());
		levelPickMagnetResult.setTagCompound(new NBTTagCompound());
		levelPickSpeedResult.setTagCompound(new NBTTagCompound());
		levelPickXPResult.setTagCompound(new NBTTagCompound());
		levelPickRepairStart.setTagCompound(new NBTTagCompound());
		levelPickAutoRepairResult.setTagCompound(new NBTTagCompound());
		levelPickUnbreakableResult.setTagCompound(new NBTTagCompound());
		
		levelPickRepairStart.getTagCompound().setInteger("dur", 150);
		levelPickRepairStart.getTagCompound().setInteger("maxdur", 250);
		
		//These values are used when I set my tooltip to show what I want the player to see
		levelPickRepairResult.getTagCompound().setBoolean("dummyRepair", true);
		levelPickAutoRepairResult.getTagCompound().setBoolean("dummyAutoRepair", true);
		levelPickMagnetResult.getTagCompound().setBoolean("dummyMagnet", true);
		levelPickMagnetResult.setItemDamage(2);
		levelPickAutoRepairResult.setItemDamage(3);
		levelPickSpeedResult.getTagCompound().setBoolean("dummySpeed", true);
		levelPickUnbreakableResult.getTagCompound().setBoolean("dummyUnbreakable", true);
		
		levelPickXPResult.getTagCompound().setBoolean("dummyXP", true);
		levelPickXPResult.setItemDamage(1);
		
		GameRegistry.addRecipe(levelPickRepairResult, "   ", " xy", "   ", 'x', levelPickRepairStart, 'y',Items.IRON_INGOT);
		GameRegistry.addRecipe(levelPickMagnetResult, "   ", " xy", "   ", 'x', levelPickStart, 'y', Itemsss.ItemMagnetT6);
		GameRegistry.addRecipe(levelPickSpeedResult, "   ", " xy", "   ", 'x', levelPickStart, 'y', Blocks.REDSTONE_BLOCK);
		GameRegistry.addRecipe(levelPickXPResult, "yyy", "yxy", "yyy", 'x', levelPickStart, 'y', Itemsss.xpItem);
		GameRegistry.addRecipe(levelPickAutoRepairResult, "   ", " xy", "   ", 'x', levelPickStart, 'y', Itemsss.diamondPlate);
		GameRegistry.addRecipe(levelPickUnbreakableResult, "zyz", "yxy", "zyz", 'x', levelPickStart, 'y', Itemsss.diamondPlate, 'z', Itemsss.obsidianPlate);
	}

	private static void addLevelPickRecipe()
	{
		ItemStack levelpick = new ItemStack(Itemsss.LevelPick);
		
		levelpick.setTagCompound(new NBTTagCompound());
		
		NBTTagCompound lvlpick = levelpick.getTagCompound();
		lvlpick.setInteger("dur", 250);
		lvlpick.setInteger("maxdur", 250);
		lvlpick.setInteger("harvestlevel", 0);
		lvlpick.setInteger("picklevel", 0);
		lvlpick.setFloat("pickspeed", 3F);
		lvlpick.setInteger("pickxp", 0);
		lvlpick.setInteger("xptonextlevel", 80);
		lvlpick.setInteger("texture", 0);
		IRecipe LevelPickRecipe = new ShapedOreRecipe(levelpick, "zzz", " x ", " y ", 'x', "ingotIron", 'y', "stickWood", 'z', "gemLapis");
		GameRegistry.addRecipe(LevelPickRecipe);
	}
	
	private static void addMagnetRingRecipes()
	{
		ItemStack T1 = new ItemStack(Itemsss.MagnetRing);
		ItemStack T2 = new ItemStack(Itemsss.MagnetRing);
		ItemStack T3 = new ItemStack(Itemsss.MagnetRing);
		ItemStack T4 = new ItemStack(Itemsss.MagnetRing);
		ItemStack T5 = new ItemStack(Itemsss.MagnetRing);
		ItemStack T6 = new ItemStack(Itemsss.MagnetRing);
		
		T1.setTagCompound(new NBTTagCompound());
		T2.setTagCompound(new NBTTagCompound());
		T3.setTagCompound(new NBTTagCompound());
		T4.setTagCompound(new NBTTagCompound());
		T5.setTagCompound(new NBTTagCompound());
		T6.setTagCompound(new NBTTagCompound());
		
		T1.getTagCompound().setInteger("teir", 1);
		T1.getTagCompound().setDouble("range", 2.5);
	
		T2.getTagCompound().setInteger("teir", 2);
		T2.getTagCompound().setDouble("range", 3.5);
	
		T3.getTagCompound().setInteger("teir", 3);
		T3.getTagCompound().setDouble("range", 4.5);
	
		T4.getTagCompound().setInteger("teir", 4);
		T4.getTagCompound().setDouble("range", 5.5);
	
		T5.getTagCompound().setInteger("teir", 5);
		T5.getTagCompound().setDouble("range", 6.5);
	
		T6.getTagCompound().setInteger("teir", 6);
		T6.getTagCompound().setDouble("range", 12.5);
		
		GameRegistry.addShapelessRecipe(T1, Itemsss.MagnetRing, Itemsss.ItemMagnet);
		GameRegistry.addShapelessRecipe(T2, Itemsss.MagnetRing, Itemsss.ItemMagnetT2);
		GameRegistry.addShapelessRecipe(T3, Itemsss.MagnetRing, Itemsss.ItemMagnetT3);
		GameRegistry.addShapelessRecipe(T4, Itemsss.MagnetRing, Itemsss.ItemMagnetT4);
		GameRegistry.addShapelessRecipe(T5, Itemsss.MagnetRing, Itemsss.ItemMagnetT5);
		GameRegistry.addShapelessRecipe(T6, Itemsss.MagnetRing, Itemsss.ItemMagnetT6);
	}
	private static void addSoulAndDemonSwordRecipes()
	{
		ItemStack zombiesoulsword = new ItemStack(Itemsss.SkeletonSword);
		ItemStack skeletonsoulsword = new ItemStack(Itemsss.SkeletonSword);
		ItemStack creepersoulsword = new ItemStack(Itemsss.SkeletonSword);
		ItemStack endermansoulsword = new ItemStack(Itemsss.SkeletonSword);
		
		zombiesoulsword.setTagCompound(new NBTTagCompound());
		skeletonsoulsword.setTagCompound(new NBTTagCompound());
		creepersoulsword.setTagCompound(new NBTTagCompound());
		endermansoulsword.setTagCompound(new NBTTagCompound());
		
		zombiesoulsword.getTagCompound().setString("mob", "Zombie");
		skeletonsoulsword.getTagCompound().setString("mob", "Skeleton");
		creepersoulsword.getTagCompound().setString("mob", "Creeper");
		endermansoulsword.getTagCompound().setString("mob", "Enderman");
		
		zombiesoulsword.getTagCompound().setInteger("kills", 0);
		skeletonsoulsword.getTagCompound().setInteger("kills", 0);
		creepersoulsword.getTagCompound().setInteger("kills", 0);
		endermansoulsword.getTagCompound().setInteger("kills", 0);
		
		GameRegistry.addRecipe(zombiesoulsword, " y ", "yxy", "aza", 'x', Items.IRON_SWORD, 'y', Items.ROTTEN_FLESH, 'a', Items.CARROT, 'z', Items.DIAMOND);
		GameRegistry.addRecipe(skeletonsoulsword, " y ", "yxy", "aza", 'x', Items.IRON_SWORD, 'y', Items.BONE, 'a', Items.ARROW, 'z', Items.DIAMOND);
		GameRegistry.addRecipe(creepersoulsword, " y ", "yxy", "aza", 'x', Items.IRON_SWORD, 'y', Items.GUNPOWDER, 'a', Blocks.TNT, 'z', Items.DIAMOND);
		GameRegistry.addRecipe(endermansoulsword, " y ", "yxy", "aza", 'x', Items.IRON_SWORD, 'y', Items.ENDER_EYE, 'a', Items.ENDER_PEARL, 'z', Items.DIAMOND);
	}
	
	/**
	 * Called in most of my Custom Crafting classes about the pick.
	 * @param inv
	 * @return
	 */
	public static ItemStack getPick(InventoryCrafting inv)
	{
		ItemStack item = null;
		for(int i=0; i < inv.getSizeInventory(); ++i)
		{
			ItemStack stack = inv.getStackInSlot(i);
			if (stack != null && stack.getItem() == Itemsss.LevelPick && stack.getTagCompound() != null) 
			{
				item=stack;
			}
		}
		return item;
		
	}
}
