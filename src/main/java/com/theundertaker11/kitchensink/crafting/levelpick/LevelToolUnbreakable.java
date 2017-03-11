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

public class LevelToolUnbreakable implements IRecipe{
		// 012
		// 345
		// 678
	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) 
	{
		int pick = 0;
		int compcount = 0;
		int obscount = 0;
		for(int i=0; i < inv.getSizeInventory(); ++i)
		{
			
			ItemStack item = inv.getStackInSlot(i);
			if(item!=null)
			{
				if (i==4&&item.getItem() == Itemsss.LevelPick && item.getTagCompound() != null) 
				{
					if(!item.getTagCompound().hasKey("unbreakable")||!item.getTagCompound().getBoolean("unbreakable"))
					{
						++pick;
					}
				}
				if(item.getItem()==Itemsss.diamondPlate)
				{
					++compcount;
				}
				if(item.getItem()==Itemsss.obsidianPlate)
				{
					++obscount;
				}
			}
		}
		if(inv.getSizeInventory()==9&&pick == 1 && compcount==4&&obscount==4)
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
		NBTTagCompound tag = result.getTagCompound();
		tag.setBoolean("unbreakable", true);
		tag.setInteger("dur", 1000);
		tag.setInteger("maxdur", 1000);
		if(item.getItemDamage()==0)
		{
			result.setItemDamage(3);
		}
		else if(item.getItemDamage()==1)
		{
			result.setItemDamage(5);
		}
		else if(item.getItemDamage()==2)
		{
			result.setItemDamage(6);
		}
		else if(item.getItemDamage()==4)
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
