package com.theundertaker11.kitchensink.crafting.armorupgrades;

import com.theundertaker11.kitchensink.crafting.CraftingManager;
import com.theundertaker11.kitchensink.ksitems.Itemsss;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class FlightUpgradeCrafting implements IRecipe{

	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) 
	{
		int chest = 0;
		int rock = 0;
		int empty = 0;
		for(int i=0; i < inv.getSizeInventory(); ++i)
		{
			ItemStack item = inv.getStackInSlot(i);
			if(item==null) ++empty;
			else if (item.getItem() == Itemsss.AngelArmorRing && item.getTagCompound() != null) 
			{
				if(!item.getTagCompound().getBoolean("flight"))
				{
					++chest;
				}
			}
			else if(item.getItem()==Itemsss.blessedRock)
			{
				++rock;
				
			}
		}
		if(inv.getSizeInventory()==9&&chest == 1 && rock==1 && empty==7) 	
		{
			return true;
		}
		else if(inv.getSizeInventory()==4 && chest==1&&rock==1 && empty==2) return true;
		else return false;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) 
	{
		ItemStack item = ArmorCraftingUtils.getRing(inv);
		if(item==null) return null;
	
		ItemStack result = new ItemStack(Itemsss.AngelArmorRing);
		result.setTagCompound(item.copy().getTagCompound());
		result.getTagCompound().setBoolean("flight", true);
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
		ItemStack stack = new ItemStack(Itemsss.AngelArmorRing);
		return stack;	
	}
	@Override
	public ItemStack[] getRemainingItems(InventoryCrafting inv)
	{
		return new ItemStack[inv.getSizeInventory()];
	}
}