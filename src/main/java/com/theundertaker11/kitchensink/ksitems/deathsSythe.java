package com.theundertaker11.kitchensink.ksitems;

import java.util.List;
import java.util.UUID;

import com.theundertaker11.kitchensink.KitchenSink;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class deathsSythe extends blessedSword {
	
		public deathsSythe(String name, ToolMaterial blessed_mat) 
		{
		super(name, blessed_mat);
		this.setUnlocalizedName(name);
		setMaxDamage(10000);
		this.setCreativeTab(KitchenSink.KStab);
		}

	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int metadata, boolean bool)
	{
		//Fixes it when it loses duribility
		if (itemstack.getItemDamage() != 0)
		{
			itemstack.setItemDamage(0);
		}
		if(entity instanceof EntityPlayer)
		{
			if (itemstack.getTagCompound() == null)
			{
				itemstack.setTagCompound(new NBTTagCompound());  
				itemstack.getTagCompound().setString("ownerID", entity.getUniqueID().toString());
				itemstack.getTagCompound().setString("owner", entity.getName());
			}
			
		}
		
	}
	
	
			//adds tooltip
			@Override
			public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
		    {
				tooltip.add("Death Takes Us All");
				//Adds the owners name and if the person holding it is the owner.
				if(stack.getTagCompound() != null)
				{
					String owner = stack.getTagCompound().getString("owner");
					tooltip.add("Owner:" + owner);
					if(!stack.getTagCompound().getString("ownerID").equals(playerIn.getUniqueID().toString()))
					{
						tooltip.add(TextFormatting.DARK_RED + "You are not the owner");
					}
				}
				
		    }
			
			//So when you right click it gives even resistance so you can still hit instead of blocking
			@Override
			public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
			{    
				if(itemStackIn.getTagCompound() != null)
				{
					if(playerIn.getUniqueID().toString().equals(itemStackIn.getTagCompound().getString("ownerID")) && itemStackIn.getTagCompound() != null)
					{
					playerIn.addPotionEffect((new PotionEffect(Potion.getPotionById(11), 200, 1)));
					}
				}
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
			}
	//Makes the name look cool
	@Override
	public EnumRarity getRarity(ItemStack stack)
    {
        return EnumRarity.RARE;
    }
	
	//makes it hurt vanilla mobs a lot
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
		if(stack.getTagCompound() != null)
		{
			if(player.getUniqueID().toString().equals(stack.getTagCompound().getString("ownerID")))
			{
				if(entity instanceof EntityMob || entity instanceof EntityGolem)
				{
					float damage = 200f;
					entity.attackEntityFrom(DamageSource.causePlayerDamage(player), damage);
				}
			}
		}
		//Makes it so players take 20 damage that bypasses armor
		if(entity instanceof EntityPlayer)
		{
			float damage = 10f;
			entity.attackEntityFrom(DamageSource.starve, damage);
			entity.attackEntityFrom(DamageSource.outOfWorld, damage);
		}
        return false;
    }
	
	//lets it be a hoe cause why not
	@Override
	@SuppressWarnings("incomplete-switch")
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!playerIn.canPlayerEdit(pos.offset(facing), facing, stack))
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(stack, playerIn, worldIn, pos);
            if (hook != 0) return hook > 0 ? EnumActionResult.SUCCESS : EnumActionResult.FAIL;

            IBlockState iblockstate = worldIn.getBlockState(pos);
            Block block = iblockstate.getBlock();

            if (facing != EnumFacing.DOWN && worldIn.isAirBlock(pos.up()))
            {
                if (block == Blocks.GRASS || block == Blocks.GRASS_PATH)
                {
                    this.setBlock(stack, playerIn, worldIn, pos, Blocks.FARMLAND.getDefaultState());
                    return EnumActionResult.SUCCESS;
                }

                if (block == Blocks.DIRT)
                {
                    switch ((BlockDirt.DirtType)iblockstate.getValue(BlockDirt.VARIANT))
                    {
                        case DIRT:
                            this.setBlock(stack, playerIn, worldIn, pos, Blocks.FARMLAND.getDefaultState());
                            return EnumActionResult.SUCCESS;
                        case COARSE_DIRT:
                            this.setBlock(stack, playerIn, worldIn, pos, Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT));
                            return EnumActionResult.SUCCESS;
                    }
                }
            }

            return EnumActionResult.PASS;
        }
    }
	/**
	 * What runs to do the hoe action
	 */
	protected void setBlock(ItemStack stack, EntityPlayer player, World worldIn, BlockPos pos, IBlockState state)
    {
        worldIn.playSound(player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

        if (!worldIn.isRemote)
        {
            worldIn.setBlockState(pos, state, 11);
            stack.damageItem(1, player);
        }
    }
	//end of letting it be a hoe
	
}
