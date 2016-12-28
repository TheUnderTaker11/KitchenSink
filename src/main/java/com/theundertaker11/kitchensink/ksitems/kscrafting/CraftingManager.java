package com.theundertaker11.kitchensink.ksitems.kscrafting;

import com.theundertaker11.kitchensink.KitchenSink;
import com.theundertaker11.kitchensink.ksblocks.KSBlocks;
import com.theundertaker11.kitchensink.ksitems.Itemsss;
import com.theundertaker11.kitchensink.ksitems.xpItem;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JEIPlugin;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class CraftingManager{
	public static void Crecipes(){
		////shaped
		//items
		GameRegistry.addRecipe(new ItemStack(Itemsss.obsidianPlate), "xxx", "xxx", "xxx", 'x', Blocks.OBSIDIAN);
		GameRegistry.addRecipe(new ItemStack(Itemsss.deathIngot), "xxx", "xxx", "xxx", 'x', Itemsss.deathNugget);
		GameRegistry.addRecipe(new ItemStack(Itemsss.diamondPlate), "xx", "xx", 'x', Itemsss.compDiamond);
		GameRegistry.addRecipe(new ItemStack(Itemsss.netherStarPlate), "xx", "xx", 'x', Items.NETHER_STAR);
		GameRegistry.addRecipe(new ItemStack(Itemsss.godEssence), "xax", "yzy", "xax", 'x', Itemsss.netherStarPlate, 'y', Itemsss.deathNugget, 'z', Items.WHEAT_SEEDS, 'a', Itemsss.obsidianPlate);
		GameRegistry.addRecipe(new ItemStack(Itemsss.angelAlloy), "axa", "xyx", "aza", 'z', Itemsss.diamondPlate, 'y', Itemsss.netherStarPlate, 'x', Itemsss.compDiamond, 'a', Itemsss.obsidianPlate);
		GameRegistry.addRecipe(new ItemStack(Itemsss.ItemMagnetT2), " x ", "xyx", " x ", 'x', Itemsss.ItemMagnet, 'y', Itemsss.obsidianPlate);
		GameRegistry.addRecipe(new ItemStack(Itemsss.ItemMagnetT3), " x ", "xyx", " x ", 'x', Itemsss.ItemMagnetT2, 'y', Items.DIAMOND);
		GameRegistry.addRecipe(new ItemStack(Itemsss.ItemMagnetT4), " x ", "xyx", " x ", 'x', Itemsss.ItemMagnetT3, 'y', Itemsss.compDiamond);
		GameRegistry.addRecipe(new ItemStack(Itemsss.ItemMagnetT5), " x ", "xyx", " x ", 'x', Itemsss.ItemMagnetT4, 'y', Itemsss.diamondPlate);
		GameRegistry.addRecipe(new ItemStack(Itemsss.ItemMagnetT6), " x ", "xyx", " x ", 'x', Itemsss.ItemMagnetT5, 'y', Itemsss.angelAlloy);
		GameRegistry.addRecipe(new ItemStack(Itemsss.blessedRock), " x ", "xyx", " x ", 'x', Blocks.COBBLESTONE, 'y', Itemsss.angelAlloy);
		GameRegistry.addRecipe(new ItemStack(Itemsss.TPitem), "zzz", "xyx", "zzz", 'x', Itemsss.angelAlloy, 'y', Itemsss.blessedRock, 'z', Items.ENDER_EYE);
		GameRegistry.addRecipe(new ItemStack(Itemsss.HealthTPitem), "xzx", "aya", "xzx", 'x', Itemsss.netherStarPlate, 'y', Itemsss.TPitem, 'z', Itemsss.angelAlloy, 'a', Items.END_CRYSTAL);
		GameRegistry.addRecipe(new ItemStack(KSBlocks.TrashChest), " x ", "xyx", " x ", 'x', Blocks.COBBLESTONE, 'y', Blocks.CHEST);
		if(KitchenSink.enableHealthStation)
		{
		IRecipe healingblock = new ShapedOreRecipe(new ItemStack(KSBlocks.HealingBlock),"zxz", "xyx", "zxz", 'x', "dyeRed", 'y', Itemsss.godEssence, 'z', Itemsss.angelAlloy);
		GameRegistry.addRecipe(healingblock);
		}
		if(KitchenSink.enableProtectionCharm)
		{
			GameRegistry.addRecipe(new ItemStack(Itemsss.ProtectionCharm), "xzx", "zyz", "xzx", 'x', Itemsss.obsidianPlate, 'y', Itemsss.godEssence, 'z', Itemsss.angelAlloy);
		}
		//tools
		GameRegistry.addRecipe(new ItemStack(Itemsss.blessedPick), "xxx", " y ", " y ", 'x', Itemsss.angelAlloy, 'y', Itemsss.obsidianPlate);
		GameRegistry.addRecipe(new ItemStack(Itemsss.blessedShovel), " x ", " y ", " y ", 'x', Itemsss.angelAlloy, 'y', Itemsss.obsidianPlate);
		GameRegistry.addRecipe(new ItemStack(Itemsss.blessedSword), " x ", "yxy", "xyx", 'x', Itemsss.angelAlloy, 'y', Itemsss.obsidianPlate);
		GameRegistry.addRecipe(new ItemStack(Itemsss.blessedAxe), " xx", " yx", " y ", 'x', Itemsss.angelAlloy, 'y', Itemsss.obsidianPlate);
		GameRegistry.addRecipe(new ItemStack(Itemsss.blessedAxe), "xx ", "xy ", " y ", 'x', Itemsss.angelAlloy, 'y', Itemsss.obsidianPlate);
		GameRegistry.addRecipe(new ItemStack(Itemsss.blessedHoe), "xx ", " y ", " y ", 'x', Itemsss.angelAlloy, 'y', Itemsss.obsidianPlate);
		GameRegistry.addRecipe(new ItemStack(Itemsss.blessedHoe), " xx", " y ", " y ", 'x', Itemsss.angelAlloy, 'y', Itemsss.obsidianPlate);
		if(KitchenSink.enableGodsTool)
		{
		GameRegistry.addRecipe(new ItemStack(Itemsss.godsTool), "   ", "ayb", "xzx", 'x', Itemsss.godEssence, 'y', Itemsss.blessedPick, 'a', Itemsss.blessedShovel, 'b', Itemsss.blessedAxe, 'z', Itemsss.blessedHoe);
		}
		if(KitchenSink.enableDeathHand)
		{
			GameRegistry.addRecipe(new ItemStack(Itemsss.DeathHand), " z ", "yxy", " z ", 'z',Itemsss.deathsSythe, 'y', Itemsss.godEssence, 'x', Itemsss.deathIngot);
		}
		
		//Blocks
		//All these use oreDictionary dyes now
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
		IRecipe magnetring = new ShapedOreRecipe(new ItemStack(Itemsss.MagnetRing), new Object[] {"yxy", "x x", "yxy", 'x', "ingotIron", 'y', "dustRedstone"});
		GameRegistry.addRecipe(magnetring);
		IRecipe compdiamond = new ShapedOreRecipe(new ItemStack(Itemsss.compDiamond),new Object[] {"xx", "xx", 'x', "gemDiamond"});
		GameRegistry.addRecipe(compdiamond);
		IRecipe quarry = new ShapedOreRecipe(new ItemStack(KSBlocks.QuarryBlock),new Object[] {"aaa", "xyx", "aza", 'x', Blocks.PISTON, 'y', Items.DIAMOND_PICKAXE, 'z', "dustRedstone", 'a', "stone"});
		GameRegistry.addRecipe(quarry);
		IRecipe itemmagnett1 = new ShapedOreRecipe(new ItemStack(Itemsss.ItemMagnet),new Object[] {"x x", "x x", "xyx", 'x', "ingotIron", 'y', "gemDiamond"});
		GameRegistry.addRecipe(itemmagnett1);
		IRecipe xpitem = new ShapedOreRecipe(new ItemStack(Itemsss.xpItem), " z ", "xyx", "xxx", 'x', Items.ROTTEN_FLESH, 'y', Items.GLASS_BOTTLE, 'z', "gemDiamond");
		GameRegistry.addRecipe(xpitem);
		
		////shapeless
		GameRegistry.addShapelessRecipe(new ItemStack(Itemsss.deathNugget, 9), Itemsss.deathIngot);
		GameRegistry.addShapelessRecipe(new ItemStack(Itemsss.deathNugget, 1), Items.NETHER_STAR);
		
		
		//Swords and Lapis pick crafting
		ItemStack zombieknife = new ItemStack(Itemsss.SkeletonSword);
		ItemStack skeletonknife = new ItemStack(Itemsss.SkeletonSword);
		ItemStack creeperknife = new ItemStack(Itemsss.SkeletonSword);
		ItemStack endermanknife = new ItemStack(Itemsss.SkeletonSword);
		ItemStack levelpick = new ItemStack(Itemsss.LevelPick);
		ItemStack deathhand = new ItemStack(Itemsss.DeathHand);
		
		
		zombieknife.setTagCompound(new NBTTagCompound());
		skeletonknife.setTagCompound(new NBTTagCompound());
		creeperknife.setTagCompound(new NBTTagCompound());
		endermanknife.setTagCompound(new NBTTagCompound());
		levelpick.setTagCompound(new NBTTagCompound());
		deathhand.setTagCompound(new NBTTagCompound());
		
		
		zombieknife.getTagCompound().setString("mob", "zombie");
		skeletonknife.getTagCompound().setString("mob", "skeleton");
		creeperknife.getTagCompound().setString("mob", "creeper");
		endermanknife.getTagCompound().setString("mob", "enderman");
		if(KitchenSink.debugMode)
		{
			zombieknife.getTagCompound().setInteger("kills", 50);
			skeletonknife.getTagCompound().setInteger("kills", 50);
			creeperknife.getTagCompound().setInteger("kills", 50);
			endermanknife.getTagCompound().setInteger("kills", 50);
		}
		else
		{
			zombieknife.getTagCompound().setInteger("kills", 0);
			skeletonknife.getTagCompound().setInteger("kills", 0);
			creeperknife.getTagCompound().setInteger("kills", 0);
			endermanknife.getTagCompound().setInteger("kills", 0);
		}
		NBTTagCompound lvlpick = levelpick.getTagCompound();
		lvlpick.setInteger("dur", 250);
		lvlpick.setInteger("maxdur", 250);
		lvlpick.setInteger("harvestlevel", 0);
		lvlpick.setInteger("picklevel", 0);
		lvlpick.setFloat("pickspeed", 3F);
		lvlpick.setInteger("pickxp", 0);
		lvlpick.setInteger("xptonextlevel", 150);
		lvlpick.setBoolean("allowmagnet", false);
		lvlpick.setBoolean("magnetactive", false);
		lvlpick.setInteger("xpfromblocks", 0);
		lvlpick.setBoolean("allowxp", false);
		lvlpick.setBoolean("autorepair", false);
		lvlpick.setInteger("texture", 0);
		
		deathhand.getTagCompound().setBoolean("active", false);
		deathhand.getTagCompound().setString("owner", "Will be set after this is crafted, ignore below message.");
		deathhand.addEnchantment(Enchantments.LOOTING, 5);
		
		
		//NBT results of a recipe.//////////////////////////////
		GameRegistry.addRecipe(zombieknife, " y ", "yxy", "aza", 'x', Itemsss.angelAlloy, 'y', Items.ROTTEN_FLESH, 'a', Items.CARROT, 'z', Itemsss.deathNugget);
		GameRegistry.addRecipe(skeletonknife, " y ", "yxy", "aza", 'x', Itemsss.angelAlloy, 'y', Items.BONE, 'a', Items.ARROW, 'z', Itemsss.deathNugget);
		GameRegistry.addRecipe(creeperknife, " y ", "yxy", "aza", 'x', Itemsss.angelAlloy, 'y', Items.GUNPOWDER, 'a', Blocks.TNT, 'z', Itemsss.deathNugget);
		GameRegistry.addRecipe(endermanknife, " y ", "yxy", "aza", 'x', Itemsss.angelAlloy, 'y', Items.ENDER_EYE, 'a', Items.ENDER_EYE, 'z', Itemsss.deathNugget);
		
		GameRegistry.addRecipe(levelpick, " z ", " x ", " y ", 'x',Items.IRON_PICKAXE, 'y', Items.DIAMOND, 'z', Blocks.LAPIS_BLOCK);
		
		GameRegistry.addRecipe(deathhand, "yyy", "yxy", "yyy", 'x',Itemsss.DeathHand, 'y', Blocks.EMERALD_ORE);
		
		
		//Start Magnet Ring Crafting//////////////////////////////
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
		//End magnet ring crafting//////////////////////////////
		
		//Start Demon Sword crafting.//////////////////////////////
		ItemStack zombie50kills = new ItemStack(Itemsss.SkeletonSword);
		ItemStack skeleton50kills = new ItemStack(Itemsss.SkeletonSword);
		ItemStack creeper50kills = new ItemStack(Itemsss.SkeletonSword);
		ItemStack demonsword = new ItemStack(Itemsss.DemonicSword);
		
		zombie50kills.setTagCompound(new NBTTagCompound());
		skeleton50kills.setTagCompound(new NBTTagCompound());
		creeper50kills.setTagCompound(new NBTTagCompound());
		demonsword.setTagCompound(new NBTTagCompound());
		
		zombie50kills.getTagCompound().setString("mob", "zombie");
		skeleton50kills.getTagCompound().setString("mob", "skeleton");
		creeper50kills.getTagCompound().setString("mob", "creeper");
		zombie50kills.getTagCompound().setInteger("kills", 50);
		skeleton50kills.getTagCompound().setInteger("kills", 50);
		creeper50kills.getTagCompound().setInteger("kills", 50);
		demonsword.getTagCompound().setInteger("zombiekills", 0);
		demonsword.getTagCompound().setInteger("skeletonkills", 0);
		demonsword.getTagCompound().setInteger("creeperkills", 0);
		demonsword.getTagCompound().setInteger("endermankills", 0);
		
		GameRegistry.addRecipe(demonsword, "xxx", "yxz", "aba", 'x', new ItemStack(Itemsss.deathIngot,1,1),'a', Itemsss.netherStarPlate, 'y', zombie50kills, 'b', skeleton50kills, 'z', creeper50kills);
		//End Demon Sword Crafting/////////////////////////////////
		
		//Start Death Scythe crafting//////////////////////////////
		ItemStack demonsword100kills = new ItemStack(Itemsss.DemonicSword);
		demonsword100kills.setTagCompound(new NBTTagCompound());
		NBTTagCompound demontag = demonsword100kills.getTagCompound();
		demontag.setInteger("zombiekills", 100);
		demontag.setInteger("skeletonkills", 100);
		demontag.setInteger("creeperkills", 100);
		demontag.setInteger("endermankills", 100);
		
		GameRegistry.addRecipe(new ItemStack(Itemsss.deathsSythe), "yxx", " zx", " xx", 'x', new ItemStack(Itemsss.deathIngot,1,1), 'y', Itemsss.netherStarPlate, 'z', demonsword100kills);
		//End Death Scythe crafting//////////////////////////////
		
		//Start Lapis Tool Dummy Recipes//////////////////////////////
		ItemStack levelPickRepairResult = new ItemStack(Itemsss.LevelPick);
		ItemStack levelPickMagnetResult = new ItemStack(Itemsss.LevelPick);
		ItemStack levelPickSpeedResult = new ItemStack(Itemsss.LevelPick);
		ItemStack levelPickXPResult = new ItemStack(Itemsss.LevelPick);
		
		ItemStack levelPickRepairStart = new ItemStack(Itemsss.LevelPick);
		ItemStack levelPickStart = new ItemStack(Itemsss.LevelPick);
		//I have a recipe registered so Item Damage 8 looks like a regular lapis pick
		levelPickStart.setItemDamage(8);
		levelPickRepairStart.setItemDamage(8);
		levelPickRepairResult.setTagCompound(new NBTTagCompound());
		levelPickMagnetResult.setTagCompound(new NBTTagCompound());
		levelPickSpeedResult.setTagCompound(new NBTTagCompound());
		levelPickXPResult.setTagCompound(new NBTTagCompound());
		levelPickRepairStart.setTagCompound(new NBTTagCompound());
		
		levelPickRepairStart.getTagCompound().setInteger("dur", 150);
		levelPickRepairStart.getTagCompound().setInteger("maxdur", 250);
		//These values are used when I set my tooltip to show what I want the player to see
		levelPickRepairResult.getTagCompound().setBoolean("dummyRepair", true);
		levelPickMagnetResult.getTagCompound().setBoolean("dummyMagnet", true);
		levelPickMagnetResult.setItemDamage(2);
		
		levelPickSpeedResult.getTagCompound().setBoolean("dummySpeed", true);
		
		levelPickXPResult.getTagCompound().setBoolean("dummyXP", true);
		levelPickXPResult.setItemDamage(1);
		
		GameRegistry.addRecipe(levelPickRepairResult, "   ", " xy", "   ", 'x', levelPickRepairStart, 'y',Items.IRON_INGOT);
		GameRegistry.addRecipe(levelPickMagnetResult, "   ", " xy", "   ", 'x', levelPickStart, 'y', Itemsss.ItemMagnetT6);
		GameRegistry.addRecipe(levelPickSpeedResult, " y ", "yxy", " y ", 'x', levelPickStart, 'y', Blocks.REDSTONE_BLOCK);
		GameRegistry.addRecipe(levelPickXPResult, "yyy", "yxy", "yyy", 'x', levelPickStart, 'y', Itemsss.xpItem);
		//End Lapis Tool Dummy Recipes//////////////////////////////
		
		//These are what do the ACTUAL lapis tool recipes
		GameRegistry.addRecipe(new CustomLevelToolFixing());
		GameRegistry.addRecipe(new CustomLevelToolSpeed());
		GameRegistry.addRecipe(new CustomLevelToolFixingx9());
		GameRegistry.addRecipe(new CustomLevelToolMagnet());
		GameRegistry.addRecipe(new CustomLevelToolXP());
		GameRegistry.addRecipe(new CustomLevelToolAutoRepair());
		//And actual death scythe/demon sword recipes
		GameRegistry.addRecipe(new CustomDeathCrafting());
		GameRegistry.addRecipe(new CustomSwordCrafting());
		
		
		
		//smelting
		}
	

}
