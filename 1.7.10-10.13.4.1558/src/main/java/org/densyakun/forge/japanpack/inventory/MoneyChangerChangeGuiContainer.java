package org.densyakun.forge.japanpack.inventory;
import org.densyakun.forge.japanpack.tileentity.TileEntityMoneyChanger;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
public class MoneyChangerChangeGuiContainer extends GuiContainer {
	private static final ResourceLocation TEXTURE = new ResourceLocation("japanpack", "textures/gui/container/money_changer_change.png");
	private TileEntityMoneyChanger te;
	private IInventory playerInv;
	public MoneyChangerChangeGuiContainer(IInventory playerInv, TileEntityMoneyChanger te) {
		super(new MoneyChangerChangeContainer(playerInv, te));
		this.te = te;
		this.playerInv = playerInv;
	}
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseZ) {
		String s = te.getDisplayName().getUnformattedText();
		fontRendererObj.drawString(s, 88 - fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
		fontRendererObj.drawString(playerInv.getInventoryName(), 8, 30, 4210752);
	}
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseZ) {
		mc.renderEngine.bindTexture(TEXTURE);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

	}
}
