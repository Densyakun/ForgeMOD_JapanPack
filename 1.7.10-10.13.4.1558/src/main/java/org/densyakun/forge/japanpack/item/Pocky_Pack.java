package org.densyakun.forge.japanpack.item;
import org.densyakun.forge.japanpack.Main;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
public class Pocky_Pack extends Item {
	boolean thin;
	public Pocky_Pack(boolean thin) {
		if (this.thin = thin) {
			setMaxStackSize(4);
			setUnlocalizedName("pocky_pack_thin");
			setTextureName("japanpack:pocky_pack_thin");
		} else {
			setMaxStackSize(2);
			setUnlocalizedName("pocky_pack_normal");
			setTextureName("japanpack:pocky_pack_normal");
		}
	}
	public static void open(ItemStack itemstack, EntityPlayer player) {
		if ((itemstack.getItem() instanceof Pocky_Pack) && (player.inventory.addItemStackToInventory(new ItemStack(((Pocky_Pack)itemstack.getItem()).thin ? Main.pocky_thin : Main.pocky_normal, ((Pocky_Pack)itemstack.getItem()).thin ? Main.pocky_thin.getItemStackLimit() : Main.pocky_normal.getItemStackLimit())))) {
			itemstack.stackSize -= 1;
		}
	}
	public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
		open(p_77659_1_, p_77659_3_);
		return p_77659_1_;
	}
}
