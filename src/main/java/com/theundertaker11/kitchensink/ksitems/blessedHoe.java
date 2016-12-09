package com.theundertaker11.kitchensink.ksitems;

import com.theundertaker11.kitchensink.KitchenSink;
import com.theundertaker11.kitchensink.entity.IndestructibleEntityItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class blessedHoe extends ItemHoe {
	
	public blessedHoe(String name, ToolMaterial blessed_mat) {
		super(blessed_mat);
		this.setUnlocalizedName(name);
		this.setCreativeTab(KitchenSink.KStab);
		setMaxDamage(5000);
		this.setRegistryName(name);
	}
	//Next two make it not lose durability when hitting or breaking blocks
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        return false;
    }
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState blockIn, BlockPos pos, EntityLivingBase entityLiving)
    {
        return false;
    }
	//Make it Always gain back its durability
	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int metadata, boolean bool)
	{
		if(itemstack.getItemDamage()>0) itemstack.setItemDamage(0);
	}
	//Tinkers Code
	@Override
	  public boolean hasCustomEntity(ItemStack stack) {
	    return true;
	  }

	  @Override
	  public Entity createEntity(World world, Entity location, ItemStack itemstack) {
	    EntityItem entity = new IndestructibleEntityItem(world, location.posX, location.posY, location.posZ, itemstack);
	    if(location instanceof EntityItem) {
	      // workaround for private access on that field >_>
	      NBTTagCompound tag = new NBTTagCompound();
	      location.writeToNBT(tag);
	      entity.setPickupDelay(tag.getShort("PickupDelay"));
	    }
	    entity.motionX = location.motionX;
	    entity.motionY = location.motionY;
	    entity.motionZ = location.motionZ;
	    return entity;
	  }

}
