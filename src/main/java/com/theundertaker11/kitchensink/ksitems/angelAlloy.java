package com.theundertaker11.kitchensink.ksitems;

import com.theundertaker11.kitchensink.KitchenSink;

import net.minecraft.item.Item;

public class angelAlloy extends Item{
	
	public angelAlloy(String name){
		super();
		this.setUnlocalizedName(name);
		this.setCreativeTab(KitchenSink.KStab);
		this.setRegistryName(name);
	}
}
