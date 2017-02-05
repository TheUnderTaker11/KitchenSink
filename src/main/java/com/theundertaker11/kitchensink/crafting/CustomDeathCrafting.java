package com.theundertaker11.kitchensink.crafting;

import com.theundertaker11.kitchensink.ksitems.Itemsss;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class CustomDeathCrafting implements IRecipe {
		// 012
		// 345
		// 678
	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) 
	{
		int demonsword = 0;
		int ingot = 0;
		for(int i=0; i < inv.getSizeInventory(); ++i)
		{
			ItemStack item = inv.getStackInSlot(i);
			if (item != null &&i==4&& item.getItem() == Itemsss.DemonicSword && item.getTagCompound() != null) 
			{
				if(item.getTagCompound().getInteger("zombiekills")>99&&item.getTagCompound().getInteger("endermankills")>99
					&&item.getTagCompound().getInteger("skeletonkills")>99&&item.getTagCompound().getInteger("creeperkills")>99)
				{
					++demonsword;
				}
				
				
			}
			if(item !=null&&item.getItem()==Itemsss.deathIngot)
			{
				if(i != 4)
				{
					++ingot;
				}
			}
			
			

			
		}
		if(demonsword ==1&&ingot==8)
		{
			return true;
		}
		else return false;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) 
	{
		return new ItemStack(Itemsss.deathsSythe);
	}

	@Override
	public int getRecipeSize() 
	{
		return 1;
	}

	@Override
	public ItemStack getRecipeOutput() 
	{
		return new ItemStack(Itemsss.deathsSythe);
	}

	@Override
	public ItemStack[] getRemainingItems(InventoryCrafting inv) 
	{
		return new ItemStack[inv.getSizeInventory()];
	}

}
