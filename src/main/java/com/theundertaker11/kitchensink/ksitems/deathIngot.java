package com.theundertaker11.kitchensink.ksitems;

import com.theundertaker11.kitchensink.KitchenSink;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class deathIngot extends Item {
	public deathIngot(String name){
		super();
		this.setUnlocalizedName(name);
		this.setCreativeTab(KitchenSink.KStab);
		this.setRegistryName(name);
	}
	@Override
    public boolean hasEffect(ItemStack par1ItemStack)
    {
	    return true;
    }
}
