package com.theundertaker11.kitchensink.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class KSTileEntity {
	public static void regTileEntitys()
	{
		GameRegistry.registerTileEntity(KSTileEntityHealingBlock.class, "KSTileEntityHealingBlock");
		GameRegistry.registerTileEntity(KSTileEntityQuarryBlock.class, "KSTileEntityQuarryBlock");
		GameRegistry.registerTileEntity(KSTileEntityTrashChest.class, "KSTileEntityTrashChest");
	}
}
