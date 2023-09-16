
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.clutteredmod.init;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.mcreator.clutteredmod.LuphieclutteredmodMod;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class LuphieclutteredmodModTabs {
	public static RegistryKey<ItemGroup> TAB_CLUTTER_MOD = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(LuphieclutteredmodMod.MODID, "clutter_mod"));

	public static void load() {
		Registry.register(Registries.ITEM_GROUP, TAB_CLUTTER_MOD,
				FabricItemGroup.builder().displayName(Text.translatable("item_group." + LuphieclutteredmodMod.MODID + ".clutter_mod")).icon(() -> new ItemStack(LuphieclutteredmodModBlocks.LUPHIE_PURPLE_BLACK_CAT_BOOKSHELF)).build());
	}
}
