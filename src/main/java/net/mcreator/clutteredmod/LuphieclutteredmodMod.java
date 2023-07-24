/*
 *	MCreator note:
 *
 *	If you lock base mod element files, you can edit this file and the proxy files
 *	and they won't get overwritten. If you change your mod package or modid, you
 *	need to apply these changes to this file MANUALLY.
 *
 *
 *	If you do not lock base mod element files in Workspace settings, this file
 *	will be REGENERATED on each build.
 *
 */
package net.mcreator.clutteredmod;

import net.fabricmc.api.ModInitializer;
import net.mcreator.clutteredmod.init.*;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LuphieclutteredmodMod implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MODID = "luphieclutteredmod";

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing LuphieclutteredmodMod");

		LuphieclutteredmodModBlocks.load();
		LuphieclutteredmodModBlockEntities.load();
		LuphieclutteredmodModItems.load();
		LuphieclutteredmodModMenus.load();
		LuphieclutteredmodModPaintings.load();
		LuphieclutteredmodModSounds.load();

		LuphieclutteredmodModTabs.load();
	}

	public static Identifier id(String path) {
		return new Identifier(MODID, path);
	}
}
