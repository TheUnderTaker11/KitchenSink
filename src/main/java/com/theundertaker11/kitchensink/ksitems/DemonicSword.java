package com.theundertaker11.kitchensink.ksitems;

import java.util.List;

import com.theundertaker11.kitchensink.KitchenSink;

import net.minecraft.entity.Entity;
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
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player)
	{
		if (itemStack.getTagCompound() == null)
        {
			itemStack.setTagCompound(new NBTTagCompound());
        }
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
		if(stack.getTagCompound() != null)
		{
			if(stack.getTagCompound().getString("mobskeleton").equals("skeleton")&& stack.getTagCompound().getInteger("skeletonkills") > 99)
			{
				if(entity instanceof EntitySkeleton)
				{
					float damage = 150f;
					entity.attackEntityFrom(DamageSource.causePlayerDamage(player), damage);
				}
			}	
			if(stack.getTagCompound().getString("mobzombie").equals("zombie")&& stack.getTagCompound().getInteger("zombiekills") > 99)
			{
				if(entity instanceof EntityZombie)
				{
					float damage = 150f;
					entity.attackEntityFrom(DamageSource.causePlayerDamage(player), damage);
				}
			}
			if(stack.getTagCompound().getString("mobcreeper").equals("creeper")&& stack.getTagCompound().getInteger("creeperkills") > 99)
			{
				if(entity instanceof EntityCreeper)
				{
					float damage = 150f;
					entity.attackEntityFrom(DamageSource.causePlayerDamage(player), damage);
				}
			}
			if(stack.getTagCompound().getString("mobenderman").equals("enderman")&& stack.getTagCompound().getInteger("endermankills") > 99)
			{
				if(entity instanceof EntityEnderman)
				{
					float damage = 150f;
					entity.attackEntityFrom(DamageSource.causePlayerDamage(player), damage);
				}
			}
			
			
		}
		return false;
    }
	
	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int metadata, boolean bool)
	{
		if(itemstack.getTagCompound() != null)
		{
		if(itemstack.getTagCompound().getInteger("skeletonkills") >= 100 && itemstack.getTagCompound().getInteger("creeperkills") >= 100
				&& itemstack.getTagCompound().getInteger("endermankills") >= 100&& itemstack.getTagCompound().getInteger("zombiekills") >= 100)
		{
			itemstack.setItemDamage(0);
		}
		//itemstack.getTagCompound().setInteger("skeletonkills", 100);
		//itemstack.getTagCompound().setInteger("zombiekills", 100);
		//itemstack.getTagCompound().setInteger("creeperkills", 100);
		//itemstack.getTagCompound().setInteger("endermankills", 100);
		}
	}
	
	@Override
    public boolean hasEffect(ItemStack par1ItemStack)
    {
		if (par1ItemStack.getTagCompound() != null)
        {
			if(par1ItemStack.getTagCompound().getInteger("skeletonkills") >= 100 && par1ItemStack.getTagCompound().getInteger("creeperkills") >= 100
					&& par1ItemStack.getTagCompound().getInteger("endermankills") >= 100&& par1ItemStack.getTagCompound().getInteger("zombiekills") >= 100)
			{
	    	return true;
			}
        }
	    return false;
    }
	
	
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {	
		if(stack.getTagCompound() != null)
		{	
		 	int intKills = stack.getTagCompound().getInteger("zombiekills");
		 	int intKills1 = stack.getTagCompound().getInteger("creeperkills");
		 	int intKills2 = stack.getTagCompound().getInteger("skeletonkills");
		 	int intKills3 = stack.getTagCompound().getInteger("endermankills");
		 	String stringKills = intKills + "";
		 	String stringKills1 = intKills1 + "";
		 	String stringKills2 = intKills2 + "";
		 	String stringKills3 = intKills3 + "";
			 if(stack.getTagCompound().getString("mobskeleton").equals("skeleton"))
			 {
				 tooltip.add(TextFormatting.RED + "Skeleton Souls: " + stringKills2);
			 }
			 if(stack.getTagCompound().getString("mobzombie").equals("zombie"))
			 {
				 tooltip.add(TextFormatting.RED + "Zombie Souls: " + stringKills);
			 }
			 if(stack.getTagCompound().getString("mobcreeper").equals("creeper"))
			 {
				 tooltip.add(TextFormatting.RED + "Creeper Souls: " + stringKills1);
			 }
			 if(stack.getTagCompound().getString("mobenderman").equals("enderman"))
			 {
				 tooltip.add(TextFormatting.RED + "Enderman Souls: " + stringKills3);
			 }
		}
	}
	
	public static void addKill(ItemStack stack, int mob)
	{
		if (stack.getTagCompound() != null)
        {
			if(mob == 0 && stack.getTagCompound().getString("mobskeleton").equals("skeleton"))
			{
					stack.getTagCompound().setInteger("skeletonkills", stack.getTagCompound().getInteger("skeletonkills")+1);
			}
			if(mob == 1 && stack.getTagCompound().getString("mobzombie").equals("zombie"))
			{
					stack.getTagCompound().setInteger("zombiekills", stack.getTagCompound().getInteger("zombiekills")+1);  
			}
			if(mob == 2 && stack.getTagCompound().getString("mobcreeper").equals("creeper"))
			{
					stack.getTagCompound().setInteger("creeperkills", stack.getTagCompound().getInteger("creeperkills")+1);  
			}
			if(mob == 3 && stack.getTagCompound().getString("mobenderman").equals("enderman"))
			{
					stack.getTagCompound().setInteger("endermankills", stack.getTagCompound().getInteger("endermankills")+1);  
			}
        }
		
	}
	
}
