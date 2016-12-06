package com.theundertaker11.kitchensink.ksitems;

import java.util.List;

import com.theundertaker11.kitchensink.KitchenSink;
import com.theundertaker11.kitchensink.ModUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class TPitem extends Item {
	
	public TPitem(String name)
	{
		super();
		this.setUnlocalizedName(name);
		this.setCreativeTab(KitchenSink.KStab);
		this.setRegistryName(name);
		this.setMaxStackSize(1);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
			if(playerIn.isSneaking()) 
			{
				ModUtils.WritePlayerXYZtoNBT(itemStackIn, playerIn);
			}
			else if(itemStackIn.getTagCompound()!=null)
				{
					ModUtils.TeleportPlayer(playerIn, itemStackIn.getTagCompound().getDouble("x"), itemStackIn.getTagCompound().getDouble("y"), itemStackIn.getTagCompound().getDouble("z"), itemStackIn.getTagCompound().getInteger("dim"));
				}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackIn);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
		tooltip.add(TextFormatting.DARK_PURPLE+ "Shift right click to set teleport point");
		tooltip.add(TextFormatting.DARK_PURPLE+ "Right Click to teleport");	
    }
	
	@Override
	 public boolean hasEffect(ItemStack par1ItemStack)
	 {
		if(par1ItemStack.getItemDamage()==0&&par1ItemStack.getTagCompound()!=null) return true;
		else return false;
	 }
}
