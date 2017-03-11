package com.theundertaker11.kitchensink.ksitems.armor.angelhandler;
/**
 * This and the other handler classes all only work with the chestplate, but are sorted like this
 * for organization sake.
 * @author TheUnderTaker11
 */
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class AngelChestHandler {

	public static void handle(EntityPlayer player, ItemStack stack)
	{
		NBTTagCompound tag = stack.getTagCompound();
		if(tag.getBoolean("flight"))
		{
			player.capabilities.allowFlying=true;
			player.sendPlayerAbilities();
			if(!AngelArmorHandler.flightlist.contains(player.getName())) AngelArmorHandler.flightlist.add(player.getName());
		}
	}
}
