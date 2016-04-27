package com.theundertaker11.kitchensink.ksitems;

import java.util.List;
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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiScreenEvent.PotionShiftEvent;

public class godsTool extends ItemPickaxe {
	protected godsTool(String name, ToolMaterial god_mat) {
		super(god_mat);
		setMaxDamage(10000);
		this.setUnlocalizedName(name);
		this.setCreativeTab(KitchenSink.KStab);
		this.setRegistryName(name);
	}
	
	//Might make it fix itself when duriblity is brought down by other mods, havent tested.
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
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
		if(!worldIn.isRemote)
		{
			if(!playerIn.isSneaking() && itemStackIn.getTagCompound() != null)
			{
				
					if(playerIn.getUniqueID().toString().equals(itemStackIn.getTagCompound().getString("ownerID")))
					{
						playerIn.addPotionEffect((new PotionEffect(Potion.getPotionById(12), 1200, 2)));
						playerIn.addPotionEffect((new PotionEffect(Potion.getPotionById(1), 1200, 2)));
						playerIn.removePotionEffect(Potion.getPotionById(2));
						playerIn.removePotionEffect(Potion.getPotionById(4));
						playerIn.removePotionEffect(Potion.getPotionById(9));
						playerIn.removePotionEffect(Potion.getPotionById(15));
						playerIn.removePotionEffect(Potion.getPotionById(17));
						playerIn.removePotionEffect(Potion.getPotionById(18));
						playerIn.removePotionEffect(Potion.getPotionById(19));
						playerIn.removePotionEffect(Potion.getPotionById(20));
						
					}
			}      
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackIn);
	}
			

	

	//untill the next slash is to make it a multi-tool
	@Override
	public Set<String> getToolClasses(ItemStack stack) {
	    return ImmutableSet.of("pickaxe", "spade", "axe");
	}
	
	private static Set effectiveAgainst = Sets.newHashSet(new Block[] {
		    Blocks.grass, Blocks.dirt, Blocks.sand, Blocks.gravel, 
		    Blocks.snow_layer, Blocks.snow, Blocks.clay, Blocks.farmland, 
		    Blocks.soul_sand, Blocks.mycelium, Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2, 
		    Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin, Blocks.web, Blocks.trapdoor, Blocks.ladder, Blocks.wool,
		    Blocks.glowstone, Blocks.skull, Blocks.glass, Blocks.glass_pane, Blocks.stained_glass, Blocks.stained_glass_pane, Blocks.cactus,
		    Blocks.leaves, Blocks.leaves2, Blocks.redstone_lamp, Blocks.carpet});
	
	@Override
	public boolean canHarvestBlock(IBlockState blockIn) 
	{
	    return true;
	}
	
	@Override
	public float getStrVsBlock(ItemStack stack, IBlockState state)
    {
		return 10000;
	}
	//End multitool code
	
	
	//adds tooltip(duh?)
		@Override
		public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
	    {	
		 tooltip.add("Was Once Weld by God");
		 if(stack.getTagCompound() != null)
			{
				String owner = stack.getTagCompound().getString("owner");
				tooltip.add("Owner:" + owner);
			}
		}
			
	//makes the name look cooler
	@Override
	 public EnumRarity getRarity(ItemStack stack)
	    {
	        return EnumRarity.RARE;
	    }
	//adds enchanted look
	@Override
    public boolean hasEffect(ItemStack par1ItemStack)
    {
	    return true;
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
		  public Entity createEntity(World world, Entity location, ItemStack itemstack) 
		  {
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
