package com.theundertaker11.kitchensink.ksitems;

import java.util.List;
import com.theundertaker11.kitchensink.KitchenSink;
import com.theundertaker11.kitchensink.ModUtils;
import com.theundertaker11.kitchensink.entity.IndestructibleEntityItem;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ModClassLoader;

public class blessedPick extends ItemPickaxe {
	
	protected blessedPick(String name, ToolMaterial blessed_mat) {
		super(blessed_mat);
		this.setUnlocalizedName(name);
		this.setCreativeTab(KitchenSink.KStab);
		this.setRegistryName(name);
		
	}
	String namE = "blessedPick";
	
	
	//adds tooltip(duh?)
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
		tooltip.add("Shift right Click to Deactivate.");
		if (stack.getTagCompound() != null)
        {
			if(stack.getTagCompound().getBoolean("state")==true)
			{
				tooltip.add("Right Click Enabled");
			}
			if(stack.getTagCompound().getBoolean("state")==false)
			{
				tooltip.add("Right Click Disabled");
			}
		
        }
    }
	
	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int metadata, boolean bool)
	{
		if (itemstack.getTagCompound() == null)
        {
			itemstack.setTagCompound(new NBTTagCompound()); 
			String toggle = "Enabled";
			itemstack.getTagCompound().setBoolean("state", true);
        }
		
		if(itemstack.getTagCompound().getBoolean("state")==true)
		{
			String namE = "blessedPick";
		}
		if(itemstack.getTagCompound().getBoolean("state")==false)
		{
			String namE = "blessedPick1";
			String toggle = "Disabled";
		}
		
	}
	

	
	//So when you hold right click you get haste, last 1 second after clicking 
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
		if(!worldIn.isRemote)
		{
			if(playerIn.isSneaking() && itemStackIn.getTagCompound() != null)
			{
			 itemStackIn.getTagCompound().setBoolean("state", itemStackIn.getTagCompound().getBoolean("state") == true ? false : true);
			}
			if(!playerIn.isSneaking()&& itemStackIn.getTagCompound() != null)
			{
				if(itemStackIn.getTagCompound().getBoolean("state")==true)
				{
					playerIn.addPotionEffect((new PotionEffect(Potion.getPotionById(3), 400, 1)));
					playerIn.addPotionEffect((new PotionEffect(Potion.getPotionById(1), 300, 1)));
					playerIn.addPotionEffect((new PotionEffect(Potion.getPotionById(12), 60, 1)));
					itemStackIn.damageItem(5, playerIn);
				}
			}      
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackIn);
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
