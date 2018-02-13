package org.densyakun.forge.japanpack.item;
import org.densyakun.forge.japanpack.Main;

import net.minecraft.item.ItemRecord;
import net.minecraft.util.ResourceLocation;
public class RecordKimigayo extends ItemRecord {
	public RecordKimigayo() {
		super("kimigayo");
		setMaxStackSize(1);
		setUnlocalizedName("record_kimigayo");
		setTextureName("japanpack:record_kimigayo");
	}
	@Override
	public ResourceLocation getRecordResource(String name) {
		return new ResourceLocation(Main.modid + ":" + name);
	}
}
