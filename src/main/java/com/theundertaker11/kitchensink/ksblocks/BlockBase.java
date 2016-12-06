package com.theundertaker11.kitchensink.ksblocks;

import com.theundertaker11.kitchensink.KitchenSink;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemPickaxe;

public class BlockBase extends Block{
	public BlockBase(String unlocalizedName, Material material, float hardness, float resistance) {
        super(material);
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(KitchenSink.KStab);
        this.setHardness(1);
        this.setResistance(1);
        this.setHarvestLevel("axe", 0);
        this.setRegistryName(unlocalizedName);
    }

    public BlockBase(String unlocalizedName, float hardness, float resistance) {
        this(unlocalizedName, Material.ROCK, hardness, resistance);
    }

    public BlockBase(String unlocalizedName) {
        this(unlocalizedName, 2.0f, 10.0f);
    }

}
