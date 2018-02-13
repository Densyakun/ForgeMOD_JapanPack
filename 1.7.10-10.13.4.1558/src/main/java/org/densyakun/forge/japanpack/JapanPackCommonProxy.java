package org.densyakun.forge.japanpack;
import org.densyakun.forge.japanpack.tileentity.TileEntityMoneyChanger;
import org.densyakun.forge.japanpack.tileentity.TileEntityTataraFurnace;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.world.World;
public class JapanPackCommonProxy {
	public World getClientWorld() {
		return null;
	}
	public void registerTileEntity() {
		GameRegistry.registerTileEntity(TileEntityTataraFurnace.class, "TileEntityTataraFurnace");
		GameRegistry.registerTileEntity(TileEntityMoneyChanger.class, "TileEntityMoneyChanger");
	}
}
