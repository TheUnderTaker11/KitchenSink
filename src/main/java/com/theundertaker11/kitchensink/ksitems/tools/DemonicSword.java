package com.theundertaker11.kitchensink.ksitems.tools;

import java.util.List;

import com.theundertaker11.kitchensink.KitchenSink;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class DemonicSword extends ItemSword {

	public DemonicSword(String name, ToolMaterial material) {
		super(material);
	    this.setCreativeTab(KitchenSink.KStab);
	    this.setMaxDamage(9000);
	    this.setUnlocalizedName(name);
	    this.setRegistryName(name);
	}
	
	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int metadata, boolean bool)
	{
		if (itemstack.getTagCompound() == null)
        {
			itemstack.setTagCompound(new NBTTagCompound());
			itemstack.getTagCompound().setInteger("zombiekills", 0);
			itemstack.getTagCompound().setInteger("skeletonkills", 0);
			itemstack.getTagCompound().setInteger("creeperkills", 0);
			itemstack.getTagCompound().setInteger("endermankills", 0);
        }
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
		if(stack.getTagCompound() != null)
		{
			NBTTagCompound tag=stack.getTagCompound();
			float damage = 150f;
			if(tag.getInteger("skeletonkills") > 99)
			{
				if(entity instanceof EntitySkeleton)
				{
					entity.attackEntityFrom(DamageSource.causePlayerDamage(player), damage);
				}
			}	
			if(tag.getInteger("zombiekills") > 99)
			{
				if(entity instanceof EntityZombie)
				{
					entity.attackEntityFrom(DamageSource.causePlayerDamage(player), damage);
				}
			}
			if(tag.getInteger("creeperkills") > 99)
			{
				if(entity instanceof EntityCreeper)
				{
					entity.attackEntityFrom(DamageSource.causePlayerDamage(player), damage);
				}
			}
			if(tag.getInteger("endermankills") > 99)
			{
				if(entity instanceof EntityEnderman)
				{
					entity.attackEntityFrom(DamageSource.causePlayerDamage(player), damage);
				}
			}
			
			
		}
		return false;
    }
	
	@Override
    public boolean hasEffect(ItemStack par1ItemStack)
    {
		if (par1ItemStack.getTagCompound() != null)
        {
			NBTTagCompound tag=par1ItemStack.getTagCompound();
			if(tag.getInteger("skeletonkills") >= 100 && tag.getInteger("creeperkills") >= 100
					&& tag.getInteger("endermankills") >= 100&& tag.getInteger("zombiekills") >= 100)
			{
	    	return true;
			}
        }
	    return false;
    }
	
	
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {	
		NBTTagCompound tag=stack.getTagCompound();
		if(tag != null)
		{
			tooltip.add(TextFormatting.RED + "Zombie Souls: " + tag.getInteger("zombiekills"));
			tooltip.add(TextFormatting.RED + "Skeleton Souls: " + tag.getInteger("skeletonkills"));
			tooltip.add(TextFormatting.RED + "Creeper Souls: " + tag.getInteger("creeperkills"));
			tooltip.add(TextFormatting.RED + "Enderman Souls: " + tag.getInteger("endermankills"));
		}
		else
		{
			tooltip.add(TextFormatting.RED + "Zombie Souls: 0");
			tooltip.add(TextFormatting.RED + "Skeleton Souls: 0");
			tooltip.add(TextFormatting.RED + "Creeper Souls: 0");
			tooltip.add(TextFormatting.RED + "Enderman Souls: 0");
		}
	}
	
	public static void addKill(ItemStack stack, int mob)
	{
		NBTTagCompound tag=stack.getTagCompound();
		if (tag != null)
        {
			if(mob == 0&&tag.getInteger("skeletonkills")<100)
			{
					tag.setInteger("skeletonkills", tag.getInteger("skeletonkills")+1);
			}
			if(mob == 1&&tag.getInteger("zombiekills")<100)
			{
					tag.setInteger("zombiekills", tag.getInteger("zombiekills")+1);  
			}
			if(mob == 2&&tag.getInteger("creeperkills")<100)
			{
					tag.setInteger("creeperkills", tag.getInteger("creeperkills")+1);  
			}
			if(mob == 3&&tag.getInteger("endermankills")<100)
			{
					tag.setInteger("endermankills", tag.getInteger("endermankills")+1);  
			}
        }
		
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
		/*
		if(stack.getTagCompound()==null) stack.setTagCompound(new NBTTagCompound());
		NBTTagCompound tag=stack.getTagCompound();
		tag.setInteger("skeletonkills", 100);
		tag.setInteger("zombiekills", 100);
		tag.setInteger("creeperkills", 100);
		tag.setInteger("endermankills", 100);
		*/
		return true;
    }
	
}
