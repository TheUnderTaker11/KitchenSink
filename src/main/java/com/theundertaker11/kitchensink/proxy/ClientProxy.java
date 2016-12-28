package com.theundertaker11.kitchensink.proxy;

import com.theundertaker11.kitchensink.Refernce;
import com.theundertaker11.kitchensink.ksitems.ItemRenderRegistry;
import com.theundertaker11.kitchensink.ksitems.Itemsss;

import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy {
	int WandVariantInt = 4;
	int PickVariantInt = 8;
	@Override
	public void registerItemRenderer(Item item, int meta, String id) 
	{
		 ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Refernce.MODID + ":" + id, "inventory"));
	}
	@Override
	public void registerRenders()
	{
		ItemRenderRegistry.RenderItems();
		
		
		
		//ResourceLocation[] WandTextures = new ResourceLocation[WandVariantInt];
		ResourceLocation[] LevelPickTextures = new ResourceLocation[PickVariantInt];
		for(int i = 0; i < WandVariantInt; i++)
		{
			String modelName = Refernce.MODID + ":" + "Wand" + i;
			//WandTextures[i] = new ResourceLocation(modelName);
		}
		for(int i = 0; i < PickVariantInt; i++)
		{
			String modelName = Refernce.MODID + ":" + "LevelPick" + i;
			LevelPickTextures[i] = new ResourceLocation(modelName);
		}
		
		//ModelBakery.registerItemVariants(Itemsss.Wand, WandTextures);
		ModelBakery.registerItemVariants(Itemsss.LevelPick, LevelPickTextures);
	}

}
