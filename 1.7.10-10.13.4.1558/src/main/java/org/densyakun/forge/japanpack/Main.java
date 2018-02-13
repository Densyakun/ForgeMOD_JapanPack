package org.densyakun.forge.japanpack;
import java.util.ArrayList;
import java.util.List;

import org.densyakun.forge.japanpack.block.SakuraSapling;
import org.densyakun.forge.japanpack.block.TataraFurnace;
import org.densyakun.forge.japanpack.item.Katana;
import org.densyakun.forge.japanpack.item.Pocky;
import org.densyakun.forge.japanpack.item.Pocky_Pack;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
@Mod(modid="japanpack", version="0.0.1a")
public class Main implements IFuelHandler {
	public static final String modid = "japanpack";
	public static final String version = "0.0.1a";
	public static final Block sapling_sakura = new SakuraSapling().setCreativeTab(CreativeTabs.tabDecorations);
	public static final Block tatara_furnace = new TataraFurnace().setCreativeTab(CreativeTabs.tabDecorations);
	public static final Item pocky_normal = new Pocky(0).setCreativeTab(CreativeTabs.tabFood);
	public static final Item pocky_thin = new Pocky(1).setCreativeTab(CreativeTabs.tabFood);
	public static final Item pocky_broken = new Pocky(2).setCreativeTab(CreativeTabs.tabFood);
	public static final Item pocky_pack_normal = new Pocky_Pack(false).setCreativeTab(CreativeTabs.tabFood);
	public static final Item pocky_pack_thin = new Pocky_Pack(true).setCreativeTab(CreativeTabs.tabFood);
	public static final Item katana = new Katana().setCreativeTab(CreativeTabs.tabCombat);
	public static final Achievement achievement_pocky = new Achievement("japanpack:pocky", "pocky", 0, 0, pocky_normal, null).setSpecial().registerStat();
	public static final Achievement achievement_pocky_broken = new Achievement("japanpack:pocky_broken", "pocky_broken", 2, -1, pocky_broken, achievement_pocky).registerStat();
	public static final Achievement achievement_pocky_packed = new Achievement("japanpack:pocky_packed", "pocky_packed", 2, 1, pocky_pack_normal, achievement_pocky).registerStat();
	@Mod.Instance(modid)
	public static Main INSTANCE;
	@SidedProxy(clientSide = "org.densyakun.forge.japanpack.JapanPackClientProxy", serverSide = "org.densyakun.forge.japanpack.JapanPackCommonProxy")
	public static JapanPackCommonProxy proxy;
	/*和膳
	和食
	和傘
	手押し車
	和菓子
	こたつ
	梅: 梅酒
	竹
	障子*/
	@EventHandler
	public void preInit(FMLPostInitializationEvent event) {
		GameRegistry.registerBlock(sapling_sakura, "sapling_sakura");
		GameRegistry.registerBlock(tatara_furnace, "tatara_furnace");
		GameRegistry.registerItem(pocky_normal, "pocky_normal");
		GameRegistry.registerItem(pocky_thin, "pocky_thin");
		GameRegistry.registerItem(pocky_broken, "pocky_broken");
		GameRegistry.registerItem(pocky_pack_normal, "pocky_pack_normal");
		GameRegistry.registerItem(pocky_pack_thin, "pocky_pack_thin");
		GameRegistry.registerItem(katana, "katana");
		GameRegistry.addRecipe(new ItemStack(tatara_furnace), new Object[] { "ccc", "ccc", "cfc", Character.valueOf('c'), new ItemStack(Blocks.hardened_clay), Character.valueOf('f'), new ItemStack(Blocks.furnace) });
		GameRegistry.addRecipe(new ItemStack(pocky_normal), new Object[] { " c ", "cwc", " cw", Character.valueOf('c'), new ItemStack(Items.dye, 1, 3), Character.valueOf('w'), new ItemStack(Items.wheat) });
		GameRegistry.addRecipe(new ItemStack(pocky_thin, 2), new Object[] { " c ", "cw ", "  w", Character.valueOf('c'), new ItemStack(Items.dye, 1, 3), Character.valueOf('w'), new ItemStack(Items.wheat) });
		GameRegistry.addShapelessRecipe(new ItemStack(pocky_pack_normal), new Object[] { new ItemStack(pocky_normal), new ItemStack(pocky_normal), new ItemStack(pocky_normal), new ItemStack(pocky_normal) });
		GameRegistry.addShapelessRecipe(new ItemStack(pocky_pack_thin), new Object[] { new ItemStack(pocky_thin), new ItemStack(pocky_thin), new ItemStack(pocky_thin), new ItemStack(pocky_thin), new ItemStack(pocky_thin), new ItemStack(pocky_thin), new ItemStack(pocky_thin), new ItemStack(pocky_thin) });
		GameRegistry.registerFuelHandler(this);
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.TERRAIN_GEN_BUS.register(this);
		MinecraftForge.ORE_GEN_BUS.register(this);
		FMLCommonHandler.instance().bus().register(this);
		
		List<Achievement> a = new ArrayList();
		a.add(achievement_pocky);
		a.add(achievement_pocky_broken);
		a.add(achievement_pocky_packed);
		LanguageRegistry.instance().addStringLocalization("achievement.pocky", "en_US", "Make a Pocky!");
		LanguageRegistry.instance().addStringLocalization("achievement.pocky.desc", "en_US", "Wheat and Cocoa Beans!");
		LanguageRegistry.instance().addStringLocalization("achievement.pocky", "ja_JP", "ポッキーを作る");
		LanguageRegistry.instance().addStringLocalization("achievement.pocky.desc", "ja_JP", "カカオ豆、それから小麦！");
		LanguageRegistry.instance().addStringLocalization("achievement.pocky_broken", "en_US", "Fold the Pocky!");
		LanguageRegistry.instance().addStringLocalization("achievement.pocky_broken.desc", "en_US", "");
		LanguageRegistry.instance().addStringLocalization("achievement.pocky_broken", "ja_JP", "ポッキーを折る");
		LanguageRegistry.instance().addStringLocalization("achievement.pocky_broken.desc", "ja_JP", "ポキッ！");
		LanguageRegistry.instance().addStringLocalization("achievement.pocky_packed", "en_US", "Boxed the Pocky!");
		LanguageRegistry.instance().addStringLocalization("achievement.pocky_packed.desc", "en_US", "");
		LanguageRegistry.instance().addStringLocalization("achievement.pocky_packed", "ja_JP", "ポッキーを箱詰めする");
		LanguageRegistry.instance().addStringLocalization("achievement.pocky_packed.desc", "ja_JP", "ポッキーがいっぱい！");
		LanguageRegistry.instance().addStringLocalization("tile.sapling_sakura.name", "en_US", "Sakura Sapling");
		LanguageRegistry.instance().addStringLocalization("tile.sapling_sakura.name", "ja_JP", "桜の苗木");
		LanguageRegistry.instance().addStringLocalization("tile.tatara_furnace.name", "en_US", "Tatara Furnace");
		LanguageRegistry.instance().addStringLocalization("tile.tatara_furnace.name", "ja_JP", "たたら炉");
		LanguageRegistry.instance().addStringLocalization("item.pocky_normal.name", "en_US", "Pocky");
		LanguageRegistry.instance().addStringLocalization("item.pocky_normal.name", "ja_JP", "Pocky");
		LanguageRegistry.instance().addStringLocalization("item.pocky_thin.name", "en_US", "Pocky(thin)");
		LanguageRegistry.instance().addStringLocalization("item.pocky_thin.name", "ja_JP", "Pocky(極細)");
		LanguageRegistry.instance().addStringLocalization("item.pocky_broken.name", "en_US", "broken Pocky");
		LanguageRegistry.instance().addStringLocalization("item.pocky_broken.name", "ja_JP", "折れたPocky");
		LanguageRegistry.instance().addStringLocalization("item.pocky_pack_normal.name", "en_US", "Boxed Pocky");
		LanguageRegistry.instance().addStringLocalization("item.pocky_pack_normal.name", "ja_JP", "Pockyの箱");
		LanguageRegistry.instance().addStringLocalization("item.pocky_pack_thin.name", "en_US", "Boxed Pocky(thin)");
		LanguageRegistry.instance().addStringLocalization("item.pocky_pack_thin.name", "ja_JP", "Pockyの箱(極細)");
		LanguageRegistry.instance().addStringLocalization("item.katana.name", "en_US", "Katana");
		LanguageRegistry.instance().addStringLocalization("item.katana.name", "ja_JP", "刀");
		AchievementPage.registerAchievementPage(new AchievementPage("Japan Pack", (Achievement[])a.toArray(new Achievement[a.size()])));
	}
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.registerTileEntity();
	}
	@SubscribeEvent(priority=EventPriority.NORMAL)
	public void LivingJump(LivingJumpEvent event) {
		if ((event.entity instanceof EntityPlayer)) {
			event.entity.addVelocity(0.0D, 0.25D, 0.0D);
		}
	}
	@SubscribeEvent(priority=EventPriority.NORMAL)
	public void ItemCrafted(ItemCraftedEvent event) {
		if ((Item.getIdFromItem(event.crafting.getItem()) == Item.getIdFromItem(pocky_normal)) || (Item.getIdFromItem(event.crafting.getItem()) == Item.getIdFromItem(pocky_thin))) {
			event.player.addStat(achievement_pocky, 1);
		}
		if ((Item.getIdFromItem(event.crafting.getItem()) == Item.getIdFromItem(pocky_pack_normal)) || (Item.getIdFromItem(event.crafting.getItem()) == Item.getIdFromItem(pocky_pack_thin))) {
			event.player.addStat(achievement_pocky_packed, 1);
		}
	}
	public int getBurnTime(ItemStack fuel) {
		Block block = Block.getBlockFromItem(fuel.getItem());
		if ((block != null) && (Block.getIdFromBlock(block) == Block.getIdFromBlock(sapling_sakura))) {
			return 100;
		}
		return 0;
	}
}
