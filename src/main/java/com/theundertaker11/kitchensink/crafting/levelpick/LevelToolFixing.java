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

public class LevelToolFixing implements IRecipe{
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
				if(tag.getInteger("dur")<(tag.getInteger("maxdur")))
				{
					++pick;
				}
			}
			if(item != null && item.getItem()==Items.IRON_INGOT)
			{
				++ironcount;
			}
			if(item==null)
			{
				++noitem;
			}
		}
		if(inv.getSizeInventory()==9&&pick == 1 && ironcount==1&&noitem==7)
		{
			return true;
		}
		if(inv.getSizeInventory()==4&&pick == 1 && ironcount==1&&noitem==2)
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
		tag.setInteger("dur", tag.getInteger("dur")+(tag.getInteger("maxdur")/6));
		if(tag.getInteger("dur")>tag.getInteger("maxdur"))
		{
			tag.setInteger("dur", tag.getInteger("maxdur"));
		}
		result.setItemDamage(item.getItemDamage());
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
