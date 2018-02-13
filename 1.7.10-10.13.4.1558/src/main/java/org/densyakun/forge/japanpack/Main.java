package org.densyakun.forge.japanpack;
import java.util.ArrayList;
import java.util.List;

import org.densyakun.forge.japanpack.block.MoneyChanger;
import org.densyakun.forge.japanpack.block.SakuraSapling;
import org.densyakun.forge.japanpack.block.TataraFurnace;
import org.densyakun.forge.japanpack.inventory.MoneyChangerChangeContainer;
import org.densyakun.forge.japanpack.inventory.MoneyChangerChangeGuiContainer;
import org.densyakun.forge.japanpack.inventory.MoneyChangerMoneyContainer;
import org.densyakun.forge.japanpack.inventory.MoneyChangerMoneyGuiContainer;
import org.densyakun.forge.japanpack.item.FiftyYen;
import org.densyakun.forge.japanpack.item.FiveHundredYen;
import org.densyakun.forge.japanpack.item.FiveThousandYen;
import org.densyakun.forge.japanpack.item.FiveYen;
import org.densyakun.forge.japanpack.item.Katana;
import org.densyakun.forge.japanpack.item.Key;
import org.densyakun.forge.japanpack.item.OneHundredYen;
import org.densyakun.forge.japanpack.item.OneThousandYen;
import org.densyakun.forge.japanpack.item.OneYen;
import org.densyakun.forge.japanpack.item.Pocky;
import org.densyakun.forge.japanpack.item.Pocky_Pack;
import org.densyakun.forge.japanpack.item.RecordKimigayo;
import org.densyakun.forge.japanpack.item.TenThousandYen;
import org.densyakun.forge.japanpack.item.TenYen;
import org.densyakun.forge.japanpack.item.TwoThousandYen;
import org.densyakun.forge.japanpack.tileentity.TileEntityMoneyChanger;

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
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
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
import net.minecraft.world.World;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
@Mod(modid="japanpack", version="0.0.1a")
public class Main implements IFuelHandler, IGuiHandler {
	public static final String modid = "japanpack";
	public static final String version = "0.0.1a";
	public static final int GUI_ID_mc_money = 0;
	public static final int GUI_ID_mc_change = 1;
	public static final Block sapling_sakura = new SakuraSapling().setCreativeTab(CreativeTabs.tabDecorations);
	public static final Block tatara_furnace = new TataraFurnace().setCreativeTab(CreativeTabs.tabDecorations);
	public static final Block money_changer = new MoneyChanger().setCreativeTab(CreativeTabs.tabDecorations);
	public static final Item pocky_normal = new Pocky(0).setCreativeTab(CreativeTabs.tabFood);
	public static final Item pocky_thin = new Pocky(1).setCreativeTab(CreativeTabs.tabFood);
	public static final Item pocky_broken = new Pocky(2).setCreativeTab(CreativeTabs.tabFood);
	public static final Item pocky_pack_normal = new Pocky_Pack(false).setCreativeTab(CreativeTabs.tabFood);
	public static final Item pocky_pack_thin = new Pocky_Pack(true).setCreativeTab(CreativeTabs.tabFood);
	public static final Item katana = new Katana().setCreativeTab(CreativeTabs.tabCombat);
	public static final Item oneyen = new OneYen().setCreativeTab(CreativeTabs.tabMisc);
	public static final Item fiveyen = new FiveYen().setCreativeTab(CreativeTabs.tabMisc);
	public static final Item tenyen = new TenYen().setCreativeTab(CreativeTabs.tabMisc);
	public static final Item fiftyyen = new FiftyYen().setCreativeTab(CreativeTabs.tabMisc);
	public static final Item onehundredyen = new OneHundredYen().setCreativeTab(CreativeTabs.tabMisc);
	public static final Item fivehundredyen = new FiveHundredYen().setCreativeTab(CreativeTabs.tabMisc);
	public static final Item onethousandyen = new OneThousandYen().setCreativeTab(CreativeTabs.tabMisc);
	public static final Item twothousandyen = new TwoThousandYen().setCreativeTab(CreativeTabs.tabMisc);
	public static final Item fivethousandyen = new FiveThousandYen().setCreativeTab(CreativeTabs.tabMisc);
	public static final Item tenthousandyen = new TenThousandYen().setCreativeTab(CreativeTabs.tabMisc);
	public static final Item key = new Key().setCreativeTab(CreativeTabs.tabMisc);
	public static final Item record_kimigayo = new RecordKimigayo().setCreativeTab(CreativeTabs.tabMisc);
	public static final Achievement achievement_pocky = new Achievement("japanpack:pocky", "pocky", 2, -2, pocky_normal, null).setSpecial().registerStat();
	public static final Achievement achievement_pocky_broken = new Achievement("japanpack:pocky_broken", "pocky_broken", 4, -4, pocky_broken, achievement_pocky).registerStat();
	public static final Achievement achievement_pocky_packed = new Achievement("japanpack:pocky_packed", "pocky_packed", 4, -2, pocky_pack_normal, achievement_pocky).registerStat();
	public static final Achievement achievement_billionaire = new Achievement("japanpack:billionaire", "billionaire", -2, -2, tenthousandyen, null).setSpecial().registerStat();
	@Mod.Instance(modid)
	public static Main INSTANCE;
	@SidedProxy(clientSide = "org.densyakun.forge.japanpack.JapanPackClientProxy", serverSide = "org.densyakun.forge.japanpack.JapanPackCommonProxy")
	public static JapanPackCommonProxy proxy;
	/*
	財布
	和膳
	和食>寿司
	和傘
	日本に生息する（していた）動物
	手押し車
	和菓子 今川焼き
	こたつ
	梅: 梅酒
	日本酒
	焼酎
	竹
	障子
	鉄道関連
	和服
	ochinの形の「山」アイテム
	ドラえもん
	夜露死苦Tシャツ
	お墓
	てるてる坊主
	赤松
	門松
	お茶
	落花生
	いちご
	熊
	枝豆
	たこわさ
	イカの塩辛
	*/
	@EventHandler
	public void preInit(FMLPostInitializationEvent event) {
		GameRegistry.registerBlock(sapling_sakura, "sapling_sakura");
		GameRegistry.registerBlock(tatara_furnace, "tatara_furnace");
		GameRegistry.registerBlock(money_changer, "money_changer");
		GameRegistry.registerItem(pocky_normal, "pocky_normal");
		GameRegistry.registerItem(pocky_thin, "pocky_thin");
		GameRegistry.registerItem(pocky_broken, "pocky_broken");
		GameRegistry.registerItem(pocky_pack_normal, "pocky_pack_normal");
		GameRegistry.registerItem(pocky_pack_thin, "pocky_pack_thin");
		GameRegistry.registerItem(katana, "katana");
		GameRegistry.registerItem(oneyen, "oneyen");
		GameRegistry.registerItem(fiveyen, "fiveyen");
		GameRegistry.registerItem(tenyen, "tenyen");
		GameRegistry.registerItem(fiftyyen, "fiftyyen");
		GameRegistry.registerItem(onehundredyen, "onehundredyen");
		GameRegistry.registerItem(fivehundredyen, "fivehundredyen");
		GameRegistry.registerItem(onethousandyen, "onethousandyen");
		GameRegistry.registerItem(twothousandyen, "twothousandyen");
		GameRegistry.registerItem(fivethousandyen, "fivethousandyen");
		GameRegistry.registerItem(tenthousandyen, "tenthousandyen");
		GameRegistry.registerItem(key, "key");
		GameRegistry.registerItem(record_kimigayo, "record_kimigayo");
		GameRegistry.registerTileEntity(TileEntityMoneyChanger.class, "money_changer");
		GameRegistry.addRecipe(new ItemStack(tatara_furnace), new Object[] { "ccc", "ccc", "cfc", 'c', new ItemStack(Blocks.hardened_clay), 'f', new ItemStack(Blocks.furnace) });
		GameRegistry.addRecipe(new ItemStack(pocky_normal), new Object[] { " c ", "cwc", " cw", 'c', new ItemStack(Items.dye, 1, 3), 'w', new ItemStack(Items.wheat) });
		GameRegistry.addRecipe(new ItemStack(pocky_thin, 2), new Object[] { " c ", "cw ", "  w", 'c', new ItemStack(Items.dye, 1, 3), 'w', new ItemStack(Items.wheat) });
		GameRegistry.addRecipe(new ItemStack(money_changer), new Object[] { "iii", " i ", "sss", 'i', new ItemStack(Items.iron_ingot), 's', new ItemStack(Blocks.stone) });
		GameRegistry.addRecipe(new ItemStack(key), new Object[] { " ii", " ii", "i  ", 'i', new ItemStack(Items.iron_ingot) });
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
		a.add(achievement_billionaire);
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
		LanguageRegistry.instance().addStringLocalization("achievement.billionaire", "en_US", "Billionaire");
		LanguageRegistry.instance().addStringLocalization("achievement.billionaire.desc", "en_US", "");
		LanguageRegistry.instance().addStringLocalization("achievement.billionaire", "ja_JP", "億万長者");
		LanguageRegistry.instance().addStringLocalization("achievement.billionaire.desc", "ja_JP", "");
		LanguageRegistry.instance().addStringLocalization("tile.sapling_sakura.name", "en_US", "Sakura Sapling");
		LanguageRegistry.instance().addStringLocalization("tile.sapling_sakura.name", "ja_JP", "桜の苗木");
		LanguageRegistry.instance().addStringLocalization("tile.tatara_furnace.name", "en_US", "Tatara Furnace");
		LanguageRegistry.instance().addStringLocalization("tile.tatara_furnace.name", "ja_JP", "たたら炉");
		LanguageRegistry.instance().addStringLocalization("tile.money_changer.name", "en_US", "Money Changer");
		LanguageRegistry.instance().addStringLocalization("tile.money_changer.name", "ja_JP", "両替機");
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
		LanguageRegistry.instance().addStringLocalization("item.oneyen.name", "en_US", "1\\");
		LanguageRegistry.instance().addStringLocalization("item.oneyen.name", "ja_JP", "一円玉");
		LanguageRegistry.instance().addStringLocalization("item.fiveyen.name", "en_US", "5\\");
		LanguageRegistry.instance().addStringLocalization("item.fiveyen.name", "ja_JP", "五円玉");
		LanguageRegistry.instance().addStringLocalization("item.tenyen.name", "en_US", "10\\");
		LanguageRegistry.instance().addStringLocalization("item.tenyen.name", "ja_JP", "十円玉");
		LanguageRegistry.instance().addStringLocalization("item.fiftyyen.name", "en_US", "50\\");
		LanguageRegistry.instance().addStringLocalization("item.fiftyyen.name", "ja_JP", "五十円玉");
		LanguageRegistry.instance().addStringLocalization("item.onehundredyen.name", "en_US", "100\\");
		LanguageRegistry.instance().addStringLocalization("item.onehundredyen.name", "ja_JP", "百円玉");
		LanguageRegistry.instance().addStringLocalization("item.fivehundredyen.name", "en_US", "500\\");
		LanguageRegistry.instance().addStringLocalization("item.fivehundredyen.name", "ja_JP", "五百円玉");
		LanguageRegistry.instance().addStringLocalization("item.onethousandyen.name", "en_US", "1000\\");
		LanguageRegistry.instance().addStringLocalization("item.onethousandyen.name", "ja_JP", "千円札");
		LanguageRegistry.instance().addStringLocalization("item.twothousandyen.name", "en_US", "2000\\");
		LanguageRegistry.instance().addStringLocalization("item.twothousandyen.name", "ja_JP", "二千円札");
		LanguageRegistry.instance().addStringLocalization("item.fivethousandyen.name", "en_US", "5000\\");
		LanguageRegistry.instance().addStringLocalization("item.fivethousandyen.name", "ja_JP", "五千円札");
		LanguageRegistry.instance().addStringLocalization("item.tenthousandyen.name", "en_US", "10000\\");
		LanguageRegistry.instance().addStringLocalization("item.tenthousandyen.name", "ja_JP", "一万円札");
		LanguageRegistry.instance().addStringLocalization("item.key.name", "en_US", "key");
		LanguageRegistry.instance().addStringLocalization("item.key.name", "ja_JP", "鍵");
		LanguageRegistry.instance().addStringLocalization("item.record_kimigayo.name", "en_US", "Record Kimigayo");
		LanguageRegistry.instance().addStringLocalization("item.record.kimigayo.desc", "en_US", "Kimigayo");
		LanguageRegistry.instance().addStringLocalization("item.record_kimigayo.name", "ja_JP", "レコード");
		LanguageRegistry.instance().addStringLocalization("item.record.kimigayo.desc", "ja_JP", "君が代");
		LanguageRegistry.instance().addStringLocalization("container.money_changer_tile_entity", "en_US", "Money Changer");
		LanguageRegistry.instance().addStringLocalization("container.money_changer_tile_entity", "ja_JP", "両替機");

		AchievementPage.registerAchievementPage(new AchievementPage("Japan Pack", (Achievement[]) a.toArray(new Achievement[a.size()])));
	}
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.registerTileEntity();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, this);
	}
	@SubscribeEvent(priority=EventPriority.NORMAL)
	public void LivingDeath(LivingDeathEvent event) {
		if (event.entity instanceof EntityPlayer) {
			ItemStack[] items = ((EntityPlayer) event.entity).inventory.mainInventory;
			for (int a = 0; a < items.length;) {
				if (items[a].getItem().getUnlocalizedName().equals(pocky_broken)) {
					ItemStack[] newitems = new ItemStack[items.length - 1];
					for (int b = 0; b < items.length - 1; b++) {
						newitems[b] = items[a <= b ? b + 1 : b];
					}
					((EntityPlayer) event.entity).inventory.mainInventory = newitems;
				} else if (items[a].getItem() instanceof Pocky) {
					Pocky.broken(items[a], (EntityPlayer) event.entity);
					a++;
				} else if (items[a].getItem() instanceof Pocky_Pack) {
					Pocky_Pack.open(items[a], (EntityPlayer) event.entity);
					a++;
				}
			}
		}
	}
	@SubscribeEvent(priority=EventPriority.NORMAL)
	public void LivingJump(LivingJumpEvent event) {
		if (event.entity instanceof EntityPlayer) {
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
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == GUI_ID_mc_money) {
			return new MoneyChangerMoneyContainer(player.inventory, (TileEntityMoneyChanger) world.getTileEntity(x, y, z));
		}
		if (ID == GUI_ID_mc_change) {
			return new MoneyChangerChangeContainer(player.inventory, (TileEntityMoneyChanger) world.getTileEntity(x, y, z));
		}
		return null;
	}
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == GUI_ID_mc_money) {
			return new MoneyChangerMoneyGuiContainer(player.inventory, (TileEntityMoneyChanger) world.getTileEntity(x, y, z));
		}
		if (ID == GUI_ID_mc_change) {
			return new MoneyChangerChangeGuiContainer(player.inventory, (TileEntityMoneyChanger) world.getTileEntity(x, y, z));
		}
		return null;
	}
}
