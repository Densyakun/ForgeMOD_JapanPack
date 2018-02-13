package org.densyakun.forge.japanpack.item;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
public class FiveYen extends Item {
	public FiveYen() {
		setMaxStackSize(1);
		setUnlocalizedName("fiveyen");
		setTextureName("japanpack:fiveyen");
	}
	@Override
	public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
		p_77659_2_.playSound(p_77659_3_.posX, p_77659_3_.posY, p_77659_3_.posZ, "japanpack:money", 1, 0, false);
		return p_77659_1_;
	}
}
