package org.densyakun.forge.japanpack.item;
import com.google.common.collect.Multimap;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
public class Katana extends Item {
	public Katana() {
		setNoRepair();
		setMaxStackSize(1);
		setMaxDamage(8192);
		setUnlocalizedName("katana");
		setTextureName("japanpack:katana");
	}
	public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_) {
		p_77644_1_.damageItem(1, p_77644_3_);
		return true;
	}
	public boolean onBlockDestroyed(ItemStack p_150894_1_, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_) {
		if (p_150894_3_.getBlockHardness(p_150894_2_, p_150894_4_, p_150894_5_, p_150894_6_) != 0.0D) {
			p_150894_1_.damageItem(2, p_150894_7_);
		}
		return true;
	}
	public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
		p_77659_3_.setItemInUse(p_77659_1_, getMaxItemUseDuration(p_77659_1_));
		return p_77659_1_;
	}
	public boolean func_150897_b(Block p_150897_1_) {
		return p_150897_1_ == Blocks.web;
	}
	public int getItemEnchantability() {
		return Item.ToolMaterial.EMERALD.getEnchantability();
	}
	public Multimap getItemAttributeModifiers() {
		Multimap multimap = super.getItemAttributeModifiers();
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", 15.0D, 0));
		return multimap;
	}
}
