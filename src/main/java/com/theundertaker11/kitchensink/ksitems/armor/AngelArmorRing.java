package com.theundertaker11.kitchensink.ksitems.armor;

import java.util.List;

import com.theundertaker11.kitchensink.event.KSEventHandler;
import com.theundertaker11.kitchensink.ksitems.ItemBase;
import com.theundertaker11.kitchensink.ksitems.Itemsss;
import com.theundertaker11.kitchensink.ksitems.armor.angelhandler.AngelArmorHandler;
import com.theundertaker11.kitchensink.util.ModUtils;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Optional.Interface(iface="baubles.api.IBauble", modid="Baubles", striprefs=true)
public class AngelArmorRing extends ItemBase implements IBauble{

	public AngelArmorRing(String name) {
		super(name);
		this.setMaxDamage(0);
		this.setMaxStackSize(1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
		if(stack.getTagCompound()==null) return;
		
		NBTTagCompound tag = stack.getTagCompound();
		if(tag.getBoolean("flight")) tooltip.add("Flight");
		if(tag.hasKey("speed"))
		{
			if(tag.getInteger("speed")==-1) tooltip.add(TextFormatting.BOLD+"Upgrades Movment speed, max level V"); 
			else tooltip.add("Speed "+ModUtils.getRomanNum(tag.getInteger("speed")));
		}
    } 
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
		if(stack.getTagCompound()==null)
		{
			stack.setTagCompound(new NBTTagCompound());
			stack.getTagCompound().setBoolean("isAngel", true);
		}
    }
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
		if(!worldIn.isRemote&&playerIn.isSneaking())
		{
			if(stack.getItemDamage()==0)
			{
				stack.setItemDamage(1);
				AngelArmorHandler.addArmor(playerIn);
			}
			else stack.setItemDamage(0);
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
    }
	
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack)
	{
		if(stack.getItemDamage()==0) return false;
		else return true;
	}
	
	@Optional.Method(modid="Baubles")
	@Override
	public BaubleType getBaubleType(ItemStack arg0) 
	{
		return BaubleType.RING;
	}
}
