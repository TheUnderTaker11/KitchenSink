package com.theundertaker11.kitchensink.ksblocks;

import com.theundertaker11.kitchensink.KitchenSink;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.material.Material;
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
	//public static Block CompChest;
	
	
	public static void createBlocks() 
	{
		GameRegistry.register(BabyBlueBlock = new BlockBase("BabyBlueBlock"));
		GameRegistry.register(BlackBlock = new BlockBase("BlackBlock"));
		GameRegistry.register(BlueBlock = new BlockBase("BlueBlock"));
		GameRegistry.register(BrownBlock = new BlockBase("BrownBlock"));
		GameRegistry.register(GreenBlock = new BlockBase("GreenBlock"));
		GameRegistry.register(OrangeBlock = new BlockBase("OrangeBlock"));
		GameRegistry.register(PurpleBlock = new BlockBase("PurpleBlock"));
		GameRegistry.register(RedBlock = new BlockBase("RedBlock"));
		GameRegistry.register(WhiteBlock = new BlockBase("WhiteBlock"));
		GameRegistry.register(YellowBlock = new BlockBase("YellowBlock"));
		//GameRegistry.register(CompChest = new StorageBlockBase(Material.IRON, "CompChest"));
		GameRegistry.registerBlock(BabyBlueBlock);
		GameRegistry.registerBlock(BlackBlock);
		GameRegistry.registerBlock(BlueBlock);
		GameRegistry.registerBlock(BrownBlock);
		GameRegistry.registerBlock(GreenBlock);
		GameRegistry.registerBlock(OrangeBlock);
		GameRegistry.registerBlock(PurpleBlock);
		GameRegistry.registerBlock(RedBlock);
		GameRegistry.registerBlock(WhiteBlock);
		GameRegistry.registerBlock(YellowBlock);
		//GameRegistry.registerBlock(CompChest);
	}

}
