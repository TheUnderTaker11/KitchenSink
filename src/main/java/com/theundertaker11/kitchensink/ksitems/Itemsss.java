package com.theundertaker11.kitchensink.ksitems;

import com.theundertaker11.kitchensink.CreativeTabKS;
import com.theundertaker11.kitchensink.KitchenSink;

import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class Itemsss
{
	
	//Materials
	public static ToolMaterial blessed_mat = EnumHelper.addToolMaterial("blessed_mat", 100, 1000, 12, 8, 20);
	public static ToolMaterial death_mat = EnumHelper.addToolMaterial("death_mat", 100, 10000, 10, 16, 40);
	public static ToolMaterial god_mat = EnumHelper.addToolMaterial("god_mat", 100, 10000, 10000, 14, 40);
	public static ToolMaterial low_mat = EnumHelper.addToolMaterial("low_mat", 1, 100, 10, 0, 20);
	public static ToolMaterial low_mat2 = EnumHelper.addToolMaterial("low_mat2", 1, 100, 10, 4, 30);
	
	//Tools
	public static Item blessedSword;
	public static Item blessedPick;
	public static Item blessedShovel;
	public static Item blessedAxe;
	public static Item blessedHoe;
	public static Item godsTool;
	public static Item deathsSythe;
	public static Item DeathHand;
	
	//Armor
	public static Item blessedHelmet;
	public static Item blessedChestplate;
	public static Item blessedLeggings;
	public static Item blessedBoots;
	public static Item deathHelmet;
	public static Item deathChestplate;
	public static Item deathLeggings;
	public static Item deathBoots;
	
	//items
	public static Item angelAlloy;
	public static Item obsidianPlate;
	public static Item diamondPlate;
	public static Item compDiamond;
	public static Item deathNugget;
	public static Item deathIngot;
	public static Item blessedRock;
	public static Item netherStarPlate;
	public static Item godEssence;
	public static Item ItemMagnet;
	public static Item ItemMagnetT2;
	public static Item ItemMagnetT3;
	public static Item ItemMagnetT4;
	public static Item ItemMagnetT5;
	public static Item ItemMagnetT6;
	public static Item MagnetRing;
	public static Item xpItem;
	public static Item HealthTPitem;
	public static Item TPitem;
	public static Item ProtectionCharm;
	public static Item speedForceRing;
	
	public static Item DemonicSword;
	public static Item SkeletonSword;
	
	//public static Item Wand;
	public static Item LevelPick;

	
	public static void itemprops(){
		obsidianPlate = register(new ItemBase("obsidianPlate"));
		angelAlloy = register(new ItemBase("angelAlloy"));
		deathNugget = register(new ItemBase("deathNugget"));
		deathIngot = register(new ItemBase("deathIngot", true));
		compDiamond = register(new ItemBase("compDiamond"));
		diamondPlate = register(new ItemBase("diamondPlate"));
		blessedRock = register(new blessedRock("blessedRock"));
		netherStarPlate = register(new ItemBase("netherStarPlate", true));
		godEssence = register(new ItemBase("godEssence", true));
		ItemMagnet = register(new ItemMagnetT6("ItemMagnet", 2.5));
		ItemMagnetT2 = register(new ItemMagnetT6("ItemMagnetT2", 3.5));
		ItemMagnetT3 = register(new ItemMagnetT6("ItemMagnetT3", 4.5));
		ItemMagnetT4 = register(new ItemMagnetT6("ItemMagnetT4", 5.5));
		ItemMagnetT5 = register(new ItemMagnetT6("ItemMagnetT5", 6.5));
		ItemMagnetT6 = register(new ItemMagnetT6("ItemMagnetT6", 12.5));
		MagnetRing = register(new MagnetRing("MagnetRing"));
		HealthTPitem = register(new HealthTPitem("HealthTPitem"));
		TPitem = register(new TPitem("TPitem"));
		xpItem = register(new xpItem("xpItem"));
		ProtectionCharm = register(new ProtectionCharm("ProtectionCharm"));
		speedForceRing = register(new SpeedForceRing("speedForceRing"));
		
		//Tools
		blessedSword = register(new blessedSword("blessedSword", blessed_mat));
		blessedShovel = register(new blessedShovel("blessedShovel", blessed_mat));
		blessedAxe = register(new blessedAxe(blessed_mat));
		blessedHoe = register(new blessedHoe("blessedHoe", blessed_mat));
		blessedPick = register(new blessedPick("blessedPick", blessed_mat));
		deathsSythe = register(new deathsSythe("deathsSythe", death_mat));
		SkeletonSword = register(new SkeletonSword("SkeletonSword", low_mat));
		DemonicSword = register(new DemonicSword("DemonicSword", low_mat2));
		LevelPick = register(new LevelPick("LevelPick", low_mat2));
		DeathHand = register(new DeathHand("DeathHand", low_mat));
		godsTool = register(new godsTool("godsTool", god_mat));
		
			
		//waht
		//Wand = register(new Wand("Wand"));	
		
	}
	private static <T extends Item> T register(T item) {
		GameRegistry.register(item);
		
		if(item instanceof ItemModelProvider) {
			((ItemModelProvider)item).registerItemModel(item);
		}
		
		return item;
	}


}
