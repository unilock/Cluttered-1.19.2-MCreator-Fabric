
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.clutteredmod.init;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.mcreator.clutteredmod.LuphieclutteredmodMod;
import net.mcreator.clutteredmod.block.entity.LuphieBriefcaseBlockEntity;
import net.mcreator.clutteredmod.block.entity.LuphieMushroomChestBlockEntity;
import net.mcreator.clutteredmod.block.entity.LuphieRetroPinkFridgeBlockEntity;
import net.mcreator.clutteredmod.block.entity.LuphieSafeBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class LuphieclutteredmodModBlockEntities {
	public static BlockEntityType<?> LUPHIE_BRIEFCASE;
	public static BlockEntityType<?> LUPHIE_MUSHROOM_CHEST;
	public static BlockEntityType<?> LUPHIE_SAFE;
	public static BlockEntityType<?> LUPHIE_RETRO_PINK_FRIDGE;

	public static void load() {
		LUPHIE_BRIEFCASE = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(LuphieclutteredmodMod.MODID, "luphie_briefcase"),
				FabricBlockEntityTypeBuilder.create(LuphieBriefcaseBlockEntity::new, LuphieclutteredmodModBlocks.LUPHIE_BRIEFCASE).build(null));
		LUPHIE_MUSHROOM_CHEST = Registry.register(Registries.BLOCK_ENTITY_TYPE,
				new Identifier(LuphieclutteredmodMod.MODID, "luphie_mushroom_chest"), FabricBlockEntityTypeBuilder
						.create(LuphieMushroomChestBlockEntity::new, LuphieclutteredmodModBlocks.LUPHIE_MUSHROOM_CHEST).build(null));
		LUPHIE_SAFE = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(LuphieclutteredmodMod.MODID, "luphie_safe"),
				FabricBlockEntityTypeBuilder.create(LuphieSafeBlockEntity::new, LuphieclutteredmodModBlocks.LUPHIE_SAFE).build(null));
		LUPHIE_RETRO_PINK_FRIDGE = Registry.register(Registries.BLOCK_ENTITY_TYPE,
				new Identifier(LuphieclutteredmodMod.MODID, "luphie_retro_pink_fridge"), FabricBlockEntityTypeBuilder
						.create(LuphieRetroPinkFridgeBlockEntity::new, LuphieclutteredmodModBlocks.LUPHIE_RETRO_PINK_FRIDGE).build(null));
	}
}
