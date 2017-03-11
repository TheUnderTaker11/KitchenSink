package com.theundertaker11.kitchensink.ksitems.armor.angelhandler;

import java.util.UUID;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/**
 * This and the other handler classes all only work with the chestplate, but are sorted like this
 * for organization sake.
 * @author TheUnderTaker11
 */
public class AngelLegHandler {
	
	protected static final String MODIFIER_SPEED_NAME = "Add Speed";
	protected static final UUID MODIFIER_SPEED_ID = UUID.fromString("d35cafc0-3ffb-4543-b3fe-44773375f2e2");

	public static void handle(EntityPlayer player, ItemStack stack)
	{
		NBTTagCompound tag = stack.getTagCompound();
		if(tag.hasKey("speed"))
		{
			final IAttributeInstance attribute = player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
			AttributeModifier addSpeed = new AttributeModifier(MODIFIER_SPEED_ID, MODIFIER_SPEED_NAME, (double)tag.getInteger("speed")*0.1D, 0);
			if(!attribute.hasModifier(addSpeed))
			{
				attribute.applyModifier(addSpeed);
			}
		}
	}
	
	/**
	 * If my speed modifier is on the player, remove it from the FOV calculation.
	 * @param event
	 */
	@SubscribeEvent
	public void onFOVChange(FOVUpdateEvent event)
	{
		EntityPlayer player = event.getEntity();
		float f = 1.0F;
        if (player.capabilities.isFlying)
        {
            f *= 1.1F;
        }
        IAttributeInstance iattributeinstance = player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
        //This if I check if they have my speed modifier, if they do, remove it from the FOV equation
        if(iattributeinstance.getModifier(MODIFIER_SPEED_ID)!=null)
        f = (float)((double)f * (((iattributeinstance.getAttributeValue()-iattributeinstance.getModifier(MODIFIER_SPEED_ID).getAmount()) / (double)player.capabilities.getWalkSpeed() + 1.0D) / 2.0D));
        else f = (float)((double)f * ((iattributeinstance.getAttributeValue() / (double)player.capabilities.getWalkSpeed() + 1.0D) / 2.0D));
        
        if (player.capabilities.getWalkSpeed() == 0.0F || Float.isNaN(f) || Float.isInfinite(f))
        {
            f = 1.0F;
        }
        //This does calculation for the bow pullback.
        if (player.isHandActive() && player.getActiveItemStack() != null && player.getActiveItemStack().getItem() == Items.BOW)
        {int i = player.getItemInUseMaxCount();
            float f1 = (float)i / 20.0F;
            if (f1 > 1.0F){f1 = 1.0F;}
            else{f1 = f1 * f1;}
            f *= 1.0F - f1 * 0.15F;}
        event.setNewfov(f);
	}
}
