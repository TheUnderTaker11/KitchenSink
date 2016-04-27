package com.theundertaker11.kitchensink.ksitems;

import java.util.List;

import com.theundertaker11.kitchensink.KitchenSink;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class blessedRock extends Item {
	public blessedRock(String name){
		super();
		this.setUnlocalizedName(name);
		this.setCreativeTab(KitchenSink.KStab);
		this.setRegistryName(name);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
     tooltip.add("What could a rock ever... Oh!"); 
    }

	@Override
	 public EnumRarity getRarity(ItemStack stack)
	    {
	        return EnumRarity.RARE;
	    }
	
	@Override
    public boolean hasEffect(ItemStack par1ItemStack)
    {
	    return true;
    }
}
