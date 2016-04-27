package com.theundertaker11.kitchensink.ksitems;

import java.util.List;
import com.theundertaker11.kitchensink.KitchenSink;
import com.theundertaker11.kitchensink.entity.IndestructibleEntityItem;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class blessedSword extends ItemSword {
	
	public blessedSword(String name, ToolMaterial blessed_mat) {
		super(blessed_mat);
		this.setUnlocalizedName(name);
		this.setCreativeTab(KitchenSink.KStab);
		this.setRegistryName(name);
		
	}
	

		//adds tooltip(duh?)
		@Override
		public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
	    {
			tooltip.add("Blessings Work Rather Well on Zombies");
	    }
		
		//So when you block it gives even more defense
		@Override
		public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
		{    
			playerIn.addPotionEffect((new PotionEffect(Potion.getPotionById(11), 10, 1)));
		    return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
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

		//makes it hurt zombies a lot
		@Override
		public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	    {
			if(entity instanceof EntityZombie)
			{
			float damage = 100f;
			entity.attackEntityFrom(DamageSource.causePlayerDamage(player), damage);
			}
	        return false;
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
