package com.theundertaker11.kitchensink.ksitems;

import java.util.List;

import com.theundertaker11.kitchensink.KitchenSink;
import com.theundertaker11.kitchensink.util.ModUtils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class HealthTPitem extends ItemBase {
	public HealthTPitem(String name)
	{
		super(name);
		this.setMaxDamage(0);
		this.setMaxStackSize(1);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
		if(playerIn.isSneaking())
		{
			ModUtils.WritePlayerXYZtoNBT(itemStackIn, playerIn);
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackIn);
	}
	
	@Override
	public void onUpdate(ItemStack item, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) 
	{
		if(!world.isRemote&&entity instanceof EntityPlayer)
		{
			if (item.getTagCompound() == null)
			{
				item.setTagCompound(new NBTTagCompound());
				item.getTagCompound().setInteger("dur", 400);
				item.getTagCompound().setInteger("maxdur", 400);
				ModUtils.WritePlayerXYZtoNBT(item, (EntityPlayer)entity);
			}
			else
			{
				NBTTagCompound tag=item.getTagCompound();
				EntityPlayer player = (EntityPlayer)entity;
				if(player.getHealth()<=(player.getMaxHealth()*0.2F)&&tag.getInteger("dur")==tag.getInteger("maxdur"))
				{
						player.addChatMessage(new TextComponentString(TextFormatting.RED+"Health Critical, Teleporting Now"));
						ModUtils.TeleportPlayer(player, tag.getDouble("x"), tag.getDouble("y"), tag.getDouble("z"), tag.getInteger("dim"));
						tag.setInteger("dur", 0);
				}
			}
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
		tooltip.add(TextFormatting.DARK_PURPLE+ "Shift right click to set teleport point");
    }
	
	public static void TimerRepair(ItemStack item)
	{
		NBTTagCompound tag = item.getTagCompound();
		if(tag.getInteger("dur")<tag.getInteger("maxdur")) tag.setInteger("dur", (tag.getInteger("dur")+1));
	}
	
	@Override
	 public boolean hasEffect(ItemStack stack)
	 {
		if(stack.getTagCompound()!=null&&stack.getTagCompound().getInteger("dur")==stack.getTagCompound().getInteger("maxdur")) return true;
		else return false;
	 }
	
	@Override
	public boolean showDurabilityBar(ItemStack stack)
    {
		if(stack.getTagCompound()!=null&&stack.getTagCompound().getInteger("dur")<stack.getTagCompound().getInteger("maxdur")) return true;
		else return false;
    }
	
	@Override
	public double getDurabilityForDisplay(ItemStack stack)
    {
		if(stack.getTagCompound()!=null)
		{
			return ((double)stack.getTagCompound().getInteger("maxdur")-(double)stack.getTagCompound().getInteger("dur"))/(double)stack.getTagCompound().getInteger("maxdur");
		}
		else return 1;
    }

}
