package com.theundertaker11.kitchensink.ksitems;

import java.util.List;

import com.theundertaker11.kitchensink.KitchenSink;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class blessedRock extends Item implements IBauble{
	public blessedRock(String name){
		super();
		this.setUnlocalizedName(name);
		this.setCreativeTab(KitchenSink.KStab);
		this.setRegistryName(name);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
     tooltip.add("What could a rock ever... Oh!"); 
    }

	@Override
	 public EnumRarity getRarity(ItemStack stack)
	    {
	        return EnumRarity.RARE;
	    }
	
	@Override
    public boolean hasEffect(ItemStack par1ItemStack)
    {
	    return true;
    }
	
	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int metadata, boolean bool)
	{
		if(entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)entity;
			player.capabilities.allowFlying = true;
		}
	}

	@Override
	public BaubleType getBaubleType(ItemStack arg0) 
	{
		return BaubleType.TRINKET;
	}
}
