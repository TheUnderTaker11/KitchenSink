package com.theundertaker11.kitchensink.ksitems;

import com.theundertaker11.kitchensink.KitchenSink;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class godEssence extends Item {
	public godEssence(String name){
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
	@Override
	 public EnumRarity getRarity(ItemStack stack)
	    {
	        return EnumRarity.RARE;
	    }

}
