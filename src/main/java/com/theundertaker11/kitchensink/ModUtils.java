package com.theundertaker11.kitchensink;


import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public final class ModUtils
{
	public int moveSpeed = 1;
	public int moveSlowness = 2;
	public int digSpeed = 3;
	public int miningSlowDown = 4;
	public int strength = 5;
	public int jumpBoost = 8;
	public int nausea = 9;
	public int regeneration = 10;
	public int resistance = 11;
	public int fireResistance = 12;
	public int waterBreathing = 13;
	public int invisibility = 14;
	public int blindness = 15;
	public int nightVision = 16;
	public int hunger = 17;
	public int weakness = 18;
	public int poison = 19;
	public int wither = 20;
	
	public static List<Entity> getEntitiesInRange(Class<? extends Entity> entityType, World world, double x, double y, double z, double radius) {
		return getEntitesInTange(entityType, world, x - radius, y - radius, z - radius, x + radius, y + radius,
				z + radius);
	}

	public static List<Entity> getEntitesInTange(Class<? extends Entity> entityType, World world, double x, double y, double z, double x2,
			double y2, double z2) {
		return world.getEntitiesWithinAABB(entityType, new AxisAlignedBB(x, y, z, x2, y2, z2));
	}
	
	public static EnumFacing getFacingFromEntity(BlockPos clickedBlock, EntityLivingBase entity) {
        return EnumFacing.getFacingFromVector(
             (float) (entity.posX - clickedBlock.getX()),
             (float) (entity.posY - clickedBlock.getY()),
             (float) (entity.posZ - clickedBlock.getZ()));
    }
}
