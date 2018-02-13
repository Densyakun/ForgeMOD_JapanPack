package org.densyakun.forge.japanpack.inventory;
import org.densyakun.forge.japanpack.tileentity.TileEntityMoneyChanger;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
public class MoneyChangerMoneyGuiContainer extends GuiContainer {
	private static final ResourceLocation TEXTURE = new ResourceLocation("japanpack", "textures/gui/container/money_changer_money.png");
	private TileEntityMoneyChanger te;
	private IInventory playerInv;
	//private IInventory upperChestInventory;
	//private IInventory lowerChestInventory;
	/** window height is calculated with these values; the more rows, the heigher */
	//private int inventoryRows;
	public MoneyChangerMoneyGuiContainer(IInventory playerInv, TileEntityMoneyChanger te) {
		super(new MoneyChangerMoneyContainer(playerInv, te));
		this.te = te;
		this.playerInv = playerInv;
		ySize = 168;
	}
	/*public GuiChest(IInventory p_i1083_1_, IInventory p_i1083_2_) {
		super(new ContainerChest(p_i1083_1_, p_i1083_2_));
		upperChestInventory = p_i1083_1_;
		lowerChestInventory = p_i1083_2_;
		allowUserInput = false;
		short short1 = 222;
		int i = short1 - 108;
		inventoryRows = p_i1083_2_.getSizeInventory() / 9;
		ySize = i + inventoryRows * 18;
	}*/
	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of the items)
	 */
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
		String s = te.getDisplayName().getUnformattedText();
		fontRendererObj.drawString(s, 88 - fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
		fontRendererObj.drawString(playerInv.getInventoryName(), 8, 30, 4210752);
	}
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseZ) {
		//GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(TEXTURE);
		/*int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, inventoryRows * 18 + 17);
		drawTexturedModalRect(k, l + inventoryRows * 18 + 17, 0, 126, xSize, 96);*/
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
}
