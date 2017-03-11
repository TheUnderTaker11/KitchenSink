package com.theundertaker11.kitchensink.ksitems.armor;

import java.util.List;

import com.theundertaker11.kitchensink.KitchenSink;
import com.theundertaker11.kitchensink.ksitems.Itemsss;
import com.theundertaker11.kitchensink.render.IItemModelProvider;
import com.theundertaker11.kitchensink.util.ModUtils;

import baubles.api.cap.BaublesCapabilities;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class KSArmor extends ItemArmor implements IItemModelProvider{

	private String name;
	public KSArmor(String name, boolean isAngel, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		setCreativeTab(KitchenSink.KStab);
		this.name=name;
	}

	@Override
	public void registerItemModel(Item item) {
		KitchenSink.proxy.registerItemRenderer(this, 0, name);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
		if(stack.getTagCompound()==null)
		{
			stack.setTagCompound(new NBTTagCompound());
			NBTTagCompound tag = stack.getTagCompound();
			if(stack.getItem()==Itemsss.blessedHelmet)
			{
				tag.setBoolean("isAngel", true);
			}
			if(stack.getItem()==Itemsss.blessedChestplate)
			{
				tag.setBoolean("flight", false);
				tag.setBoolean("isAngel", true);
			}
			if(stack.getItem()==Itemsss.blessedLeggings)
			{
				tag.setBoolean("isAngel", true);
			}
			if(stack.getItem()==Itemsss.blessedBoots)
			{
				tag.setBoolean("isAngel", true);
			}
		}
    }
	//@Override
	//public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack){}
	/**
	 * Checks if the player is wearing the full set and has the ring.
	 * @param player
	 * @return
	 */
	public static boolean hasFullAngelSet(EntityPlayer player)
	{
		int angelArmorCount=0;
		int hasRing = 0;
		boolean baublesHasRing = false;
		boolean invHasRing = false;
		for(ItemStack stack : player.getArmorInventoryList())
		{
			if(stack!=null&&stack.getTagCompound()!=null&&stack.getItem() instanceof KSArmor)
			{
				NBTTagCompound tag = stack.getTagCompound();
				if(tag.getBoolean("isAngel"))
				{
					++angelArmorCount;
				}
			}
		}
		try{
	    	hasRing = baublesHasActiveRing(player, true);
	    }catch(NoSuchMethodError e){}
		if(hasRing==2) return false;
		else if(hasRing==1) baublesHasRing = true;
		
		int angelRingCount = 0;
		for(int i=0; i<player.inventory.getSizeInventory() ;i++)
		{
			ItemStack stack = player.inventory.getStackInSlot(i);
			if(stack!=null&&stack.getItem()==Itemsss.AngelArmorRing&&stack.getItemDamage()==1)++angelRingCount;
		}
		if(angelRingCount>1) return false;
		if(angelRingCount==0) invHasRing = false;
		if(angelRingCount==1) invHasRing = true;
		
		if((invHasRing&&baublesHasRing)||(!invHasRing&&!baublesHasRing)) return false;
		else if(angelArmorCount==4) return true;
		return false;
	}
	/**
	 * Checks if the baubles slots has the active, will return false if both are there!
	 * @param player
	 * @param isAngel True to check for angel ring, false to check for death ring
	 * @return 0 for false, 1 for true, 2 if there is 2 active rings
	 */
	@Optional.Method(modid="Baubles")
	public static int baublesHasActiveRing(EntityPlayer player, boolean isAngel)
	{
		int should = 0;
		IBaublesItemHandler baubles = player.getCapability(BaublesCapabilities.CAPABILITY_BAUBLES, player.getHorizontalFacing());
		boolean hasAngel = false;
		boolean hasDeath = false;
		for(int i=0;i<baubles.getSlots();++i)
		{
			ItemStack stack = baubles.getStackInSlot(i);
			if(stack!=null)
			{
				if(stack.getItem()==Itemsss.AngelArmorRing&&stack.getItemDamage()==1)hasAngel = true;
				if(stack.getItem()==Itemsss.DeathArmorRing&&stack.getItemDamage()==1)hasDeath = true;
			}
		}
		if(hasAngel&&hasDeath) return 2;
		
		if(isAngel&&hasAngel) should = 1;
		if(!isAngel&&hasDeath) should = 1;
		return should;
	}
	public boolean hasFullDeathSet(EntityPlayer player)
	{
		int deathcount=0;
		for(ItemStack stack : player.getArmorInventoryList())
		{
			if(stack!=null&&stack.getTagCompound()!=null&&stack.getItem() instanceof KSArmor)
			{
				NBTTagCompound tag = stack.getTagCompound();
				if(!tag.getBoolean("isAngel"))
				{
					++deathcount;
				}
			}
		}
		if(deathcount==4) return true;
		return false;
	}
}
