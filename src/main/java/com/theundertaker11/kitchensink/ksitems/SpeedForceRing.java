package com.theundertaker11.kitchensink.ksitems;

import java.util.List;

import com.theundertaker11.kitchensink.ModUtils;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class SpeedForceRing extends ItemBase implements IBauble{

	public SpeedForceRing(String name) {
		super(name);
		setMaxDamage(0);
		this.setMaxStackSize(1);
	}

	//adds tooltip
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
		tooltip.add("Would not use for now, pretty glitchy FOV wise");
    }
	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		return BaubleType.RING;
	}
	@Override
	public void onWornTick(ItemStack itemstack, EntityLivingBase Player) 
	{
		if(Player instanceof EntityPlayer&&!Player.worldObj.isRemote)
		{
			EntityPlayer player = (EntityPlayer)Player;
			player.capabilities.setFlySpeed(3.0F);
			player.capabilities.setPlayerWalkSpeed(3.0F);
			if(itemstack.getItemDamage()==1)
			{
				player.stepHeight = 1.0F;
			}
		}
	}
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn,
			EnumHand hand) {
		if (!worldIn.isRemote && playerIn.isSneaking()) {
			itemStackIn.setItemDamage(itemStackIn.getItemDamage() == 0 ? 1 : 0);
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
	}
	@Override
	public boolean hasEffect(ItemStack item) {
		if(item.getItemDamage()==1) return true;
		else return false;
	}
}
