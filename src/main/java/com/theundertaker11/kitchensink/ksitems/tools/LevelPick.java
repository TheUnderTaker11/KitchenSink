package com.theundertaker11.kitchensink.ksitems.tools;

import java.util.List;
import java.util.UUID;

import com.theundertaker11.kitchensink.KitchenSink;
import com.theundertaker11.kitchensink.entity.IndestructibleEntityItem;
import com.theundertaker11.kitchensink.event.KSEventHandler;
import com.theundertaker11.kitchensink.util.ModUtils;

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
	public LevelPick(String name, ToolMaterial material) 
	{
		super(material);
		this.setMaxDamage(0);
		this.setUnlocalizedName(name);
		this.setCreativeTab(KitchenSink.KStab);
		this.setRegistryName(name);
	}
	
	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int metadata, boolean bool)
	{
		if(!world.isRemote&&itemstack.getTagCompound()!=null&&(entity instanceof EntityPlayer))
		{
			EntityPlayer player = (EntityPlayer) entity;
			NBTTagCompound tag = itemstack.getTagCompound();
			
			if(tag.getInteger("maxdur")<tag.getInteger("dur"))
				tag.setInteger("dur",tag.getInteger("maxdur"));
			
			if(itemstack.getTagCompound().getBoolean("allowmagnet")&&itemstack.getTagCompound().getBoolean("magnetactive"))
				ModUtils.doMagnet(player, world, 12.5);
			
			if(KSEventHandler.itemtimer>8)
				testXPLevel(tag, itemstack, player);
		}
	}
	
	/**
	 * Checks if XP is >= max XP, and levels the pick up if it is.
	 * @param tag
	 * @param pick
	 * @param player
	 */
	public static void testXPLevel(NBTTagCompound tag, ItemStack pick, EntityPlayer player)
	{
		if(tag.getInteger("pickxp")>=tag.getInteger("xptonextlevel"))
		{
			if(tag.getInteger("picklevel")==0)
			{
				levelUpPick(pick, player,1F);
			}
			if(tag.getInteger("picklevel")==2||tag.getInteger("picklevel")==3)
			{
				levelUpPick(pick, player,3F);
			}
			if(tag.getInteger("picklevel")==4)
			{
				levelUpPick(pick, player, 1F);
				//Adds fortune 10 if it there is not silk touch
				if(EnchantmentHelper.getEnchantmentLevel(Enchantment.getEnchantmentByID(33), pick)<1)
				{
					pick.addEnchantment(Enchantment.getEnchantmentByID(35), 10);
					player.addChatMessage(new TextComponentString("Fortune 10 added"));
				}
				
			}
			//If it was level 1 or level 5+
			if(tag.getInteger("picklevel")==1||tag.getInteger("picklevel")>=5)
			{
				levelUpPick(pick, player, 2F);
			}
		}
	}
	/**
	 * Levels up the pick, player is needed to give them the chat message
	 */
	 public static void levelUpPick(ItemStack itemstack, EntityPlayer player, float speedadded)
	 {
		  if(itemstack.getTagCompound()!=null)
		  {
			  NBTTagCompound tag = itemstack.getTagCompound();
			  tag.setInteger("pickxp", 0);
			  tag.setInteger("picklevel", tag.getInteger("picklevel")+1);
			  tag.setFloat("pickspeed", (tag.getFloat("pickspeed")+speedadded));
			  player.addChatMessage(new TextComponentString("Your Pick Has Leveled Up!"));
			  player.addChatMessage(new TextComponentString("Speed added: " + speedadded));
			  tag.setInteger("harvestlevel", tag.getInteger("picklevel"));
			  tag.setInteger("xptonextlevel", ((tag.getInteger("picklevel")*150)+50));
			  if(!tag.hasKey("unbreakable"))
			  {
				  tag.setInteger("maxdur", ((tag.getInteger("picklevel")*250)+250));
				  tag.setInteger("dur", tag.getInteger("dur")+250);
			  }
		  }
	 }
	  
	//Adds tooltip
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
		if(stack.getTagCompound() !=null)
		{
		  NBTTagCompound tag = stack.getTagCompound();
		  //These dummies are used for tooltips on crafting results
		  if(tag.getBoolean("dummyRepair")||tag.getBoolean("dummyMagnet")||tag.getBoolean("dummySpeed")||tag.getBoolean("dummyXP")||tag.getBoolean("dummyAutoRepair"))
		  {
			  if(tag.getBoolean("dummyRepair"))
			  {
				  tooltip.add(TextFormatting.BOLD+"This will repair your pickaxe");
				  tooltip.add(TextFormatting.BLUE + "Duribility remaining: " +tag.getInteger("dur")+"/"+tag.getInteger("maxdur"));
			  }
			  else if(tag.getBoolean("dummyMagnet"))
			  {
				  tooltip.add(TextFormatting.BOLD+"This will add a magnet to your pick");
			  }
			  else if(tag.getBoolean("dummySpeed"))
			  {
				  tooltip.add(TextFormatting.BOLD+"This will add speed to your pick");
			  }
			  else if(tag.getBoolean("dummyXP"))
			  {
				  tooltip.add(TextFormatting.BOLD+"This will add the XP addon to your pick");
				  tooltip.add(TextFormatting.BOLD+"It will make you be able to right click every 20 blocks or so for XP");
			  }
			  else if(tag.getBoolean("dummyAutoRepair"))
			  {
				  tooltip.add(TextFormatting.BOLD+"This will add auto repair onto your pick");
				  tooltip.add(TextFormatting.BOLD+"It is fast enough it will never need repaired again.");
			  }
			  else if(tag.getBoolean("dummyUnbreakable"))
			  {
				  tooltip.add(TextFormatting.BOLD+"Your pick will be fully repaired and unbreakable");
			  }
		  }
		  else
		  {
			if(tag.getInteger("dur")>0)
			{
				tooltip.add(TextFormatting.BLUE + "Duribility remaining: " +tag.getInteger("dur")+"/"+tag.getInteger("maxdur"));
			}
			if(tag.getInteger("dur")==0)
			{
				tooltip.add(TextFormatting.RED + "Tool is Broken");
			}
			tooltip.add(TextFormatting.GOLD + "Pickaxe Level: "+tag.getInteger("picklevel"));
			tooltip.add(TextFormatting.GOLD + "Harvest Level: "+tag.getInteger("harvestlevel"));
			tooltip.add(TextFormatting.GOLD + "Mining Speed: "+tag.getFloat("pickspeed"));
			if(tag.getBoolean("allowmagnet"))
			{
				if(tag.getBoolean("magnetactive"))
				{
					tooltip.add(TextFormatting.BOLD+"Magnet Enabled");
				}
				else tooltip.add(TextFormatting.BOLD+"Magnet Disabled");
			}
			if(tag.hasKey("xpfromblocks")) tooltip.add("Right clicks remaining: "+(tag.getInteger("xpfromblocks")/20));
			tooltip.add("Current XP: "+tag.getInteger("pickxp")+"/"+tag.getInteger("xptonextlevel"));
			if(tag.getBoolean("autorepair")) tooltip.add("Repair I");
		  }
		}
    }
	
	/**
	 * Makes it so hitting Entity's takes 2 from NBT duribility
	 * 
	 */
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
		if(stack.getTagCompound() !=null && !stack.getTagCompound().hasKey("unbreakable") && stack.getTagCompound().getInteger("dur")>0)
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
		if(!worldIn.isRemote&&entityLiving instanceof EntityPlayer&&stack.getTagCompound()!=null&&stack.getTagCompound().getInteger("dur")>0)
		{
			Block block = blockIn.getBlock();
			NBTTagCompound tag = stack.getTagCompound();
			
			if(block==Blocks.DIAMOND_ORE||block==Blocks.GOLD_ORE||block==Blocks.EMERALD_ORE||block==Blocks.QUARTZ_ORE||block==Blocks.OBSIDIAN)
			{
				tag.setInteger("pickxp", tag.getInteger("pickxp")+4);
			}
			else if(block==Blocks.COAL_ORE||block==Blocks.IRON_ORE||block==Blocks.LAPIS_ORE||block==Blocks.REDSTONE_ORE||block==Blocks.LIT_REDSTONE_ORE)
			{
				tag.setInteger("pickxp", tag.getInteger("pickxp")+3);
			}
			else tag.setInteger("pickxp", tag.getInteger("pickxp")+1);

			//Increases xpfromblocks NBT if it has been crafted with the xp items.
			if(tag.hasKey("xpfromblocks"))
			{
				tag.setInteger("xpfromblocks", (tag.getInteger("xpfromblocks")+1));
			}
			if(!tag.hasKey("unbreakable")) tag.setInteger("dur", (tag.getInteger("dur")-1));
		}
		
        return true;
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
			if (state.getBlock()==Blocks.REDSTONE_ORE||state.getBlock()==Blocks.LIT_REDSTONE_ORE||state.getBlock()==Blocks.OBSIDIAN
						||state.getBlock().isToolEffective("pickaxe", state))
			{
				if(state.getBlock().getHarvestLevel(state)>=stack.getTagCompound().getInteger("harvestlevel"))
				{
					return stack.getTagCompound().getFloat("pickspeed");
				}
			}
		}
		return super.getStrVsBlock(stack, state);
    }

	/**
	 * Makes it so my pick cant break blocks when NBT Duribility is 0 
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
	public int getItemEnchantability(){return 0;}
	/**
	 * Code for magnet activation on shift click, and getting XP when that is allowed.
	 * 
	 */
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
		if(itemStackIn.getTagCompound()!=null)
		{
			NBTTagCompound tag=itemStackIn.getTagCompound();
			if (!worldIn.isRemote && playerIn.isSneaking())
			{
				if(tag.getBoolean("allowmagnet"))
				{
					tag.setBoolean("magnetactive", (!tag.getBoolean("magnetactive")));
				}
			}
			if(!worldIn.isRemote&&!playerIn.isSneaking()&&tag.hasKey("xpfromblocks"))
			{
				if(tag.getInteger("xpfromblocks")>19)
				{
					worldIn.spawnEntityInWorld(new EntityXPOrb(worldIn, playerIn.posX + 1, playerIn.posY, playerIn.posZ, 23));
					tag.setInteger("xpfromblocks", (tag.getInteger("xpfromblocks")-20));
				}
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackIn);
	}
	
	/**
	 * Adds duribility when called by the worldtick class
	 * 
	 */
	public static void addDur(ItemStack itemstack, EntityPlayer player)
	{
		
		if(itemstack.getTagCompound()!=null&&itemstack.getTagCompound().getBoolean("autorepair")&&!player.isSwingInProgress)
		{
			NBTTagCompound tag = itemstack.getTagCompound();
			if(tag.getInteger("dur")<tag.getInteger("maxdur"))
			{
				tag.setInteger("dur",(tag.getInteger("dur")+1));
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
	
	//Tinkers Code to make its EntityItem invincible
	@Override
	public boolean hasCustomEntity(ItemStack stack)
	{
		return true;
	}

	@Override
	public Entity createEntity(World world, Entity location, ItemStack itemstack) 
	{
		EntityItem entity = new IndestructibleEntityItem(world, location.posX, location.posY, location.posZ, itemstack);
		if(location instanceof EntityItem)
		{
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
