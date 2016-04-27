package com.theundertaker11.kitchensink.ksitems.kscrafting;

import com.theundertaker11.kitchensink.ksitems.Itemsss;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;


public class CustomSwordCrafting implements IRecipe{
	// Order of Recipe numbers
	// 012
	// 345
	// 678    
	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) {
		
		int zombie = 0;
		int skeleton = 0;
		int creeper = 0;
		int deathingot = 0;
		int deathingot1 = 0;
		int deathingot2 = 0;
		int deathingot3 = 0;
		int netherstar = 0;
		int netherstar1 = 0;
		ItemStack sword = null;
		for(int i=0; i < inv.getSizeInventory(); ++i)
		{
			ItemStack item = inv.getStackInSlot(i);
			if (item != null && item.getItem() == Itemsss.SkeletonSword && item.getTagCompound() != null) 
			{

				NBTTagCompound tag = item.getTagCompound();
				if (tag.getInteger("kills") > 49) 
				{
					if(i == 3 && tag.getString("mob").equals("zombie"))
					{
						++zombie;
					}	
					if(i == 5 &&tag.getString("mob").equals("creeper"))
					{
						++creeper;
					}
					if(i == 7 && tag.getString("mob").equals("skeleton"))
					{
						++skeleton;
					}
					 
				}
			}
			if(item != null && item.getItem() == Itemsss.deathIngot)
			{
				if(i ==0)
				{
					++deathingot;
				}
				if(i ==1)
				{
					++deathingot1;
				}
				if(i ==2 )
				{
					++deathingot2;
				}
				if(i ==4)
				{
					++deathingot3;
				}
				
			}
			if(item != null && item.getItem() == Itemsss.netherStarPlate)
			{
				if(i == 6)
				{
					++netherstar;
				}
				if(i == 8)
				{
					++netherstar1;
				}
				
			}
			
		}
			 
		if(zombie == 1 && skeleton == 1 && creeper == 1 && deathingot == 1 && netherstar == 1&& netherstar1 == 1 && deathingot1 == 1
	    	&& deathingot2 == 1 && deathingot3 == 1)
		{
			return true;
		}
		else return false;
		
		
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		ItemStack stack = new ItemStack(Itemsss.DemonicSword);
		stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setString("mobzombie", "zombie");
		stack.getTagCompound().setString("mobskeleton", "skeleton");
		stack.getTagCompound().setString("mobcreeper", "creeper");
		stack.getTagCompound().setString("mobenderman", "enderman");
		stack.getTagCompound().setInteger("zombiekills", 0);
		stack.getTagCompound().setInteger("skeletonkills", 0);
		stack.getTagCompound().setInteger("creeperkills", 0);
		stack.getTagCompound().setInteger("endermankills", 0);	
		return stack;
	}

	@Override
	public int getRecipeSize() {
		
		return 8;
	}

	@Override
	public ItemStack getRecipeOutput() {
		
		ItemStack stack = new ItemStack(Itemsss.DemonicSword);
		stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setString("mobzombie", "zombie");
		stack.getTagCompound().setString("mobskeleton", "skeleton");
		stack.getTagCompound().setString("mobcreeper", "creeper");
		stack.getTagCompound().setString("mobenderman", "enderman");
		stack.getTagCompound().setInteger("zombiekills", 0);
		stack.getTagCompound().setInteger("skeletonkills", 0);
		stack.getTagCompound().setInteger("creeperkills", 0);
		stack.getTagCompound().setInteger("endermankills", 0);	
		return stack;
	}

	@Override
	public ItemStack[] getRemainingItems(InventoryCrafting inv) {
		
		return new ItemStack[inv.getSizeInventory()];
	}
	


}
