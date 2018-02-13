package org.densyakun.forge.japanpack.block;
import java.util.Random;

import org.densyakun.forge.japanpack.Main;
import org.densyakun.forge.japanpack.item.Key;
import org.densyakun.forge.japanpack.tileentity.TileEntityMoneyChanger;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
public class MoneyChanger extends BlockContainer {
	private final Random field_149955_b = new Random();
	public MoneyChanger() {
		super(Material.iron);
		setStepSound(Block.soundTypeMetal);
		setBlockName("money_changer");
		setBlockTextureName("japanpack:money_changer");
	}
	@Override
	public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		TileEntityMoneyChanger tile = (TileEntityMoneyChanger) p_149727_1_.getTileEntity(p_149727_2_, p_149727_3_, p_149727_4_);
		if (!p_149727_1_.isRemote && tile != null) {
			ItemStack itemstack = p_149727_5_.getCurrentEquippedItem();
			if (itemstack != null && itemstack.getItem() instanceof Key) {
				try {
					int key = Key.getCode(itemstack);
					if (key == 0) {
						p_149727_5_.addChatMessage(new ChatComponentText("鍵が破損しています"));
					} else {
						int truecode = tile.getKeycode();
						if (truecode == 0) {
							tile.setKeycode(key);
							p_149727_5_.addChatMessage(new ChatComponentText("鍵を設定しました"));
						} else 	if (key == truecode) {
							p_149727_5_.openGui(Main.INSTANCE, Main.GUI_ID_mc_money, p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_);
						} else {
							p_149727_5_.addChatMessage(new ChatComponentText("鍵が合いません"));
						}
					}
				} catch (NullPointerException e) {
					p_149727_5_.addChatMessage(new ChatComponentText("鍵が破損しています"));
				}
			} else {
				p_149727_5_.openGui(Main.INSTANCE, Main.GUI_ID_mc_change, p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_);
			}
		}
		return true;
	}
	@Override
	public TileEntity createNewTileEntity(World world, int a) {
		return new TileEntityMoneyChanger();
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = Blocks.wool.getIcon(1, 2);
	}
	@Override
	public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_,
			int p_149749_6_) {
		TileEntityMoneyChanger te = (TileEntityMoneyChanger)p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);
		if (te != null) {
			for (int i1 = 0; i1 < te.getSizeInventory(); ++i1) {
				ItemStack itemstack = te.getStackInSlot(i1);

				if (itemstack != null)
				{
					float f = this.field_149955_b.nextFloat() * 0.8F + 0.1F;
					float f1 = this.field_149955_b.nextFloat() * 0.8F + 0.1F;
					EntityItem entityitem;

					for (float f2 = this.field_149955_b.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; p_149749_1_.spawnEntityInWorld(entityitem))
					{
						int j1 = this.field_149955_b.nextInt(21) + 10;

						if (j1 > itemstack.stackSize)
						{
							j1 = itemstack.stackSize;
						}

						itemstack.stackSize -= j1;
						entityitem = new EntityItem(p_149749_1_, (double)((float)p_149749_2_ + f), (double)((float)p_149749_3_ + f1), (double)((float)p_149749_4_ + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
						float f3 = 0.05F;
						entityitem.motionX = (double)((float)this.field_149955_b.nextGaussian() * f3);
						entityitem.motionY = (double)((float)this.field_149955_b.nextGaussian() * f3 + 0.2F);
						entityitem.motionZ = (double)((float)this.field_149955_b.nextGaussian() * f3);

						if (itemstack.hasTagCompound())
						{
							entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
						}
					}
				}
			}

			p_149749_1_.func_147453_f(p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_);
		}

		super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
	}
}
