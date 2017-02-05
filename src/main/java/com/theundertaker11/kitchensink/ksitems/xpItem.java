package com.theundertaker11.kitchensink.ksitems;

import com.theundertaker11.kitchensink.KitchenSink;

import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class xpItem extends ItemBase {
	public xpItem(String name){
		super(name);
		setMaxDamage(1);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
		if(!worldIn.isRemote)
		{
			worldIn.spawnEntityInWorld(new EntityXPOrb(worldIn, playerIn.posX + 1, playerIn.posY, playerIn.posZ, 43));
			stack.damageItem(2, playerIn);
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
	}

}
