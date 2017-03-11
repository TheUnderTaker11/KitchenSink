package com.theundertaker11.kitchensink.tileentity;

import com.theundertaker11.kitchensink.ksblocks.blessedfurnace.KSTileEntityBlessedFurnace;
import com.theundertaker11.kitchensink.ksblocks.quarry.KSTileEntityQuarryBlock;
import com.theundertaker11.kitchensink.ksblocks.trashchest.KSTileEntityTrashChest;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class KSTileEntityRegistry {
	public static void regTileEntitys()
	{
		GameRegistry.registerTileEntity(KSTileEntityHealingBlock.class, "KSTileEntityHealingBlock");
		GameRegistry.registerTileEntity(KSTileEntityQuarryBlock.class, "KSTileEntityQuarryBlock");
		GameRegistry.registerTileEntity(KSTileEntityTrashChest.class, "KSTileEntityTrashChest");
		GameRegistry.registerTileEntity(KSTileEntityBlessedFurnace.class, "KSTileEntityBlessedFurnace");
	}
}
