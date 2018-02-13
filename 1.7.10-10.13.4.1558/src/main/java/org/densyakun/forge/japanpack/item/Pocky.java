package org.densyakun.forge.japanpack.item;
import org.densyakun.forge.japanpack.Main;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
public class Pocky extends ItemFood {
	int type;
	public Pocky(int type) {
		super(type == 2 ? 1 : type == 1 ? 2 : 3, false);
		switch (this.type = type) {
		case 0: 
			setMaxStackSize(4);
			setUnlocalizedName("pocky_normal");
			setTextureName("japanpack:pocky_normal");
			break;
		case 1: 
			setMaxStackSize(8);
			setUnlocalizedName("pocky_thin");
			setTextureName("japanpack:pocky_thin");
			break;
		case 2: 
			setMaxStackSize(12);
			setUnlocalizedName("pocky_broken");
			setTextureName("japanpack:pocky_broken");
			break;
		}
	}
	public static void broken(ItemStack itemstack, EntityPlayer player) {
		if ((itemstack.getItem() instanceof Pocky) && (((Pocky)itemstack.getItem()).type != 2)) {
			if (player.inventory.addItemStackToInventory(new ItemStack(Main.pocky_broken, ((Pocky)itemstack.getItem()).type == 0 ? 3 : 2))) {
				itemstack.stackSize -= 1;
			}
			player.addStat(Main.achievement_pocky_broken, 1);
		}
	}
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		broken(stack, player);
		return false;
	}
	public boolean onBlockStartBreak(ItemStack itemstack, int X, int Y, int Z, EntityPlayer player) {
		broken(itemstack, player);
		return false;
	}
}
