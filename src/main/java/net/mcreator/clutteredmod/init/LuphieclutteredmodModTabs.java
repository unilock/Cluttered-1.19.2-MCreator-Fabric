
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.clutteredmod.init;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.resources.ResourceLocation;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;

public class LuphieclutteredmodModTabs {
	public static CreativeModeTab TAB_CLUTTER_MOD;

	public static void load() {
		TAB_CLUTTER_MOD = FabricItemGroupBuilder.create(new ResourceLocation("luphieclutteredmod", "clutter_mod"))
				.icon(() -> new ItemStack(LuphieclutteredmodModBlocks.LUPHIE_PURPLE_BLACK_CAT_BOOKSHELF)).build();
	}
}
