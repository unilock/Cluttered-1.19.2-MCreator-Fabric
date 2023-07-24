
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.clutteredmod.init;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.mcreator.clutteredmod.LuphieclutteredmodMod;
import net.mcreator.clutteredmod.world.inventory.LuphieLargeStorageGuiMenu;
import net.mcreator.clutteredmod.world.inventory.LuphieSmallStorageGuiMenu;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class LuphieclutteredmodModMenus {
	public static ScreenHandlerType<LuphieSmallStorageGuiMenu> LUPHIE_SMALL_STORAGE_GUI;
	public static ScreenHandlerType<LuphieLargeStorageGuiMenu> LUPHIE_LARGE_STORAGE_GUI;

	public static void load() {
		LUPHIE_SMALL_STORAGE_GUI = ScreenHandlerRegistry
				.registerExtended(new Identifier(LuphieclutteredmodMod.MODID, "luphie_small_storage_gui"), LuphieSmallStorageGuiMenu::new);
	
		LUPHIE_LARGE_STORAGE_GUI = ScreenHandlerRegistry
				.registerExtended(new Identifier(LuphieclutteredmodMod.MODID, "luphie_large_storage_gui"), LuphieLargeStorageGuiMenu::new);
	
	}
}
