package com.theundertaker11.kitchensink.crafting.armorupgrades;
import com.theundertaker11.kitchensink.crafting.CraftingManager;
import com.theundertaker11.kitchensink.ksitems.Itemsss;

import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionUtils;
import net.minecraft.world.World;

public class LegSpeedUpgradeCrafting implements IRecipe{

	// 012
	// 345
	// 678
	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) 
	{
		int legs = 0;
		int potion = 0;
		int sugar = 0;
		for(int i=0; i < inv.getSizeInventory(); ++i)
		{
			ItemStack item = inv.getStackInSlot(i);
			if(item==null) ;
			
			else if (i==4&&item.getItem() == Itemsss.AngelArmorRing && item.getTagCompound() != null)
			{
				if(item.getTagCompound().hasKey("speed"))
				{
					if(item.getTagCompound().getInteger("speed")<5) ++legs;
				}
				else ++legs;
			}
			
			else if((i==1||i==3||i==5||i==7)&&item.getItem()==Items.POTIONITEM
					&&PotionUtils.getPotionFromItem(item)==PotionTypes.STRONG_SWIFTNESS) ++potion;
			
			else if(item.getItem()==Items.SUGAR) ++sugar;
		}
		if(inv.getSizeInventory()==9&&legs == 1 && potion==4 && sugar==4) 	
		{
			return true;
		}
		else return false;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) 
	{
		ItemStack item = ArmorCraftingUtils.getRing(inv);
		if(item==null) return null;
	
		ItemStack result = new ItemStack(Itemsss.AngelArmorRing);
		result.setTagCompound(item.copy().getTagCompound());
		result.getTagCompound().setInteger("speed", item.getTagCompound().getInteger("speed")+1);
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
