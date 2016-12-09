package com.theundertaker11.kitchensink.ksitems;

import java.util.List;

import com.theundertaker11.kitchensink.KitchenSink;
import com.theundertaker11.kitchensink.Refernce;
import com.theundertaker11.kitchensink.entity.IndestructibleEntityItem;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DeathHand extends ItemSword
{
	public static DamageSource Death = (new DamageSource("death").setDamageBypassesArmor().setDamageIsAbsolute().setMagicDamage());
	public DeathHand(String name, ToolMaterial mat) {
		super(mat);
		this.setUnlocalizedName(name);
        this.setCreativeTab(KitchenSink.KStab);
        this.setRegistryName(name);
	}
	
	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn)
    {
		if(stack.getTagCompound()!=null)
		{
			stack.getTagCompound().setString("ownerID", playerIn.getUniqueID().toString());
			stack.getTagCompound().setString("owner", playerIn.getName());
			stack.getTagCompound().setBoolean("active", false);
		}
    }
	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int metadata, boolean bool)
	{
		//Fixes it when it loses duribility
		if (itemstack.getItemDamage() != 0)
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
				itemstack.getTagCompound().setBoolean("active", false);
			}
			
		}
		
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
		if(attacker instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)attacker;
			//Checks if the player holding it is the one who made it(or held it first)
			if(!(target instanceof EntityPlayer)&&stack.getTagCompound()!=null&&stack.getTagCompound().getString("ownerID").equals(player.getUniqueID().toString()))
			{
				//Damages entity with playerdamage and my Death damage
				target.attackEntityFrom(DamageSource.causePlayerDamage(player), 10000);
				target.attackEntityFrom(DeathHand.Death, 10000);
				//If they are not dead then this will either put them at 1/2 a heart or kill them.
				if(target.isEntityAlive())
				{
						if(stack.getTagCompound().getBoolean("active")) target.setHealth(0);
						else target.setHealth(1);
				}
			}
			else target.attackEntityFrom(DamageSource.causePlayerDamage(player), 2000);
					
		
	 }
        return true;
    }
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
		if(stack.getTagCompound()!=null)
		{
			if(stack.getTagCompound().getBoolean("active")&&stack.getTagCompound().getString("ownerID").equals(playerIn.getUniqueID().toString()))
			{
				tooltip.add(TextFormatting.RED+"Force Killing Enabled");
			}
			else tooltip.add(TextFormatting.BLUE+"Force Killing Disabled");
			
			String owner = stack.getTagCompound().getString("owner");
			tooltip.add("Owner: " + owner);
			if(!stack.getTagCompound().getString("ownerID").equals(playerIn.getUniqueID().toString()))
			{
				tooltip.add(TextFormatting.DARK_RED + "You are not the owner");
			}
		}
		else{
			tooltip.add("Shift right click to toggle force kill");
			tooltip.add("(Force kill MAY not drop items)");
			tooltip.add(TextFormatting.BOLD + "Mobs hit when inactive will die normally ");
			tooltip.add(TextFormatting.BOLD + "or be put to half health");
			tooltip.add(TextFormatting.BLUE+"Force Killing Disabled");
			tooltip.add("Owner: (Owner Has Not Been Set)" );
		}
		
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
		if(itemStackIn.getTagCompound()==null)
		{
			itemStackIn.setTagCompound(new NBTTagCompound());
			itemStackIn.getTagCompound().setBoolean("active", false);
		}
		
		if(playerIn.isSneaking()&&itemStackIn.getTagCompound().getString("ownerID").equals(playerIn.getUniqueID().toString()))
		{
			itemStackIn.getTagCompound().setBoolean("active", (!itemStackIn.getTagCompound().getBoolean("active")));
			playerIn.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 0.8F, 0.5F);
		}
	    return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackIn);
	 }
	 
	 @Override
	 public boolean hasEffect(ItemStack par1ItemStack)
	 {
		if(par1ItemStack.getTagCompound()!=null)
		{
			return par1ItemStack.getTagCompound().getBoolean("active");
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
