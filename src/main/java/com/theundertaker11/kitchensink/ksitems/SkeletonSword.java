package com.theundertaker11.kitchensink.ksitems;

import java.util.List;

import com.theundertaker11.kitchensink.KitchenSink;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
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
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class SkeletonSword extends ItemSword {

	public SkeletonSword(String name, ToolMaterial low_mat) {
		super(low_mat);
		this.setUnlocalizedName(name);
		this.setCreativeTab(KitchenSink.KStab);
		setMaxDamage(500);
		this.setRegistryName(name);
	}

	
	@Override
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player){
		if (itemStack.getTagCompound() == null)
        {
			itemStack.setTagCompound(new NBTTagCompound());
        }
			itemStack.getTagCompound().setInteger("kills", 50);
			
			if(itemStack.getTagCompound() != null)
			  {
				if(itemStack.getTagCompound().getString("mob").equals("zombie"))
				{
					itemStack.getTagCompound().setString("mob", "zombie");
				}
				if(itemStack.getTagCompound().getString("mob").equals("skeleton"))
				{
					itemStack.getTagCompound().setString("mob", "skeleton");
				}
				if(itemStack.getTagCompound().getString("mob").equals("creeper"))
				{
					itemStack.getTagCompound().setString("mob", "creeper");
				}
				if(itemStack.getTagCompound().getString("mob").equals("enderman"))
				{
					itemStack.getTagCompound().setString("mob", "enderman");
				}
			  }
	}
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {	
	 if(stack.getTagCompound() != null)
	 {	
		 	int intKills = stack.getTagCompound().getInteger("kills");
		 	String stringKills = intKills + "";
			 if(stack.getTagCompound().getString("mob").equals("skeleton"))
			 {
				 tooltip.add(TextFormatting.RED + "Skeleton Souls: " + stringKills);
			 }
			 if(stack.getTagCompound().getString("mob").equals("zombie"))
			 {
				 tooltip.add(TextFormatting.RED + "Zombie Souls: " + stringKills);
			 }
			 if(stack.getTagCompound().getString("mob").equals("creeper"))
			 {
				 tooltip.add(TextFormatting.RED + "Creeper Souls: " + stringKills);
			 }
			 if(stack.getTagCompound().getString("mob").equals("enderman"))
			 {
				 tooltip.add(TextFormatting.RED + "Enderman Souls: " + stringKills);
			 }
	 }
	 
	 
    }
	
	public static void addKill(ItemStack stack, int mob)
	{
		if (stack.getTagCompound() != null)
        {
			if(mob == 0 && stack.getTagCompound().getString("mob").equals("skeleton"))
			{
					stack.getTagCompound().setInteger("kills", stack.getTagCompound().getInteger("kills")+1);
			}
			if(mob == 1 && stack.getTagCompound().getString("mob").equals("zombie"))
			{
					stack.getTagCompound().setInteger("kills", stack.getTagCompound().getInteger("kills")+1);  
			}
			if(mob == 2 && stack.getTagCompound().getString("mob").equals("creeper"))
			{
					stack.getTagCompound().setInteger("kills", stack.getTagCompound().getInteger("kills")+1);  
			}
			if(mob == 3 && stack.getTagCompound().getString("mob").equals("enderman"))
			{
					stack.getTagCompound().setInteger("kills", stack.getTagCompound().getInteger("kills")+1);  
			}
        }
		
	}
	
	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int metadata, boolean bool)
	{
		if (itemstack.getTagCompound() == null)
        {
			itemstack.setTagCompound(new NBTTagCompound());  
        }
		if(itemstack.getTagCompound().getInteger("kills") >= 50)
		{
			itemstack.setItemDamage(0);
		}
	  
	}
	
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
		if (stack.getTagCompound() != null)
        {
		if(stack.getTagCompound().getInteger("kills") >= 50)
		{
			if(stack.getTagCompound().getString("mob").equals("skeleton"))
			{
				if(entity instanceof EntitySkeleton)
				{
					float damage = 75f;
					entity.attackEntityFrom(DamageSource.causePlayerDamage(player), damage);
				}
			}	
			if(stack.getTagCompound().getString("mob").equals("zombie"))
			{
				if(entity instanceof EntityZombie)
				{
					float damage = 75f;
					entity.attackEntityFrom(DamageSource.causePlayerDamage(player), damage);
				}
			}
			if(stack.getTagCompound().getString("mob").equals("creeper"))
			{
				if(entity instanceof EntityCreeper)
				{
					float damage = 75f;
					entity.attackEntityFrom(DamageSource.causePlayerDamage(player), damage);
				}
			}
			if(stack.getTagCompound().getString("mob").equals("enderman"))
			{
				if(entity instanceof EntityEnderman)
				{
					float damage = 75f;
					entity.attackEntityFrom(DamageSource.causePlayerDamage(player), damage);
				}
			}
		}
        }
        return false;
    }
	
	/*
	 * 
	 * 
	 * 
	 */
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
		if (stack.getTagCompound() != null)
        {
			if(stack.getTagCompound().getInteger("kills") >= 50)
			{
				return false;
			}
        }
		stack.damageItem(1, attacker);
		return true;
        
    }
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState blockIn, BlockPos pos, EntityLivingBase entityLiving)
    {
		if(!(entityLiving instanceof EntityPlayer)) return false;
		if (stack.getTagCompound() != null)
        {
			if(stack.getTagCompound().getInteger("kills") >= 50)
			{
			return false;
			}
        }
		if ((double)blockIn.getBlockHardness(worldIn, pos) != 0.0D)
        {
            stack.damageItem(2, entityLiving);
        }

        return true;
    }
	
	@Override
    public boolean hasEffect(ItemStack par1ItemStack)
    {
		if (par1ItemStack.getTagCompound() != null)
        {
			if(par1ItemStack.getTagCompound().getInteger("kills") > 49)
			{
	    	return true;
			}
        }
	    return false;
    }
	
	
	
}
