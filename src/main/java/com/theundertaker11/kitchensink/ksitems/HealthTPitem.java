package com.theundertaker11.kitchensink.ksitems;

import java.util.List;

import com.theundertaker11.kitchensink.KitchenSink;
import com.theundertaker11.kitchensink.ModUtils;

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

public class HealthTPitem extends Item {
	public HealthTPitem(String name)
	{
		super();
		this.setUnlocalizedName(name);
		this.setCreativeTab(KitchenSink.KStab);
		this.setRegistryName(name);
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
		if(entity instanceof EntityPlayer)
		{
			if (item.getTagCompound() == null)
			{
				item.setTagCompound(new NBTTagCompound());
				item.getTagCompound().setInteger("dur", 400);
				item.getTagCompound().setInteger("maxdur", 400);
				ModUtils.WritePlayerXYZtoNBT(item, (EntityPlayer)entity);
			}
		}
		if(!world.isRemote)
		{
			if(item.getTagCompound()!=null && entity instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer)entity;
				if(player.getHealth()<=(player.getMaxHealth()*0.2F)&&item.getTagCompound().getInteger("dur")==item.getTagCompound().getInteger("maxdur"))
				{
						player.addChatMessage(new TextComponentString(TextFormatting.RED+"Health Critical, Teleporting Now"));
						ModUtils.TeleportPlayer(player, item.getTagCompound().getDouble("x"), item.getTagCompound().getDouble("y"), item.getTagCompound().getDouble("z"), item.getTagCompound().getInteger("dim"));
						item.getTagCompound().setInteger("dur", 0);
				}
				
				
			}
			
			
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
		tooltip.add(TextFormatting.DARK_PURPLE+ "Shift right click to set teleport point");
		tooltip.add(TextFormatting.DARK_PURPLE+ "At 20% health this will Teleport you to place you set");
		tooltip.add(TextFormatting.DARK_PURPLE+ "There is a cooldown, shown by duribilty");
		tooltip.add(TextFormatting.DARK_PURPLE+ "(Note:Don't keep more than one in your inventory)");
    }
	
	public static void TimerRepair(ItemStack item)
	{
		if(item.getTagCompound().getInteger("dur")<item.getTagCompound().getInteger("maxdur")) item.getTagCompound().setInteger("dur", (item.getTagCompound().getInteger("dur")+1));
	}
	
	@Override
	 public boolean hasEffect(ItemStack par1ItemStack)
	 {
		if(par1ItemStack.getTagCompound()!=null&&par1ItemStack.getTagCompound().getInteger("dur")==par1ItemStack.getTagCompound().getInteger("maxdur")) return true;
		else return false;
	 }
	
	@Override
	public boolean showDurabilityBar(ItemStack stack)
    {
		if(stack.getTagCompound()!=null&&stack.getTagCompound().getInteger("dur")<stack.getTagCompound().getInteger("maxdur")) return true;
		else return false;
    }
	/**Makes it so duribility bar still shows even though I'm using meta damage for textures */
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
