package com.theundertaker11.kitchensink.gui;

import com.theundertaker11.kitchensink.Refernce;
import com.theundertaker11.kitchensink.container.TrashChestContainer;
import com.theundertaker11.kitchensink.tileentity.KSTileEntityTrashChest;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

public class TrashChestGui extends GuiContainer {
    public static final int WIDTH = 180;
    public static final int HEIGHT = 152;

    private static final ResourceLocation background = new ResourceLocation(Refernce.MODID, "textures/gui/TrashChestContainer.png");

    public TrashChestGui(KSTileEntityTrashChest tileEntity, TrashChestContainer container) {
        super(container);

        xSize = WIDTH;
        ySize = HEIGHT;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

}
