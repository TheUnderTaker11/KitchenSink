package com.theundertaker11.kitchensink.ksblocks.blessedfurnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerBlessedFurnace extends Container {

	private KSTileEntityBlessedFurnace tileInventory;

	private int cachedTicksCooking;

	private final int HOTBAR_SLOT_COUNT = 9;
	private final int PLAYER_INVENTORY_ROW_COUNT = 3;
	private final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
	private final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
	private final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;

	public final int INPUT_SLOTS_COUNT = 1;
	public final int OUTPUT_SLOTS_COUNT = 1;
	public final int TOTAL_SLOTS_COUNT = INPUT_SLOTS_COUNT + OUTPUT_SLOTS_COUNT;

	private final int VANILLA_FIRST_SLOT_INDEX = 0;
	private final int INPUT_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
	private final int OUTPUT_SLOT_INDEX = INPUT_SLOT_INDEX + INPUT_SLOTS_COUNT;

	private final int INPUT_SLOT_NUMBER = 0;
	private final int OUTPUT_SLOT_NUMBER = 0;

	public ContainerBlessedFurnace(InventoryPlayer invPlayer, KSTileEntityBlessedFurnace tileInventory){
		this.tileInventory = tileInventory;

		final int SLOT_X_SPACING = 18;
		final int SLOT_Y_SPACING = 18;
		final int HOTBAR_XPOS = 8;
		final int HOTBAR_YPOS = 183;
		// Add the players hotbar to the gui - the [xpos, ypos] location of each item
		for (int x = 0; x < HOTBAR_SLOT_COUNT; x++) {
			int slotNumber = x;
			addSlotToContainer(new Slot(invPlayer, slotNumber, HOTBAR_XPOS + SLOT_X_SPACING * x, HOTBAR_YPOS));
		}

		final int PLAYER_INVENTORY_XPOS = 8;
		final int PLAYER_INVENTORY_YPOS = 125;
		// Add the rest of the players inventory to the gui
		for (int y = 0; y < PLAYER_INVENTORY_ROW_COUNT; y++) {
			for (int x = 0; x < PLAYER_INVENTORY_COLUMN_COUNT; x++) {
				int slotNumber = HOTBAR_SLOT_COUNT + y * PLAYER_INVENTORY_COLUMN_COUNT + x;
				int xpos = PLAYER_INVENTORY_XPOS + x * SLOT_X_SPACING;
				int ypos = PLAYER_INVENTORY_YPOS + y * SLOT_Y_SPACING;
				addSlotToContainer(new Slot(invPlayer, slotNumber,  xpos, ypos));
			}
		}
		
		IItemHandler itemhandlerinput = tileInventory.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		IItemHandler itemhandleroutput = tileInventory.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);
		final int INPUT_SLOTS_XPOS = 26;
		final int INPUT_SLOTS_YPOS = 24;
		// Add the tile input slots
		int slotNumberInput = 2 + INPUT_SLOT_NUMBER;
		addSlotToContainer(new SlotSmeltableInput(itemhandlerinput, INPUT_SLOT_NUMBER, INPUT_SLOTS_XPOS, INPUT_SLOTS_YPOS+ SLOT_Y_SPACING * 2));
		

		final int OUTPUT_SLOTS_XPOS = 134;
		final int OUTPUT_SLOTS_YPOS = 24;
		// Add the tile output slots
		int slotNumber = 2 + OUTPUT_SLOT_NUMBER;
		addSlotToContainer(new SlotOutput(itemhandleroutput, OUTPUT_SLOT_NUMBER, OUTPUT_SLOTS_XPOS, OUTPUT_SLOTS_YPOS + SLOT_Y_SPACING * 2));
	}

	// Checks each tick to make sure the player is still able to access the inventory and if not closes the gui
	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return tileInventory.isUseableByPlayer(player);
	}

	//Always make sure it returns null if nothing should happen, and after stuff has happened make it return a copy
	//of the stack
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int sourceSlotIndex)
	{
		ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(sourceSlotIndex);
        if(slot == null || !slot.getHasStack()) return null;
        if(tileInventory.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP)==null) return null;
        IItemHandler input = tileInventory.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        
        ItemStack sourceStack = slot.getStack();
        ItemStack copyOfStack = sourceStack.copy();
        
        //If it is player's inventory do these things.
        if (sourceSlotIndex >= VANILLA_FIRST_SLOT_INDEX && sourceSlotIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT)
        {
        	if(KSTileEntityBlessedFurnace.getSmeltingResultForItem(sourceStack)!=null)
        	{
        		if(input.insertItem(0, sourceStack, true)==null)
        		{
        			input.insertItem(0, sourceStack, false);
        			player.inventory.setInventorySlotContents(sourceSlotIndex, null);
        		}
        		else if (input.insertItem(0, sourceStack, true).stackSize==sourceStack.stackSize){
					return null;
				}
        		else
        		{
        			player.inventory.setInventorySlotContents(sourceSlotIndex, input.insertItem(0, sourceStack, false));
        		}
        	}
        	else return null;
        }
        else if(sourceSlotIndex==INPUT_SLOT_INDEX||sourceSlotIndex==OUTPUT_SLOT_INDEX)
        {
        	if (!this.mergeItemStack(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)){
        		return null;
			}
        }else return null;
        return copyOfStack;
	}	
	

	/* Client Synchronization */
	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();

		boolean fieldHasChanged = false;
		boolean overclockersChanged = false;
		if (cachedTicksCooking != tileInventory.getField(0))
		{
			this.cachedTicksCooking = tileInventory.getField(0);
			fieldHasChanged = true;
		}

		// go through the list of listeners (players using this container) and update them if necessary
		for (IContainerListener listener : this.listeners)
		{
			if (fieldHasChanged)
			{
				// Note that although sendProgressBarUpdate takes 2 ints on a server these are truncated to shorts
				listener.sendProgressBarUpdate(this, 0, this.cachedTicksCooking);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int data)
	{
		tileInventory.setField(id, data);
	}

	// SlotSmeltableInput is a slot for input items
	public class SlotSmeltableInput extends SlotItemHandler
	{
		public SlotSmeltableInput(IItemHandler inventoryIn, int index, int xPosition, int yPosition) {
			super(inventoryIn, index, xPosition, yPosition);
		}

		// if this function returns false, the player won't be able to insert the given item into this slot
		@Override
		public boolean isItemValid(ItemStack stack) {
			return (KSTileEntityBlessedFurnace.getSmeltingResultForItem(stack)!=null);
		}
	}

	public class SlotOutput extends SlotItemHandler
	{
		public SlotOutput(IItemHandler inventoryIn, int index, int xPosition, int yPosition) {
			super(inventoryIn, index, xPosition, yPosition);
		}

		@Override
		public boolean isItemValid(ItemStack stack) {
			return false;
		}
	}
}