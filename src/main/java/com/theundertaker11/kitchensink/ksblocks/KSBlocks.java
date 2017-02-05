package com.theundertaker11.kitchensink.ksblocks;

import com.theundertaker11.kitchensink.KitchenSink;
import com.theundertaker11.kitchensink.ksblocks.quarry.QuarryBlock;
import com.theundertaker11.kitchensink.ksblocks.trashchest.TrashChest;
import com.theundertaker11.kitchensink.render.IItemModelProvider;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class KSBlocks {
	public static Block BabyBlueBlock;
	public static Block BlackBlock;
	public static Block BlueBlock;
	public static Block BrownBlock;
	public static Block GreenBlock;
	public static Block OrangeBlock;
	public static Block PurpleBlock;
	public static Block RedBlock;
	public static Block WhiteBlock;
	public static Block YellowBlock;
	public static Block HealingBlock;
	public static Block QuarryBlock;
	public static Block TrashChest;
	public static Block BlessedFurnace;
	
	public static void createBlocks() 
	{
		BabyBlueBlock = register(new BlockBase("BabyBlueBlock"));
		BlackBlock = register(new BlockBase("BlackBlock"));
		BlueBlock = register(new BlockBase("BlueBlock"));
		BrownBlock = register(new BlockBase("BrownBlock"));
		GreenBlock = register(new BlockBase("GreenBlock"));
		OrangeBlock = register(new BlockBase("OrangeBlock"));
		PurpleBlock = register(new BlockBase("PurpleBlock"));
		RedBlock = register(new BlockBase("RedBlock"));
		WhiteBlock = register(new BlockBase("WhiteBlock"));
		YellowBlock = register(new BlockBase("YellowBlock"));
		HealingBlock = register(new HealingBlock("HealingBlock"));
		QuarryBlock = register(new QuarryBlock("QuarryBlock"));
		TrashChest = register(new TrashChest("TrashChest"));
	}
	private static <T extends Block> T register (T block, ItemBlock itemBlock)
	{
		 GameRegistry.register(block);
		 if(itemBlock != null)
		 {
		 GameRegistry.register(itemBlock);
		 }
		 
		 if(block instanceof IItemModelProvider)
		 {
		 ((IItemModelProvider)block).registerItemModel(itemBlock);
		 }
		 
		 return block;
	}
		 
	private static <T extends Block> T register(T block)
	{
		 ItemBlock itemBlock = new ItemBlock(block);
		 itemBlock.setRegistryName(block.getRegistryName());
		 return register(block, itemBlock);
	}

}
