package com.theundertaker11.kitchensink.ksitems.kscrafting;

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

public class CraftingManager{
	public static void Crecipes(){
		////shaped
		//items
		GameRegistry.addRecipe(new ItemStack(Itemsss.obsidianPlate), "xxx", "xxx", "xxx", 'x', Blocks.OBSIDIAN);
		GameRegistry.addRecipe(new ItemStack(Itemsss.deathIngot), "xxx", "xxx", "xxx", 'x', Itemsss.deathNugget);
		GameRegistry.addRecipe(new ItemStack(Itemsss.diamondPlate), "xx", "xx", 'x', Itemsss.compDiamond);
		GameRegistry.addRecipe(new ItemStack(Itemsss.compDiamond), "xx", "xx", 'x', Items.DIAMOND);
		GameRegistry.addRecipe(new ItemStack(Itemsss.netherStarPlate), "xx", "xx", 'x', Items.NETHER_STAR);
		GameRegistry.addRecipe(new ItemStack(Itemsss.godEssence), "xax", "yzy", "xax", 'x', Itemsss.netherStarPlate, 'y', Itemsss.deathNugget, 'z', Items.WHEAT_SEEDS, 'a', Itemsss.obsidianPlate);
		GameRegistry.addRecipe(new ItemStack(Itemsss.angelAlloy), "axa", "xyx", "aza", 'z', Itemsss.diamondPlate, 'y', Itemsss.netherStarPlate, 'x', Itemsss.compDiamond, 'a', Itemsss.obsidianPlate);
		GameRegistry.addRecipe(new ItemStack(Itemsss.ItemMagnet), "x x", "x x", "xyx", 'x', Items.IRON_INGOT, 'y', Items.DIAMOND);
		GameRegistry.addRecipe(new ItemStack(Itemsss.ItemMagnetT2), " x ", "xyx", " x ", 'x', Itemsss.ItemMagnet, 'y', Itemsss.obsidianPlate);
		GameRegistry.addRecipe(new ItemStack(Itemsss.ItemMagnetT3), " x ", "xyx", " x ", 'x', Itemsss.ItemMagnetT2, 'y', Items.DIAMOND);
		GameRegistry.addRecipe(new ItemStack(Itemsss.ItemMagnetT4), " x ", "xyx", " x ", 'x', Itemsss.ItemMagnetT3, 'y', Itemsss.compDiamond);
		GameRegistry.addRecipe(new ItemStack(Itemsss.ItemMagnetT5), " x ", "xyx", " x ", 'x', Itemsss.ItemMagnetT4, 'y', Itemsss.diamondPlate);
		GameRegistry.addRecipe(new ItemStack(Itemsss.ItemMagnetT6), " x ", "xyx", " x ", 'x', Itemsss.ItemMagnetT5, 'y', Itemsss.angelAlloy);
		GameRegistry.addRecipe(new ItemStack(Itemsss.blessedRock), " x ", "xyx", " x ", 'x', Blocks.COBBLESTONE, 'y', Itemsss.angelAlloy);
		GameRegistry.addRecipe(new ItemStack(Itemsss.xpItem), " z ", "xyx", "xxx", 'x', Items.ROTTEN_FLESH, 'y', Items.GLASS_BOTTLE, 'z', Items.DIAMOND);
		GameRegistry.addRecipe(new ItemStack(Itemsss.TPitem), "zzz", "xyx", "zzz", 'x', Itemsss.angelAlloy, 'y', Itemsss.blessedRock, 'z', Items.ENDER_EYE);
		GameRegistry.addRecipe(new ItemStack(Itemsss.ProtectionCharm), "xzx", "zyz", "xzx", 'x', Itemsss.obsidianPlate, 'y', Itemsss.godEssence, 'z', Itemsss.angelAlloy);
		GameRegistry.addRecipe(new ItemStack(Itemsss.HealthTPitem), "xzx", "aya", "xzx", 'x', Itemsss.netherStarPlate, 'y', Itemsss.TPitem, 'z', Itemsss.angelAlloy, 'a', Items.END_CRYSTAL);
		GameRegistry.addRecipe(new ItemStack(Itemsss.MagnetRing), "yxy", "x x", "yxy", 'x', Items.IRON_INGOT, 'y', Items.REDSTONE);
		//tools
		GameRegistry.addRecipe(new ItemStack(Itemsss.blessedPick), "xxx", " y ", " y ", 'x', Itemsss.angelAlloy, 'y', Itemsss.obsidianPlate);
		GameRegistry.addRecipe(new ItemStack(Itemsss.blessedShovel), " x ", " y ", " y ", 'x', Itemsss.angelAlloy, 'y', Itemsss.obsidianPlate);
		GameRegistry.addRecipe(new ItemStack(Itemsss.blessedSword), " x ", "yxy", "xyx", 'x', Itemsss.angelAlloy, 'y', Itemsss.obsidianPlate);
		GameRegistry.addRecipe(new ItemStack(Itemsss.blessedAxe), " xx", " yx", " y ", 'x', Itemsss.angelAlloy, 'y', Itemsss.obsidianPlate);
		GameRegistry.addRecipe(new ItemStack(Itemsss.blessedAxe), "xx ", "xy ", " y ", 'x', Itemsss.angelAlloy, 'y', Itemsss.obsidianPlate);
		GameRegistry.addRecipe(new ItemStack(Itemsss.blessedHoe), "xx ", " y ", " y ", 'x', Itemsss.angelAlloy, 'y', Itemsss.obsidianPlate);
		GameRegistry.addRecipe(new ItemStack(Itemsss.blessedHoe), " xx", " y ", " y ", 'x', Itemsss.angelAlloy, 'y', Itemsss.obsidianPlate);
		GameRegistry.addRecipe(new ItemStack(Itemsss.blessedHoe), " xx", " y ", " y ", 'x', Itemsss.angelAlloy, 'y', Itemsss.obsidianPlate);
		GameRegistry.addRecipe(new ItemStack(Itemsss.godsTool), "   ", "ayb", "xzx", 'x', Itemsss.godEssence, 'y', Itemsss.blessedPick, 'a', Itemsss.blessedShovel, 'b', Itemsss.blessedAxe, 'z', Itemsss.blessedHoe);
		GameRegistry.addRecipe(new ItemStack(Itemsss.DeathHand), " z ", "yxy", " z ", 'z',Itemsss.deathsSythe, 'y', Itemsss.godEssence, 'x', Itemsss.deathIngot);
		
		//Blocks
		GameRegistry.addRecipe(new ItemStack(KSBlocks.BlackBlock, 6), " y ", "xxx", "xxx", 'x', Blocks.COBBLESTONE, 'y', Items.DYE);
		GameRegistry.addRecipe(new ItemStack(KSBlocks.BabyBlueBlock, 6), " y ", "xxx", "xxx", 'x', Blocks.COBBLESTONE, 'y', new ItemStack(Items.DYE, 1, 12));
		GameRegistry.addRecipe(new ItemStack(KSBlocks.BlueBlock, 6), " y ", "xxx", "xxx", 'x', Blocks.COBBLESTONE, 'y', new ItemStack(Items.DYE, 1, 4));
		GameRegistry.addRecipe(new ItemStack(KSBlocks.BrownBlock, 6), " y ", "xxx", "xxx", 'x', Blocks.COBBLESTONE, 'y', new ItemStack(Items.DYE, 1, 3));
		GameRegistry.addRecipe(new ItemStack(KSBlocks.GreenBlock, 6), " y ", "xxx", "xxx", 'x', Blocks.COBBLESTONE, 'y', new ItemStack(Items.DYE, 1, 2));
		GameRegistry.addRecipe(new ItemStack(KSBlocks.OrangeBlock, 6), " y ", "xxx", "xxx", 'x', Blocks.COBBLESTONE, 'y', new ItemStack(Items.DYE, 1, 14));
		GameRegistry.addRecipe(new ItemStack(KSBlocks.PurpleBlock, 6), " y ", "xxx", "xxx", 'x', Blocks.COBBLESTONE, 'y', new ItemStack(Items.DYE, 1, 5));
		GameRegistry.addRecipe(new ItemStack(KSBlocks.RedBlock, 6), " y ", "xxx", "xxx", 'x', Blocks.COBBLESTONE, 'y', new ItemStack(Items.DYE,1, 1));
		GameRegistry.addRecipe(new ItemStack(KSBlocks.WhiteBlock, 6), " y ", "xxx", "xxx", 'x', Blocks.COBBLESTONE, 'y', new ItemStack(Items.DYE, 1, 15));
		GameRegistry.addRecipe(new ItemStack(KSBlocks.YellowBlock, 6), " y ", "xxx", "xxx", 'x', Blocks.COBBLESTONE, 'y', new ItemStack(Items.DYE, 1, 11));
		
		
		////shapeless
		GameRegistry.addShapelessRecipe(new ItemStack(Itemsss.deathNugget, 9), new Object[] {Itemsss.deathIngot});		
		
		//GameRegistry.addRecipe(new ShapedOreRecipe(Item.bucketEmpty, true, new Object[]{
        //       "FF", Character.valueOf('F'), "ingotCopper"}));
		//The important part is the "ingotCopper", instead of using Itemsss.ingotCopper
		
		
		//NBT crafting
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
		
		
		levelpick.getTagCompound().setInteger("dur", 250);
		levelpick.getTagCompound().setInteger("maxdur", 250);
		levelpick.getTagCompound().setInteger("harvestlevel", 0);
		levelpick.getTagCompound().setInteger("picklevel", 0);
		levelpick.getTagCompound().setFloat("pickspeed", 3F);
		levelpick.getTagCompound().setInteger("pickxp", 0);
		levelpick.getTagCompound().setInteger("xptonextlevel", 150);
		levelpick.getTagCompound().setBoolean("allowmagnet", false);
		levelpick.getTagCompound().setBoolean("magnetactive", false);
		levelpick.getTagCompound().setInteger("xpfromblocks", 0);
		levelpick.getTagCompound().setBoolean("allowxp", false);
		levelpick.getTagCompound().setBoolean("autorepair", false);
		levelpick.getTagCompound().setInteger("texture", 0);
		
		deathhand.getTagCompound().setBoolean("active", false);
		deathhand.addEnchantment(Enchantments.LOOTING, 5);
		
		
		//NBT results of a recipe.
		GameRegistry.addRecipe(zombieknife, " y ", "yxy", "aza", 'x', Itemsss.angelAlloy, 'y', Items.ROTTEN_FLESH, 'a', Items.CARROT, 'z', Itemsss.deathNugget);
		GameRegistry.addRecipe(skeletonknife, " y ", "yxy", "aza", 'x', Itemsss.angelAlloy, 'y', Items.BONE, 'a', Items.ARROW, 'z', Itemsss.deathNugget);
		GameRegistry.addRecipe(creeperknife, " y ", "yxy", "aza", 'x', Itemsss.angelAlloy, 'y', Items.GUNPOWDER, 'a', Blocks.TNT, 'z', Itemsss.deathNugget);
		GameRegistry.addRecipe(endermanknife, " y ", "yxy", "aza", 'x', Itemsss.angelAlloy, 'y', Items.ENDER_EYE, 'a', Items.ENDER_EYE, 'z', Itemsss.deathNugget);
		
		GameRegistry.addRecipe(levelpick, " z ", " x ", " y ", 'x',Items.IRON_PICKAXE, 'y', Items.DIAMOND, 'z', Blocks.LAPIS_BLOCK);
		
		GameRegistry.addRecipe(deathhand, "yyy", "yxy", "yyy", 'x',Itemsss.DeathHand, 'y', Blocks.EMERALD_ORE);
		
		GameRegistry.addRecipe(new CustomSwordCrafting());
		GameRegistry.addRecipe(new CustomDeathCrafting());
		GameRegistry.addRecipe(new CustomLevelToolFixing());
		GameRegistry.addRecipe(new CustomLevelToolSpeed());
		GameRegistry.addRecipe(new CustomLevelToolFixingx9());
		GameRegistry.addRecipe(new CustomLevelToolMagnet());
		GameRegistry.addRecipe(new CustomLevelToolXP());
		GameRegistry.addRecipe(new CustomLevelToolAutoRepair());
		GameRegistry.addRecipe(new MagnetRingCrafting());
		
		//smelting
		}
	

}
