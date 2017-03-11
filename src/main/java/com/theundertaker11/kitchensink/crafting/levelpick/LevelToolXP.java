package com.theundertaker11.kitchensink.crafting.levelpick;

import com.theundertaker11.kitchensink.crafting.CraftingManager;
import com.theundertaker11.kitchensink.ksitems.Itemsss;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class LevelToolXP implements IRecipe{
		// 012
		// 345
		// 678
	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) 
	{
		int pick = 0;
		int xpcount = 0;
		int noitem = 0;
		for(int i=0; i < inv.getSizeInventory(); ++i)
		{
			
			ItemStack item = inv.getStackInSlot(i);
			
			if (item != null && item.getItem() == Itemsss.LevelPick && item.getTagCompound() != null) 
			{
				NBTTagCompound tag = item.getTagCompound();
				if(!tag.hasKey("xpfromblocks"))
				{
					++pick;
				}
			}
			if(item != null && inv.getStackInSlot(i).getItem()==Itemsss.xpItem)
			{
				++xpcount;
			}
		}
		if(inv.getSizeInventory()==9&&pick == 1 && xpcount==8)
		{
			return true;
		}
		else return false;
	}
	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) 
	{
		ItemStack item = CraftingManager.getPick(inv);
		if(item==null) return null;
		
		ItemStack result = new ItemStack(Itemsss.LevelPick);
		result.setTagCompound(item.copy().getTagCompound());
		result.getTagCompound().setInteger("xpfromblocks", 0);
		if(item.getItemDamage()==0)
		{
			result.setItemDamage(1);
		}
		else if(item.getItemDamage()==2)
		{
			result.setItemDamage(4);
		}
		else if(item.getItemDamage()==3)
		{
			result.setItemDamage(5);
		}
		else if(item.getItemDamage()==6)
		{
			result.setItemDamage(7);
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
		return new ItemStack(Itemsss.LevelPick);
	}
	@Override
	public ItemStack[] getRemainingItems(InventoryCrafting inv)
	{
		return new ItemStack[inv.getSizeInventory()];
	}

}
