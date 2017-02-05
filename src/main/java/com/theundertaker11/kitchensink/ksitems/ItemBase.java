package com.theundertaker11.kitchensink.ksitems;

import com.theundertaker11.kitchensink.KitchenSink;
import com.theundertaker11.kitchensink.render.IItemModelProvider;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBase extends Item implements IItemModelProvider{
	protected String name;
	boolean hasEffct;
	public ItemBase(String name, boolean hasEffect){
		super();
		this.name= name;
		this.hasEffct=hasEffect;
		setUnlocalizedName(name);
		setCreativeTab(KitchenSink.KStab);
		setRegistryName(name);
	}
	public ItemBase(String name){
		this(name, false);
	}
	@Override
	public void registerItemModel(Item item) {
		KitchenSink.proxy.registerItemRenderer(this, 0, name);
	}
	
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack)
	{
		return this.hasEffct;
	}

}
