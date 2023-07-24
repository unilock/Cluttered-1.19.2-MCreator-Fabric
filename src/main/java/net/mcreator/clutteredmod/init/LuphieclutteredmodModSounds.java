
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.clutteredmod.init;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class LuphieclutteredmodModSounds {
	public static SoundEvent STARBOUNDMUSICBOX = SoundEvent.of(new Identifier("luphieclutteredmod", "starboundmusicbox"));

	public static void load() {
		Registry.register(Registries.SOUND_EVENT, new Identifier("luphieclutteredmod", "starboundmusicbox"), STARBOUNDMUSICBOX);
	}
}
