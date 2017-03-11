package com.theundertaker11.kitchensink.crafting.armorupgrades;

import com.theundertaker11.kitchensink.ksitems.Itemsss;
import com.theundertaker11.kitchensink.ksitems.armor.KSArmor;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

public class ArmorCraftingUtils {
	
	public static ItemStack getRing(InventoryCrafting inv)
	{
		ItemStack stack = null;
		for(int i=0; i < inv.getSizeInventory(); ++i)
		{
			ItemStack slotstack = inv.getStackInSlot(i);
			if (slotstack != null && slotstack.getTagCompound() != null) 
			{
				if(slotstack.getItem()==Itemsss.AngelArmorRing)
				{
					stack=slotstack;
				}
			}
		}
		return stack;
	}
}
