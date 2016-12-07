package com.theundertaker11.kitchensink.ksitems.kscrafting;

import com.theundertaker11.kitchensink.ksitems.Itemsss;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class MagnetRingCrafting implements IRecipe{
		// 012
		// 345
		// 678
	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) 
	{
		int ring = 0;
		int magnet = 0;
		int noitem = 0;
		for(int i=0; i < inv.getSizeInventory(); ++i)
		{
			
			ItemStack item = inv.getStackInSlot(i);
			
			if (item != null && item.getItem() == Itemsss.MagnetRing && item.getTagCompound() == null) 
			{
				++ring;
			}
			if(item != null)
			{
				//Checks for any of the 6 magnets
				if(inv.getStackInSlot(i).getItem()==Itemsss.ItemMagnet||inv.getStackInSlot(i).getItem()==Itemsss.ItemMagnetT2||inv.getStackInSlot(i).getItem()==Itemsss.ItemMagnetT3||inv.getStackInSlot(i).getItem()==Itemsss.ItemMagnetT4||inv.getStackInSlot(i).getItem()==Itemsss.ItemMagnetT5||inv.getStackInSlot(i).getItem()==Itemsss.ItemMagnetT6) ++magnet;
			}
			if(item==null)
			{
					++noitem;
			}
		}
		if(ring == 1 && magnet==1&&noitem==7)
		{
			return true;
		}
		else return false;
	}

	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) 
	{
		ItemStack result = new ItemStack(Itemsss.MagnetRing);
		result.setTagCompound(new NBTTagCompound());
		for(int i=0; i < inv.getSizeInventory(); ++i)
		{
			ItemStack item = inv.getStackInSlot(i);
			if(item!=null)
			{
				if(item.getItem()!=Itemsss.MagnetRing)
				{
					if(item.getItem()==Itemsss.ItemMagnet)
					{
						result.getTagCompound().setInteger("teir", 1);
						result.getTagCompound().setDouble("range", 2.5);
					}
					else if(item.getItem()==Itemsss.ItemMagnetT2)
					{
						result.getTagCompound().setInteger("teir", 2);
						result.getTagCompound().setDouble("range", 3.5);
					}
					else if(item.getItem()==Itemsss.ItemMagnetT3)
					{
						result.getTagCompound().setInteger("teir", 3);
						result.getTagCompound().setDouble("range", 4.5);
					}
					else if(item.getItem()==Itemsss.ItemMagnetT4)
					{
						result.getTagCompound().setInteger("teir", 4);
						result.getTagCompound().setDouble("range", 5.5);
					}
					else if(item.getItem()==Itemsss.ItemMagnetT5)
					{
						result.getTagCompound().setInteger("teir", 5);
						result.getTagCompound().setDouble("range", 6.5);
					}
					else if(item.getItem()==Itemsss.ItemMagnetT6)
					{
						result.getTagCompound().setInteger("teir", 6);
						result.getTagCompound().setDouble("range", 12.5);
					}
				}
			}
		}
		return result;
	}
	@Override
	public int getRecipeSize()
	{
		return 2;
	}
	@Override
	public ItemStack getRecipeOutput()
	{
		ItemStack stack = new ItemStack(Itemsss.MagnetRing);
		return stack;	
	}
	@Override
	public ItemStack[] getRemainingItems(InventoryCrafting inv)
	{
		return new ItemStack[inv.getSizeInventory()];
	}

}
