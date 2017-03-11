package com.theundertaker11.kitchensink.crafting.levelpick;

import com.theundertaker11.kitchensink.crafting.CraftingManager;
import com.theundertaker11.kitchensink.ksitems.Itemsss;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class LevelToolSpeed implements IRecipe{
		// 012
		// 345
		// 678
	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) 
	{
		int pick = 0;
		int redstonecount = 0;
		int none = 0;
		int otheritems = 0;
		for(int i=0; i < inv.getSizeInventory(); ++i)
		{
			ItemStack item = inv.getStackInSlot(i);
			
			if (item != null && item.getItem() == Itemsss.LevelPick && item.getTagCompound() != null) 
			{
				if(item.getTagCompound().getInteger("pickspeed")<20000000) ++pick;
			}
			else if(item != null && item.getItem()==item.getItem().getItemFromBlock(Blocks.REDSTONE_BLOCK))
			{
				++redstonecount;
			}
			else if(item==null) ++none;
			else if(item!=null) ++otheritems;
		}
		if(pick == 1&&redstonecount>0&&otheritems==0)
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
		
		float speedadded = 0;
		for(int i=0; i < inv.getSizeInventory(); ++i)
		{
			ItemStack stack = inv.getStackInSlot(i);
			if(stack!=null&&stack.getItem()==item.getItem().getItemFromBlock(Blocks.REDSTONE_BLOCK))
			{
				speedadded+=0.5F;
			}
		}
		ItemStack result = new ItemStack(Itemsss.LevelPick);
		result.setTagCompound(item.copy().getTagCompound());
		result.getTagCompound().setFloat("pickspeed", item.getTagCompound().getFloat("pickspeed")+speedadded);
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
