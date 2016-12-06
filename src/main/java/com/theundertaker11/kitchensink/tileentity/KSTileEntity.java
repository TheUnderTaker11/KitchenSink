package com.theundertaker11.kitchensink.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.ibm.icu.impl.duration.impl.Utils;
import com.theundertaker11.kitchensink.Refernce;
import com.theundertaker11.kitchensink.tileentity.KSTileEntityStorageBlock;

public class KSTileEntity {
	public static void regTileEntitys()
	{
		GameRegistry.registerTileEntity(KSTileEntityStorageBlock.class, "KSTileEntityStorageBlock");
		
		
		
	}

}
