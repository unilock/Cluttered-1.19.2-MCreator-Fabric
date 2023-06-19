
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.clutteredmod.init;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.clutteredmod.world.inventory.LuphieSmallStorageGuiMenu;
import net.mcreator.clutteredmod.world.inventory.LuphieLargeStorageGuiMenu;
import net.mcreator.clutteredmod.client.gui.LuphieSmallStorageGuiScreen;
import net.mcreator.clutteredmod.client.gui.LuphieLargeStorageGuiScreen;
import net.mcreator.clutteredmod.LuphieclutteredmodMod;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;

public class LuphieclutteredmodModMenus {
	public static MenuType<LuphieSmallStorageGuiMenu> LUPHIE_SMALL_STORAGE_GUI;
	public static MenuType<LuphieLargeStorageGuiMenu> LUPHIE_LARGE_STORAGE_GUI;

	public static void load() {
		LUPHIE_SMALL_STORAGE_GUI = ScreenHandlerRegistry
				.registerExtended(new ResourceLocation(LuphieclutteredmodMod.MODID, "luphie_small_storage_gui"), LuphieSmallStorageGuiMenu::new);
		LuphieSmallStorageGuiScreen.screenInit();
		LUPHIE_LARGE_STORAGE_GUI = ScreenHandlerRegistry
				.registerExtended(new ResourceLocation(LuphieclutteredmodMod.MODID, "luphie_large_storage_gui"), LuphieLargeStorageGuiMenu::new);
		LuphieLargeStorageGuiScreen.screenInit();
	}
}
