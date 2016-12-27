package com.theundertaker11.kitchensink.tileentity;

import com.theundertaker11.kitchensink.KitchenSink;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;

public class KSTileEntityHealingBlock extends TileEntity implements ITickable{
	
	public static double range = KitchenSink.HealingBlockRange;
	public static float healthHealed = KitchenSink.HealingBlockHealthHealed;
	
	private int timer = 0;
	
	public KSTileEntityHealingBlock(){
		super();
	}
	@Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
        super.writeToNBT(compound);
        return compound;
        
    }
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
	}
	@Override
	public void update()
	{
		++timer;
		if(timer>19)
		{
			timer=0;
			World world = getWorld();
			int x = getPos().getX();
			int y = getPos().getY();
			int z = getPos().getZ();
			for (int i = 0; i < world.playerEntities.size(); ++i)
			{
				EntityPlayer entityplayer = (EntityPlayer)world.playerEntities.get(i);

				if (EntitySelectors.NOT_SPECTATING.apply(entityplayer))
				{
					double d0 = entityplayer.getDistanceSq(x, y, z);

					if (range < 0.0D || d0 < ((range * range)+0.5))
					{
						entityplayer.heal(healthHealed);
					}
				}
			}
		}
	}
}
