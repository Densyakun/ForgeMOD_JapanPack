package org.densyakun.forge.japanpack.inventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
public class JPContainer extends Container {
    int xCoord, yCoord, zCoord;
    public JPContainer(int x, int y, int z) {
        this.xCoord = x;
        this.yCoord = y;
        this.zCoord = z;
    }
	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return p_75145_1_.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64D;
	}
}
