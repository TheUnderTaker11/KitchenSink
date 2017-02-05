package com.theundertaker11.kitchensink.ksitems.tools;

import java.util.List;
import java.util.UUID;

import com.theundertaker11.kitchensink.KitchenSink;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class deathsSythe extends blessedSword {
	
		public deathsSythe(String name, ToolMaterial blessed_mat) 
		{
		super(name, blessed_mat);
		setMaxDamage(10000);
		}

	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int metadata, boolean bool)
	{
		//Fixes it when it loses duribility
		if (itemstack.getItemDamage()>0)
		{
			itemstack.setItemDamage(0);
		}
		if(itemstack.getTagCompound() == null)
		{
			if (entity instanceof EntityPlayer)
			{
				itemstack.setTagCompound(new NBTTagCompound());  
				itemstack.getTagCompound().setString("ownerID", entity.getUniqueID().toString());
				itemstack.getTagCompound().setString("owner", entity.getName());
			}
			
		}
		
	}
	
	
			//adds tooltip
			@Override
			public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
		    {
				tooltip.add("Death Takes Us All");
				//Adds the owners name and if the person holding it is the owner.
				if(stack.getTagCompound() != null)
				{
					tooltip.add("Owner: "+stack.getTagCompound().getString("owner"));
					if(!stack.getTagCompound().getString("ownerID").equals(playerIn.getUniqueID().toString()))
					{
						tooltip.add(TextFormatting.DARK_RED + "You are not the owner");
					}
				}
				else tooltip.add("Owner: (Owner Has Not Been Set)");
				
		    }
			
			//So when you right click it gives even resistance so you can still hit instead of blocking
			@Override
			public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
			{    
				if(itemStackIn.getTagCompound() != null)
				{
					if(playerIn.getUniqueID().toString().equals(itemStackIn.getTagCompound().getString("ownerID")) && itemStackIn.getTagCompound() != null)
					{
					playerIn.addPotionEffect((new PotionEffect(Potion.getPotionById(11), 200, 1)));
					}
				}
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
			}
	//Makes the name look cool
	@Override
	public EnumRarity getRarity(ItemStack stack)
    {
        return EnumRarity.RARE;
    }
	
	//makes it hurt vanilla mobs a lot
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
		if(stack.getTagCompound() != null)
		{
			if(player.getUniqueID().toString().equals(stack.getTagCompound().getString("ownerID")))
			{
				if(entity instanceof EntityMob || entity instanceof EntityGolem)
				{
					float damage = 200f;
					entity.attackEntityFrom(DamageSource.causePlayerDamage(player), damage);
				}
			}
		}
		//Makes it so players take 10 damage that bypasses armor
		if(entity instanceof EntityPlayer)
		{
			float damage = 10f;
			entity.attackEntityFrom(DamageSource.outOfWorld, damage);
		}
        return false;
    }
}
