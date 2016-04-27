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
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int metadata, boolean bool){
		if (itemstack.getItemDamage() != 10000){
			itemstack.setItemDamage(0);
		}
		
		
	}
	
	
	@Override
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player){
		if (itemStack.getTagCompound() == null)
        {
			itemStack.setTagCompound(new NBTTagCompound());  
        }
		itemStack.getTagCompound().setString("ownerID", player.getUniqueID().toString());
		itemStack.getTagCompound().setString("owner", player.getName());
		
	}
	
	//adds tooltip(duh?)
			@Override
			public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
		    {
				tooltip.add("Death Takes Us All");
				if(stack.getTagCompound() != null)
				{
					String owner = stack.getTagCompound().getString("owner");
					tooltip.add("Owner:" + owner);
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
                if (block == Blocks.grass || block == Blocks.grass_path)
                {
                    func_185071_a(stack, playerIn, worldIn, pos, Blocks.farmland.getDefaultState());
                    return EnumActionResult.SUCCESS;
                }

                if (block == Blocks.dirt)
                {
                    switch ((BlockDirt.DirtType)iblockstate.getValue(BlockDirt.VARIANT))
                    {
                        case DIRT:
                            this.func_185071_a(stack, playerIn, worldIn, pos, Blocks.farmland.getDefaultState());
                            return EnumActionResult.SUCCESS;
                        case COARSE_DIRT:
                            this.func_185071_a(stack, playerIn, worldIn, pos, Blocks.dirt.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT));
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
	protected void func_185071_a(ItemStack stack, EntityPlayer player, World worldIn, BlockPos pos, IBlockState state)
    {
        worldIn.playSound(player, pos, SoundEvents.item_hoe_till, SoundCategory.BLOCKS, 1.0F, 1.0F);

        if (!worldIn.isRemote)
        {
            worldIn.setBlockState(pos, state, 11);
           // stack.damageItem(1, player);   I slashed this out so it would not damage the hoe.
        }
    }
	//end of letting it be a hoe
	
}
