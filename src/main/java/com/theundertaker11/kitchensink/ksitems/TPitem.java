package com.theundertaker11.kitchensink.ksitems;

import java.util.List;

import com.theundertaker11.kitchensink.KitchenSink;
import com.theundertaker11.kitchensink.util.ModUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class TPitem extends ItemBase {
	
	public TPitem(String name)
	{
		super(name);
		this.setMaxStackSize(1);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
		if(!worldIn.isRemote)
		{
			if(playerIn.isSneaking()) 
			{
				ModUtils.WritePlayerXYZtoNBT(stack, playerIn);
			}
			else if(stack.getTagCompound()!=null)
			{
				NBTTagCompound tag=stack.getTagCompound();
				ModUtils.TeleportPlayer(playerIn, tag.getDouble("x"), tag.getDouble("y"), tag.getDouble("z"), tag.getInteger("dim"));
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
		tooltip.add(TextFormatting.DARK_PURPLE+ "Shift right click to set teleport point");
		tooltip.add(TextFormatting.DARK_PURPLE+ "Right Click to teleport");	
    }
	
	@Override
	 public boolean hasEffect(ItemStack stack)
	 {
		if(stack.getItemDamage()==0&&stack.getTagCompound()!=null) return true;
		else return false;
	 }
}
