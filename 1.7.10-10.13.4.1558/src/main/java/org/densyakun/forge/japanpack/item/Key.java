package org.densyakun.forge.japanpack.item;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
public class Key extends Item {
	public Key() {
		setMaxStackSize(1);
		setUnlocalizedName("key");
		setTextureName("japanpack:key");
	}
	@Override
	public void onUpdate(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
		if (!p_77663_2_.isRemote) {
			if (p_77663_1_.getTagCompound() == null) {
				NBTTagCompound nbt = new NBTTagCompound();
				p_77663_1_.setTagCompound(nbt);
				nbt.setInteger("code", new Random().nextInt(Integer.MAX_VALUE));
			}
		}
	}
	public static int getCode(ItemStack itemstack) {
		NBTTagCompound nbt = itemstack.getTagCompound();
		if (nbt != null) {
			return nbt.getInteger("code");
		}
		return 0;
	}
}
