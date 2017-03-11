package com.theundertaker11.kitchensink.ksitems.armor.angelhandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
/**
 * This and the other handler classes all only work with the chestplate, but are sorted like this
 * for organization sake.
 * @author TheUnderTaker11
 */
public class AngelHelmetHandler {
	
	public static void handle(EntityPlayer player, ItemStack stack)
	{
		NBTTagCompound tag = stack.getTagCompound();
		
		if(tag.hasKey("waterbreathing"))
		{
			if(player.isInWater()) player.setAir(300);
		}
	}
}
