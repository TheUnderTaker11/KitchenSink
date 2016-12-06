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

public class CustomLevelToolFixing implements IRecipe{
		// 012
		// 345
		// 678
	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) 
	{
		int pick = 0;
		int ironcount = 0;
		int noitem = 0;
		for(int i=0; i < inv.getSizeInventory(); ++i)
		{
			
			ItemStack item = inv.getStackInSlot(i);
			
			if (item != null && item.getItem() == Itemsss.LevelPick && item.getTagCompound() != null) 
			{
				NBTTagCompound tag = item.getTagCompound();
				if(tag.getInteger("dur")<=(tag.getInteger("maxdur")-100))
				{
					++pick;
				}
			}
			if(item != null && inv.getStackInSlot(i).getItem()==Items.IRON_INGOT)
			{
				++ironcount;
			}
			if(i==0||i==1||i==2||i==3||i==5||i==6||i==7||i==8)
			{
				if(item==null)
				{
					++noitem;
				}
			}
		}
		if(pick == 1 && ironcount==1&&noitem==7)
		{
			return true;
		}
		
		else return false;
	}

	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) 
	{
		ItemStack item = inv.getStackInSlot(4);
		ItemStack result = new ItemStack(Itemsss.LevelPick);
		result.setTagCompound(item.copy().getTagCompound());
		result.getTagCompound().setInteger("dur", item.getTagCompound().getInteger("dur")+100);
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
		ItemStack stack = new ItemStack(Itemsss.LevelPick);
		return stack;	
	}
	@Override
	public ItemStack[] getRemainingItems(InventoryCrafting inv)
	{
		return new ItemStack[inv.getSizeInventory()];
	}

}
