package com.theundertaker11.kitchensink.crafting.armorupgrades;

import com.theundertaker11.kitchensink.crafting.CraftingManager;
import com.theundertaker11.kitchensink.ksitems.Itemsss;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class WaterBreathingUpgradeCrafting implements IRecipe{

	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) 
	{
		int ring = 0;
		int slime = 0;
		int obs = 0;
		int bottle = 0;
		for(int i=0; i < inv.getSizeInventory(); ++i)
		{
			ItemStack item = inv.getStackInSlot(i);
			if(item==null) ;
			else if (item.getItem() == Itemsss.AngelArmorRing && item.getTagCompound() != null) 
			{
				if(!item.getTagCompound().hasKey("waterbreathing"))
				{
					++ring;
				}
			}
			else if(OreDictionary.getOres("slimeball").contains(item))
			{
				++slime;	
			}
			else if(item.getItem()==Itemsss.obsidianPlate)
			{
				++obs;
			}
			else if(item.getItem()==Items.GLASS_BOTTLE)
			{
				++bottle;
			}
		}
		if(inv.getSizeInventory()==9&&ring == 1 && slime==2 && obs==2 && bottle==4) 	
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
		result.getTagCompound().setBoolean("waterbreathing", true);
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