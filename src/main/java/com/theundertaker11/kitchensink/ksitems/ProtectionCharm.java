package com.theundertaker11.kitchensink.ksitems;

import java.util.List;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ProtectionCharm extends ItemBase implements IBauble{
	
	public ProtectionCharm(String name) 
	{
		super(name);
		this.setMaxStackSize(1);
		this.setMaxDamage(0);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
		tooltip.add("Protects you from any and all damage");
		tooltip.add("Only comes into affect when you reach 30% or lower health");
		tooltip.add("Also slowly repairs itself");
		if(stack.getTagCompound()!=null)
			{
			tooltip.add("Duribility:"+stack.getTagCompound().getInteger("dur")+"/"+stack.getTagCompound().getInteger("maxdur"));
			}
    }
	
	public static void DamageCharm(ItemStack itemstack, int amount)
	{
		if(itemstack.getTagCompound().getInteger("dur")>amount)
		{
			itemstack.getTagCompound().setInteger("dur", (itemstack.getTagCompound().getInteger("dur")-amount));
		}
	}
	
	public static void RepairCharm(ItemStack itemstack, int amount)
	{
		if(itemstack.getTagCompound().getInteger("dur")<=(400-amount))
		{
			itemstack.getTagCompound().setInteger("dur", (itemstack.getTagCompound().getInteger("dur")+amount));
		}
	}
	
	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int metadata, boolean bool)
	{
		if(entity instanceof EntityPlayer)
		{
			if (itemstack.getTagCompound() == null)
			{
				itemstack.setTagCompound(new NBTTagCompound());
				itemstack.getTagCompound().setInteger("dur", 400);
				itemstack.getTagCompound().setInteger("maxdur", 400);
			}
		}
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack)
    {
		if(stack.getTagCompound()!=null&&stack.getTagCompound().getInteger("dur")<stack.getTagCompound().getInteger("maxdur")) return true;
		else return false;
    }
	/**Makes it so duribility bar still shows even though I'm using meta damage for textures */
	@Override
	public double getDurabilityForDisplay(ItemStack stack)
    {
		if(stack.getTagCompound()!=null)
		{
			return ((double)stack.getTagCompound().getInteger("maxdur")-(double)stack.getTagCompound().getInteger("dur"))/(double)stack.getTagCompound().getInteger("maxdur");
		}
		else return 1;
    }

	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		return BaubleType.RING;
	}
}
