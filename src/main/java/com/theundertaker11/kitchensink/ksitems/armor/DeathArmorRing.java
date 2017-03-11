package com.theundertaker11.kitchensink.ksitems.armor;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Optional;

@Optional.Interface(iface="baubles.api.IBauble", modid="Baubles", striprefs=true)
public class DeathArmorRing extends AngelArmorRing{

	public DeathArmorRing(String name) {
		super(name);
		this.setMaxDamage(0);
	}
}
