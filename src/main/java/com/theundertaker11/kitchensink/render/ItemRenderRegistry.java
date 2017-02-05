package com.theundertaker11.kitchensink.render;

import com.theundertaker11.kitchensink.Refernce;
import com.theundertaker11.kitchensink.ksblocks.KSBlocks;
import com.theundertaker11.kitchensink.ksitems.Itemsss;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;

public class ItemRenderRegistry {
	public static void RenderItems(){
	    
	    reg(Itemsss.SkeletonSword);
	    reg(Itemsss.DemonicSword);
	    reg(Itemsss.DeathHand);
	    reg(Itemsss.blessedSword);
		reg(Itemsss.blessedPick);
		reg(Itemsss.blessedHoe);
		reg(Itemsss.blessedAxe);
		reg(Itemsss.blessedShovel);
		reg(Itemsss.godsTool);
		reg(Itemsss.deathsSythe);
	    
	    regWithMeta(Itemsss.ItemMagnet, 1);
	    regWithMeta(Itemsss.ItemMagnetT2, 1);
	    regWithMeta(Itemsss.ItemMagnetT3, 1);
	    regWithMeta(Itemsss.ItemMagnetT4, 1);
	    regWithMeta(Itemsss.ItemMagnetT5, 1);
	    regWithMeta(Itemsss.ItemMagnetT6, 1);
	    regWithMeta(Itemsss.deathIngot, 1);

	    //regWithMetaAndName(Itemsss.Wand, 0, "Wand0");
	    //regWithMetaAndName(Itemsss.Wand, 1, "Wand0");
	    //regWithMetaAndName(Itemsss.Wand, 2, "Wand0");
	    //regWithMetaAndName(Itemsss.Wand, 3, "Wand0");
	    
	    for(int i=0;i<8;++i)
	    {
	    	String name = "LevelPick" + i;
	    	regWithMetaAndName(Itemsss.LevelPick, i, name);
	    }
	    //dummy item rendering(For recipes)
	    regWithMetaAndName(Itemsss.LevelPick, 8, "LevelPick0");
	    regWithMetaAndName(Itemsss.DemonicSword, -1, "DemonicSword");
	    
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
}
