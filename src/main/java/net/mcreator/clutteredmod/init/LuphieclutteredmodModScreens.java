
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.clutteredmod.init;

import net.mcreator.clutteredmod.client.gui.LuphieSmallStorageGuiScreen;
import net.mcreator.clutteredmod.client.gui.LuphieLargeStorageGuiScreen;

import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

public class LuphieclutteredmodModScreens {
	public static void load() {
		ScreenRegistry.register(LuphieclutteredmodModMenus.LUPHIE_SMALL_STORAGE_GUI, LuphieSmallStorageGuiScreen::new);
		ScreenRegistry.register(LuphieclutteredmodModMenus.LUPHIE_LARGE_STORAGE_GUI, LuphieLargeStorageGuiScreen::new);
	}
}
