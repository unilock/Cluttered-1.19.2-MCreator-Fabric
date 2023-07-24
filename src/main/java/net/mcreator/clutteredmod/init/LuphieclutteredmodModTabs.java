
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.clutteredmod.init;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.mcreator.clutteredmod.LuphieclutteredmodMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

import java.lang.reflect.Field;

public class LuphieclutteredmodModTabs {
	public static final ItemGroup TAB_CLUTTER_MOD = FabricItemGroup.builder()
			.icon(() -> new ItemStack(LuphieclutteredmodModBlocks.LUPHIE_PURPLE_BLACK_CAT_BOOKSHELF))
			.displayName(Text.literal("Cluttered"))
			.entries(((displayContext, entries) -> {
				for (Field field : LuphieclutteredmodModItems.class.getDeclaredFields()) {
					if (Item.class.isAssignableFrom(field.getType())) {
						try {
							entries.add((Item) field.get(null));
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
					}
				}
			}))
			.build();

	public static void load() {
		Registry.register(Registries.ITEM_GROUP, LuphieclutteredmodMod.id("cluttered"), TAB_CLUTTER_MOD);
	}
}
