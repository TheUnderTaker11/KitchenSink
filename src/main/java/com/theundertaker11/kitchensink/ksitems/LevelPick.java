package com.theundertaker11.kitchensink.ksitems;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import com.theundertaker11.kitchensink.KitchenSink;
import com.theundertaker11.kitchensink.ModUtils;
import com.theundertaker11.kitchensink.entity.IndestructibleEntityItem;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentBase;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;

public class LevelPick extends ItemPickaxe {
	protected LevelPick(String name, ToolMaterial material) 
	{
		super(material);
		this.setMaxDamage(0);
		this.setUnlocalizedName(name);
		this.setCreativeTab(KitchenSink.KStab);
		this.setRegistryName(name);
	}
	
	/* Max durability for each level
	 * Max=250(level) + 250
	 * 
	 * XP to next level
	 * Y=200(level)+50
	 * 
	 * Harvest level is the same as the level up to 5
	 * 
	 */
	
	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int metadata, boolean bool)
	{
		if(!world.isRemote&&itemstack.getTagCompound()!=null)
		{
			if(itemstack.getTagCompound().getInteger("maxdur")<itemstack.getTagCompound().getInteger("dur"))
			{
				itemstack.getTagCompound().setInteger("dur",itemstack.getTagCompound().getInteger("maxdur"));
			}
			if(itemstack.getTagCompound().getInteger("pickxp")>=itemstack.getTagCompound().getInteger("xptonextlevel"))
			{
				if(itemstack.getTagCompound().getInteger("picklevel")==0)
				{
					levelUpPick(itemstack, entity,1F);
				}
				if(itemstack.getTagCompound().getInteger("picklevel")==2||itemstack.getTagCompound().getInteger("picklevel")==3)
				{
					levelUpPick(itemstack, entity,3F);
				}
				if(itemstack.getTagCompound().getInteger("picklevel")==4)
				{
					levelUpPick(itemstack, entity, 1F);
					//Adds fortune 10 if it there is not silk touch
					if(EnchantmentHelper.getEnchantmentLevel(Enchantment.getEnchantmentByID(33), itemstack)<1)
					{
						itemstack.addEnchantment(Enchantment.getEnchantmentByID(35), 10);
						entity.addChatMessage(new TextComponentString("Fortune 10 added"));
					}
					
				}
				//If it was level 1 or level 5+
				if(itemstack.getTagCompound().getInteger("picklevel")==1||itemstack.getTagCompound().getInteger("picklevel")>=5)
				{
					levelUpPick(itemstack, entity, 2F);
				}
			}
			
			/*
			 * Does math to the level to set certain values(Ones that only matter what the level is).
			 * 
			 */
			itemstack.getTagCompound().setInteger("maxdur", ((itemstack.getTagCompound().getInteger("picklevel")*250)+250));
			itemstack.getTagCompound().setInteger("xptonextlevel", ((itemstack.getTagCompound().getInteger("picklevel")*200)+50));
			itemstack.getTagCompound().setInteger("harvestlevel", itemstack.getTagCompound().getInteger("picklevel"));
		/*
		 * Start magnet
		 * 
		 */
if(itemstack.getTagCompound().getBoolean("allowmagnet")&&itemstack.getTagCompound().getBoolean("magnetactive")&&(entity instanceof EntityPlayer))
{
		EntityPlayer player = (EntityPlayer) entity;

		// items
				Iterator iterator = ModUtils.getEntitiesInRange(EntityItem.class, world, player.posX, player.posY,
						player.posZ, 12.5).iterator();
				while (iterator.hasNext()) {
					EntityItem itemToGet = (EntityItem) iterator.next();

					EntityItemPickupEvent pickupEvent = new EntityItemPickupEvent(player, itemToGet);
					MinecraftForge.EVENT_BUS.post(pickupEvent);
					ItemStack itemStackToGet = itemToGet.getEntityItem();
					int stackSize = itemStackToGet.stackSize;

					if (pickupEvent.getResult() == Result.ALLOW || stackSize <= 0
							|| player.inventory.addItemStackToInventory(itemStackToGet)) {
						player.onItemPickup(itemToGet, stackSize);
						world.playSound(player, player.getPosition(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.AMBIENT,
								0.15F, ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
					}
				}

				// xp
				iterator = ModUtils.getEntitiesInRange(EntityXPOrb.class, world, player.posX, player.posY, player.posZ,
						12.5).iterator();
				while (iterator.hasNext()) {
					EntityXPOrb xpToGet = (EntityXPOrb) iterator.next();

					if (xpToGet.isDead || xpToGet.isInvisible()) {
						continue;
					}
					player.xpCooldown = 0;
					xpToGet.delayBeforeCanPickup=0;
					xpToGet.setPosition(player.posX,player.posY,player.posZ);
					PlayerPickupXpEvent xpEvent = new PlayerPickupXpEvent(player, xpToGet);
					MinecraftForge.EVENT_BUS.post(xpEvent);
					if(xpEvent.getResult()==Result.ALLOW){
						xpToGet.onCollideWithPlayer(player);
					}
					
				}
}
	/*
	 * End magnet
	 * 
	 */
		}
	}
	
	/**
	 * Levels up the pick, entity is needed to give them the chat message
	 */
	  public static void levelUpPick(ItemStack itemstack, Entity entity, float speedadded)
	  {
		  if(itemstack.getTagCompound()!=null)
		  {
			  itemstack.getTagCompound().setInteger("pickxp", 0);
			  itemstack.getTagCompound().setInteger("picklevel", itemstack.getTagCompound().getInteger("picklevel")+1);
			  itemstack.getTagCompound().setFloat("pickspeed", (itemstack.getTagCompound().getFloat("pickspeed")+speedadded));
			  entity.addChatMessage(new TextComponentString("Your Pick Has Leveled Up!"));
			  entity.addChatMessage(new TextComponentString("Speed added: " + speedadded));
		  }
	  }
	  
	//Adds tooltip
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
		if(stack.getTagCompound() !=null)
		{ 
		String dur = stack.getTagCompound().getInteger("dur") + "";
		String harvestlevel = stack.getTagCompound().getInteger("harvestlevel") + "";
		String picklevel = stack.getTagCompound().getInteger("picklevel") + "";
		String pickspeed = stack.getTagCompound().getFloat("pickspeed") + "";
		String pickxp = stack.getTagCompound().getInteger("pickxp") + "";
		String xpremains = stack.getTagCompound().getInteger("xptonextlevel") + "";
		String maxdur = stack.getTagCompound().getInteger("maxdur") + "";
		
			if(stack.getTagCompound().getInteger("dur")>0)
			{
				tooltip.add(TextFormatting.BLUE + "Duribility remaining: " + dur+"/"+maxdur);
			}
			if(stack.getTagCompound().getInteger("dur")==0)
			{
				tooltip.add(TextFormatting.RED + "Tool is Broken");
			}
			tooltip.add(TextFormatting.GOLD + "Pickaxe Level: "+picklevel);
			tooltip.add(TextFormatting.GOLD + "Harvest Level: "+harvestlevel);
			tooltip.add(TextFormatting.GOLD + "Mining Speed: "+pickspeed);
			if(stack.getTagCompound().getBoolean("allowmagnet"))
			{
				if(stack.getTagCompound().getBoolean("magnetactive"))
				{
					tooltip.add(TextFormatting.BOLD+"Magnet Enabled");
				}
				else tooltip.add(TextFormatting.BOLD+"Magnet Disabled");
			}
			if(stack.getTagCompound().getBoolean("allowxp")) tooltip.add("Right clicks remaining: "+(stack.getTagCompound().getInteger("xpfromblocks")/20));
			tooltip.add("Current XP: "+pickxp+"/"+xpremains);
			if(stack.getTagCompound().getBoolean("autorepair")) tooltip.add("Repair I");
		}
    }
	/**
	 * Makes it so hitting Entity's takes 2 from NBT duribility
	 * 
	 */
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
		if(stack.getTagCompound() !=null && stack.getTagCompound().getInteger("dur")>0)
		{
			stack.getTagCompound().setInteger("dur", (stack.getTagCompound().getInteger("dur")-2));
		}	
        return false;
    }
	/**
	 * Makes it so breaking takes 1 from my NBT duribility
	 * Also adds XP for each block broken, based on the block
	 */
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState blockIn, BlockPos pos, EntityLivingBase entityLiving)
    {
		if(!worldIn.isRemote&&stack.getTagCompound() !=null&& stack.getTagCompound().getInteger("dur")>0)
		{
			stack.getTagCompound().setInteger("dur", (stack.getTagCompound().getInteger("dur")-1));
			//These will give varying XP based on the block broken.
			if(blockIn==Blocks.DIAMOND_ORE||blockIn==Blocks.GOLD_ORE||blockIn==Blocks.EMERALD_ORE||blockIn==Blocks.QUARTZ_ORE||blockIn==Blocks.OBSIDIAN)
			{
			stack.getTagCompound().setInteger("pickxp", stack.getTagCompound().getInteger("pickxp")+4);
			}
			else if(blockIn==Blocks.COAL_ORE||blockIn==Blocks.IRON_ORE||blockIn==Blocks.LAPIS_ORE||blockIn==Blocks.REDSTONE_ORE)
			{
			stack.getTagCompound().setInteger("pickxp", stack.getTagCompound().getInteger("pickxp")+3);
			}
			else stack.getTagCompound().setInteger("pickxp", stack.getTagCompound().getInteger("pickxp")+1);

			//Increases xpfromblocks NBT if it has been crafted with the xp items.
			if(stack.getTagCompound().getBoolean("allowxp"))
			{
			stack.getTagCompound().setInteger("xpfromblocks", (stack.getTagCompound().getInteger("xpfromblocks")+1));
			}
		}
		
        return false;
    }
	
	/**
	 * Changes harvestlevel based on NBT harvest level
	 * 
	 */
	@Override
    public int getHarvestLevel(ItemStack stack, String toolClass)
    {
		if(stack.getTagCompound()!=null)return stack.getTagCompound().getInteger("harvestlevel");
		else return 1;
    }
	/**
	 * Changes mining speed based on NBT
	 * 
	 */
	@Override
	public float getStrVsBlock(ItemStack stack, IBlockState state)
    {
		if(stack.getTagCompound() !=null)
		{
			if(stack.getTagCompound().getInteger("dur")>0)
			{
				if (state.getBlock().isToolEffective("pickaxe", state))
					return stack.getTagCompound().getFloat("pickspeed");
			}
			else return 0.0F;
		}
		return 3.0F;
    }

	/**Makes it so my pick cant break blocks when NBT Duribility is 0 
	 * (Along with the getStrVsBlock method)
	 */
	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player)
    {
		if(itemstack.getTagCompound()!=null)
		{
			if(itemstack.getTagCompound().getInteger("dur")>0)
			{
				return false;
			}
			else return true;
		}
		return false;
    }
	
	@Override
	public int getItemEnchantability()
    {
        return 0;
    }
	/**
	 * Code for magnet activation on shift click, and getting XP when that is allowed.
	 * 
	 */
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
		if (!worldIn.isRemote && playerIn.isSneaking()&&itemStackIn.getTagCompound()!=null)
		{
			if(itemStackIn.getTagCompound().getBoolean("allowmagnet"))
			{
				itemStackIn.getTagCompound().setBoolean("magnetactive", (!itemStackIn.getTagCompound().getBoolean("magnetactive")));
			}
		}
		if(!worldIn.isRemote&&!playerIn.isSneaking()&&itemStackIn.getTagCompound()!=null&&itemStackIn.getTagCompound().getBoolean("allowxp"))
		{
			if(itemStackIn.getTagCompound().getInteger("xpfromblocks")>19)
			{
				worldIn.spawnEntityInWorld(new EntityXPOrb(worldIn, playerIn.posX + 1, playerIn.posY, playerIn.posZ, 23));
				itemStackIn.getTagCompound().setInteger("xpfromblocks", (itemStackIn.getTagCompound().getInteger("xpfromblocks")-20));
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackIn);
	}
	
	/**
	 * Adds duribility when called by the playertick in my event class
	 * 
	 */
	public static void addDur(ItemStack itemstack, EntityPlayer player)
	{
		
		if(itemstack.getTagCompound()!=null&&itemstack.getTagCompound().getBoolean("autorepair")&&!player.isSwingInProgress)
		{
			if(itemstack.getTagCompound().getInteger("dur")<itemstack.getTagCompound().getInteger("maxdur"))
			{
				itemstack.getTagCompound().setInteger("dur",(itemstack.getTagCompound().getInteger("dur")+20));
			}
		}
	}
	
	@Override
	public boolean showDurabilityBar(ItemStack stack)
    {
		if(stack.getTagCompound()!=null&&stack.getTagCompound().getInteger("dur")<stack.getTagCompound().getInteger("maxdur")) return true;
		else return false;
    }
	/**Makes it so duribility bar still shows even though I'm using meta damage for textures */
	@Override
	public double getDurabilityForDisplay(ItemStack stack)
    {
		if(stack.getTagCompound()!=null)
		{
			return ((double)stack.getTagCompound().getInteger("maxdur")-(double)stack.getTagCompound().getInteger("dur"))/(double)stack.getTagCompound().getInteger("maxdur");
		}
		else return 1;
    }
	
	@Override
	public boolean hasEffect(ItemStack item)
	{
		if(item.getTagCompound()!=null) return item.getTagCompound().getBoolean("magnetactive");
		else return false;
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
