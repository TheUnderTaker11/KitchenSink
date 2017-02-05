package com.theundertaker11.kitchensink.ksblocks;

import com.theundertaker11.kitchensink.KitchenSink;
import com.theundertaker11.kitchensink.render.IItemModelProvider;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;

public class BlockBase extends Block implements IItemModelProvider{
	protected String Name;
	
	public BlockBase(String name, Material material, float hardness, float resistance) {
        super(material);
        this.Name=name;
        setUnlocalizedName(name);
        setCreativeTab(KitchenSink.KStab);
        setHardness(hardness);
        setResistance(resistance);
        setHarvestLevel("pickaxe", 0);
        setRegistryName(name);
    }

    public BlockBase(String name) {
        this(name, Material.ROCK, 0.5f, 0.5f);
    }

	@Override
	public void registerItemModel(Item itemBlock) {
		KitchenSink.proxy.registerItemRenderer(itemBlock, 0, Name);
	}

}
