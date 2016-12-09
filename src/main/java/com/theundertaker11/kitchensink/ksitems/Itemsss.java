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
	
	public static Item DemonicSword;
	public static Item SkeletonSword;
	
	//public static Item Wand;
	public static Item LevelPick;

	
	public static void itemprops(){
		GameRegistry.register(obsidianPlate = new angelAlloy("obsidianPlate"));
		GameRegistry.register(angelAlloy = new angelAlloy("angelAlloy"));
		GameRegistry.register(deathNugget = new angelAlloy("deathNugget"));
		GameRegistry.register(deathIngot = new deathIngot("deathIngot"));
		GameRegistry.register(compDiamond = new angelAlloy("compDiamond"));
		GameRegistry.register(diamondPlate = new angelAlloy("diamondPlate"));
		GameRegistry.register(blessedRock = new blessedRock("blessedRock"));
		GameRegistry.register(netherStarPlate = new deathIngot("netherStarPlate"));
		GameRegistry.register(godEssence = new deathIngot("godEssence"));
		GameRegistry.register(ItemMagnet = new ItemMagnetT6("ItemMagnet", 2.5,1));
		GameRegistry.register(ItemMagnetT2 = new ItemMagnetT6("ItemMagnetT2", 3.5,2));
		GameRegistry.register(ItemMagnetT3 = new ItemMagnetT6("ItemMagnetT3", 4.5,3));
		GameRegistry.register(ItemMagnetT4 = new ItemMagnetT6("ItemMagnetT4", 5.5,4));
		GameRegistry.register(ItemMagnetT5 = new ItemMagnetT6("ItemMagnetT5", 6.5,5));
		GameRegistry.register(ItemMagnetT6 = new ItemMagnetT6("ItemMagnetT6", 12.5,6));
		GameRegistry.register(MagnetRing = new MagnetRing("MagnetRing"));
		GameRegistry.register(HealthTPitem = new HealthTPitem("HealthTPitem"));
		GameRegistry.register(TPitem = new TPitem("TPitem"));
		GameRegistry.register(xpItem = new xpItem("xpItem"));
		GameRegistry.register(ProtectionCharm = new ProtectionCharm("ProtectionCharm"));
		
		//Tools
		GameRegistry.register(blessedSword = new blessedSword("blessedSword", blessed_mat));
		GameRegistry.register(blessedShovel = new blessedShovel("blessedShovel", blessed_mat));
		GameRegistry.register(blessedAxe = new blessedAxe(blessed_mat));
		GameRegistry.register(blessedHoe = new blessedHoe("blessedHoe", blessed_mat));
		GameRegistry.register(blessedPick = new blessedPick("blessedPick", blessed_mat));
		GameRegistry.register(godsTool = new godsTool("godsTool", god_mat));
		GameRegistry.register(deathsSythe = new deathsSythe("deathsSythe", death_mat));
		GameRegistry.register(SkeletonSword = new SkeletonSword("SkeletonSword", low_mat));
		GameRegistry.register(DemonicSword = new DemonicSword("DemonicSword", low_mat2));
		GameRegistry.register(LevelPick = new LevelPick("LevelPick", low_mat2));
		GameRegistry.register(DeathHand = new DeathHand("DeathHand", low_mat));
		
		//waht
		//GameRegistry.register(Wand = new Wand("Wand"));	
		
	}


}
