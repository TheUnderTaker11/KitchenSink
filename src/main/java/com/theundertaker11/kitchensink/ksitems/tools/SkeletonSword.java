package com.theundertaker11.kitchensink.ksitems.tools;

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
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {	
	 if(stack.getTagCompound() != null)
	 {
		 tooltip.add(TextFormatting.RED + stack.getTagCompound().getString("mob")+" Souls: " + stack.getTagCompound().getInteger("kills"));
	 }
	 else
	 {
		 tooltip.add("No Soul Type Set Yet");
	 }
	 
	 
    }
	
	public static void addKill(ItemStack stack, String mob)
	{
		if (stack.getTagCompound() != null&&stack.getTagCompound().getInteger("kills")<50)
        {
			if(stack.getTagCompound().getString("mob").equals(mob))
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
			itemstack.getTagCompound().setString("mob", "Zombie");
			itemstack.getTagCompound().setInteger("kills", 0);
        }
	}
	
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
		NBTTagCompound tag = stack.getTagCompound();
		if (tag != null&&entity instanceof EntityLivingBase)
        {
			if(tag.getInteger("kills") > 49)
			{
				if(tag.getString("mob").equals(entity.getClass().getSimpleName().substring(6)))
				{
					entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 75F);
				}
			}
        }
        return false;
    }
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
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
				return true;
			}
        }
		if ((double)blockIn.getBlockHardness(worldIn, pos) != 0.0D)
        {
            stack.damageItem(2, entityLiving);
        }

        return true;
    }
	
	@Override
    public boolean hasEffect(ItemStack stack)
    {
		if (stack.getTagCompound() != null)
        {
			if(stack.getTagCompound().getInteger("kills") > 49)
			{
				return true;
			}
        }
	    return false;
    }
	
	
	
}
