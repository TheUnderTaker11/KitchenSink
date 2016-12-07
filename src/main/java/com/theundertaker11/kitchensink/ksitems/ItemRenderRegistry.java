package com.theundertaker11.kitchensink.ksitems;

import com.theundertaker11.kitchensink.Refernce;
import com.theundertaker11.kitchensink.ksblocks.KSBlocks;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;

public class ItemRenderRegistry {
	public static void RenderItems(){
		reg(Itemsss.angelAlloy);
		reg(Itemsss.blessedSword);
		reg(Itemsss.blessedPick);
		reg(Itemsss.blessedHoe);
		reg(Itemsss.blessedAxe);
		reg(Itemsss.blessedShovel);
		reg(Itemsss.obsidianPlate);
		reg(Itemsss.godsTool);
		reg(Itemsss.deathsSythe);
		reg(Itemsss.deathNugget);
	    reg(Itemsss.deathIngot);
	    reg(Itemsss.compDiamond);
	    reg(Itemsss.diamondPlate);
	    reg(Itemsss.blessedRock);
	    reg(Itemsss.netherStarPlate);
	    reg(Itemsss.godEssence);
	    reg(Itemsss.ItemMagnet);
	    reg(Itemsss.ItemMagnetT2);
	    reg(Itemsss.ItemMagnetT3);
	    reg(Itemsss.ItemMagnetT4);
	    reg(Itemsss.ItemMagnetT5);
	    reg(Itemsss.ItemMagnetT6);
	    reg(Itemsss.xpItem);
	    reg(Itemsss.SkeletonSword);
	    reg(Itemsss.DemonicSword);
	    reg(Itemsss.DeathHand);
	    reg(Itemsss.HealthTPitem);
	    reg(Itemsss.TPitem);
	    reg(Itemsss.ProtectionCharm);
	    reg(Itemsss.MagnetRing);
	    
	    regWithMeta(Itemsss.ItemMagnet, 1);
	    regWithMeta(Itemsss.ItemMagnetT2, 1);
	    regWithMeta(Itemsss.ItemMagnetT3, 1);
	    regWithMeta(Itemsss.ItemMagnetT4, 1);
	    regWithMeta(Itemsss.ItemMagnetT5, 1);
	    regWithMeta(Itemsss.ItemMagnetT6, 1);
	    
	    regBlock(KSBlocks.BlackBlock);
	    regBlock(KSBlocks.BlueBlock);
	    regBlock(KSBlocks.BrownBlock);
	    regBlock(KSBlocks.GreenBlock);
	    regBlock(KSBlocks.OrangeBlock);
	    regBlock(KSBlocks.PurpleBlock);
	    regBlock(KSBlocks.RedBlock);
	    regBlock(KSBlocks.WhiteBlock);
	    regBlock(KSBlocks.YellowBlock);
	    regBlock(KSBlocks.BabyBlueBlock);
	    
	    regWithMetaAndName(Itemsss.Wand, 0, "Wand0");
	    regWithMetaAndName(Itemsss.Wand, 1, "Wand0");
	    regWithMetaAndName(Itemsss.Wand, 2, "Wand0");
	    regWithMetaAndName(Itemsss.Wand, 3, "Wand0");
	    
	    for(int i=0;i<8;++i)
	    {
	    	String name = "LevelPick" + i;
	    	regWithMetaAndName(Itemsss.LevelPick, i, name);
	    }
	   
	}
	//Registers the item if no meta data/first meta data(0)
	public static void reg(Item item) {
	    Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
	    .register(item, 0, new ModelResourceLocation(Refernce.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}

	public static void regWithMeta(Item item, int meta) {
	    Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
	    .register(item, meta, new ModelResourceLocation(Refernce.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
	
	public static void regWithMetaAndName(Item item, int meta, String name) {
	    Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
	    .register(item, meta, new ModelResourceLocation(Refernce.MODID + ":" + name, "inventory"));
	}

	public static void regBlock(Block block) {
	    Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
	    .register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(Refernce.MODID + ":" + block.getUnlocalizedName().substring(5), "inventory"));
	}
}
