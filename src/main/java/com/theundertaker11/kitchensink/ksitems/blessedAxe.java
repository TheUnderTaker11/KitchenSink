package com.theundertaker11.kitchensink.ksitems;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.theundertaker11.kitchensink.KitchenSink;
import com.theundertaker11.kitchensink.entity.IndestructibleEntityItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class blessedAxe extends ItemTool {
	
	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(new Block[] {Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2, Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin, Blocks.melon_block, Blocks.ladder, Blocks.wooden_button, Blocks.wooden_pressure_plate});
    private static final float[] ATTACK_DAMAGES = new float[] {6.0F, 8.0F, 8.0F, 8.0F, 6.0F};
    private static final float[] ATTACK_SPEEDS = new float[] { -3.2F, -3.2F, -3.1F, -3.0F, -3.0F};

    protected blessedAxe(ToolMaterial material)
    {
        super(material, EFFECTIVE_ON);
        this.attackSpeed = 0.24F;
        this.setRegistryName("blessedAxe");
        this.setUnlocalizedName("blessedAxe");
        this.setCreativeTab(KitchenSink.KStab);
    }

    public float getStrVsBlock(ItemStack stack, IBlockState state)
    {
        Material material = state.getMaterial();
        return material != Material.wood && material != Material.plants && material != Material.vine ? super.getStrVsBlock(stack, state) : this.efficiencyOnProperMaterial;
    }
    
    @Override
	public Set<String> getToolClasses(ItemStack stack) 
    {
	    return ImmutableSet.of("axe");
	}
	//Next two make it not lose durability when hitting or breaking blocks
		@Override
		public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
	    {
	        return false;
	    }
		@Override
		public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState blockIn, BlockPos pos, EntityLivingBase entityLiving)
	    {
	        return false;
	    }
	    

	//Tinkers Code
	@Override
	  public boolean hasCustomEntity(ItemStack stack) {
	    return true;
	  }

	  @Override
	  public Entity createEntity(World world, Entity location, ItemStack itemstack) {
	    EntityItem entity = new IndestructibleEntityItem(world, location.posX, location.posY, location.posZ, itemstack);
	    if(location instanceof EntityItem) {
	      // workaround for private access on that field >_>
	      NBTTagCompound tag = new NBTTagCompound();
	      location.writeToNBT(tag);
	      entity.setPickupDelay(tag.getShort("PickupDelay"));
	    }
	    entity.motionX = location.motionX;
	    entity.motionY = location.motionY;
	    entity.motionZ = location.motionZ;
	    return entity;
	  }

}
