package com.theundertaker11.kitchensink.ksblocks;

import com.theundertaker11.kitchensink.KitchenSink;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;

public class CompChest extends BlockChest {

	protected CompChest(Type type) 
	{
		super(type);
		this.setUnlocalizedName("CompChest");
		this.setCreativeTab(KitchenSink.KStab);
		
	}

}
