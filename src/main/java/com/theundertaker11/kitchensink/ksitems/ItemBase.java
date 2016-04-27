package com.theundertaker11.kitchensink.ksitems;

import java.util.List;

import com.theundertaker11.kitchensink.CreativeTabKS;
import com.theundertaker11.kitchensink.KitchenSink;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.client.model.ModelLoader;
import com.theundertaker11.kitchensink.Logger;

public class ItemBase extends Item
{
/*
 * This Class is from JoeTato's mod, I take no credit, as well as the magnets that will come
 * from this class
 * 
 */
	public ItemBase(String name) {
		setUnlocalizedName(name);
		setCreativeTab(KitchenSink.KStab);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {

	}
	
	public String getSimpleName(){
		return this.getUnlocalizedName().substring(5);
	}

    @SideOnly(Side.CLIENT)
    public void initModel() {
    	Logger.info("    Registering model for %s",getSimpleName());
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

}