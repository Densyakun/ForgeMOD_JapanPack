package org.densyakun.forge.japanpack.block;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenMegaPineTree;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
public class SakuraSapling extends BlockBush implements IGrowable {
	public SakuraSapling() {
		super(Material.plants);
		setBlockName("sapling_sakura");
		setBlockTextureName("japanpack:sapling_sakura");
	}
	public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_) {
		if (!p_149674_1_.isRemote) {
			super.updateTick(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_, p_149674_5_);
			if ((p_149674_1_.getBlockLightValue(p_149674_2_, p_149674_3_ + 1, p_149674_4_) >= 9) && (p_149674_5_.nextInt(7) == 0)) {
				func_149879_c(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_, p_149674_5_);
			}
		}
	}
	public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_, boolean p_149851_5_) {
		return true;
	}
	public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_) {
		return p_149852_1_.rand.nextFloat() < 0.45F;
	}
	public void func_149853_b(World p_149853_1_, Random p_149853_2_, int p_149853_3_, int p_149853_4_, int p_149853_5_) {
		func_149879_c(p_149853_1_, p_149853_3_, p_149853_4_, p_149853_5_, p_149853_2_);
	}
	public void func_149879_c(World p_149879_1_, int p_149879_2_, int p_149879_3_, int p_149879_4_, Random p_149879_5_) {
		int l = p_149879_1_.getBlockMetadata(p_149879_2_, p_149879_3_, p_149879_4_);
		if ((l & 0x8) == 0) {
			p_149879_1_.setBlockMetadataWithNotify(p_149879_2_, p_149879_3_, p_149879_4_, l | 0x8, 4);
		} else {
			func_149878_d(p_149879_1_, p_149879_2_, p_149879_3_, p_149879_4_, p_149879_5_);
		}
	}
	public void func_149878_d(World p_149878_1_, int p_149878_2_, int p_149878_3_, int p_149878_4_, Random p_149878_5_) {
		if (!TerrainGen.saplingGrowTree(p_149878_1_, p_149878_5_, p_149878_2_, p_149878_3_, p_149878_4_)) {
			return;
		}
		int l = p_149878_1_.getBlockMetadata(p_149878_2_, p_149878_3_, p_149878_4_) & 0x7;
		Object object = p_149878_5_.nextInt(10) == 0 ? new WorldGenBigTree(true) : new WorldGenTrees(true);
		int i1 = 0;
		int j1 = 0;
		boolean flag = false;
		switch (l) {
		case 0:
		default:
			break;
		case 1:
			for (i1 = 0; i1 >= -1; i1--) {
				for (j1 = 0; j1 >= -1; j1--) {
					if ((func_149880_a(p_149878_1_, p_149878_2_ + i1, p_149878_3_, p_149878_4_ + j1, 1)) && (func_149880_a(p_149878_1_, p_149878_2_ + i1 + 1, p_149878_3_, p_149878_4_ + j1, 1)) && (func_149880_a(p_149878_1_, p_149878_2_ + i1, p_149878_3_, p_149878_4_ + j1 + 1, 1)) && (func_149880_a(p_149878_1_, p_149878_2_ + i1 + 1, p_149878_3_, p_149878_4_ + j1 + 1, 1))) {
						object = new WorldGenMegaPineTree(false, p_149878_5_.nextBoolean());
						flag = true;
						break;
					}
				}
			}
			if (!flag) {
				j1 = 0;
				i1 = 0;
				object = new WorldGenTaiga2(true);
			}
			break;
		case 2:
			object = new WorldGenForest(true, false);
			break;
		case 3:
			for (i1 = 0; i1 >= -1; i1--) {
				for (j1 = 0; j1 >= -1; j1--) {
					if ((func_149880_a(p_149878_1_, p_149878_2_ + i1, p_149878_3_, p_149878_4_ + j1, 3)) && (func_149880_a(p_149878_1_, p_149878_2_ + i1 + 1, p_149878_3_, p_149878_4_ + j1, 3)) && (func_149880_a(p_149878_1_, p_149878_2_ + i1, p_149878_3_, p_149878_4_ + j1 + 1, 3)) && (func_149880_a(p_149878_1_, p_149878_2_ + i1 + 1, p_149878_3_, p_149878_4_ + j1 + 1, 3))) {
						object = new WorldGenMegaJungle(true, 10, 20, 3, 3);
						flag = true;
						break;
					}
				}
			}
			if (!flag) {
				j1 = 0;
				i1 = 0;
				object = new WorldGenTrees(true, 4 + p_149878_5_.nextInt(7), 3, 3, false);
			}
			break;
		case 4:
			object = new WorldGenSavannaTree(true);
			break;
		case 5:
			for (i1 = 0; i1 >= -1; i1--) {
				for (j1 = 0; j1 >= -1; j1--) {
					if ((func_149880_a(p_149878_1_, p_149878_2_ + i1, p_149878_3_, p_149878_4_ + j1, 5)) && (func_149880_a(p_149878_1_, p_149878_2_ + i1 + 1, p_149878_3_, p_149878_4_ + j1, 5)) && (func_149880_a(p_149878_1_, p_149878_2_ + i1, p_149878_3_, p_149878_4_ + j1 + 1, 5)) && (func_149880_a(p_149878_1_, p_149878_2_ + i1 + 1, p_149878_3_, p_149878_4_ + j1 + 1, 5))) {
						object = new WorldGenCanopyTree(true);
						flag = true;
						break;
					}
				}
			}
			if (!flag) {
				return;
			}
			break;
		}
		Block block = Blocks.air;
		if (flag) {
			p_149878_1_.setBlock(p_149878_2_ + i1, p_149878_3_, p_149878_4_ + j1, block, 0, 4);
			p_149878_1_.setBlock(p_149878_2_ + i1 + 1, p_149878_3_, p_149878_4_ + j1, block, 0, 4);
			p_149878_1_.setBlock(p_149878_2_ + i1, p_149878_3_, p_149878_4_ + j1 + 1, block, 0, 4);
			p_149878_1_.setBlock(p_149878_2_ + i1 + 1, p_149878_3_, p_149878_4_ + j1 + 1, block, 0, 4);
		} else {
			p_149878_1_.setBlock(p_149878_2_, p_149878_3_, p_149878_4_, block, 0, 4);
		}
		if (!((WorldGenerator)object).generate(p_149878_1_, p_149878_5_, p_149878_2_ + i1, p_149878_3_, p_149878_4_ + j1)) {
			if (flag) {
				p_149878_1_.setBlock(p_149878_2_ + i1, p_149878_3_, p_149878_4_ + j1, this, l, 4);
				p_149878_1_.setBlock(p_149878_2_ + i1 + 1, p_149878_3_, p_149878_4_ + j1, this, l, 4);
				p_149878_1_.setBlock(p_149878_2_ + i1, p_149878_3_, p_149878_4_ + j1 + 1, this, l, 4);
				p_149878_1_.setBlock(p_149878_2_ + i1 + 1, p_149878_3_, p_149878_4_ + j1 + 1, this, l, 4);
			} else {
				p_149878_1_.setBlock(p_149878_2_, p_149878_3_, p_149878_4_, this, l, 4);
			}
		}
	}
	public boolean func_149880_a(World p_149880_1_, int p_149880_2_, int p_149880_3_, int p_149880_4_, int p_149880_5_) {
		return (p_149880_1_.getBlock(p_149880_2_, p_149880_3_, p_149880_4_) == this) && ((p_149880_1_.getBlockMetadata(p_149880_2_, p_149880_3_, p_149880_4_) & 0x7) == p_149880_5_);
	}
}
