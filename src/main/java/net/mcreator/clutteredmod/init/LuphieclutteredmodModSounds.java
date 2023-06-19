
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.clutteredmod.init;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;

public class LuphieclutteredmodModSounds {
	public static SoundEvent STARBOUNDMUSICBOX = new SoundEvent(new ResourceLocation("luphieclutteredmod", "starboundmusicbox"));

	public static void load() {
		Registry.register(Registry.SOUND_EVENT, new ResourceLocation("luphieclutteredmod", "starboundmusicbox"), STARBOUNDMUSICBOX);
	}
}
