
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.clutteredmod.init;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;

import net.mcreator.clutteredmod.block.entity.LuphieSafeBlockEntity;
import net.mcreator.clutteredmod.block.entity.LuphieRetroPinkFridgeBlockEntity;
import net.mcreator.clutteredmod.block.entity.LuphieMushroomChestBlockEntity;
import net.mcreator.clutteredmod.block.entity.LuphieBriefcaseBlockEntity;
import net.mcreator.clutteredmod.LuphieclutteredmodMod;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;

public class LuphieclutteredmodModBlockEntities {
	public static BlockEntityType<?> LUPHIE_BRIEFCASE;
	public static BlockEntityType<?> LUPHIE_MUSHROOM_CHEST;
	public static BlockEntityType<?> LUPHIE_SAFE;
	public static BlockEntityType<?> LUPHIE_RETRO_PINK_FRIDGE;

	public static void load() {
		LUPHIE_BRIEFCASE = Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(LuphieclutteredmodMod.MODID, "luphie_briefcase"),
				FabricBlockEntityTypeBuilder.create(LuphieBriefcaseBlockEntity::new, LuphieclutteredmodModBlocks.LUPHIE_BRIEFCASE).build(null));
		LUPHIE_MUSHROOM_CHEST = Registry.register(Registry.BLOCK_ENTITY_TYPE,
				new ResourceLocation(LuphieclutteredmodMod.MODID, "luphie_mushroom_chest"), FabricBlockEntityTypeBuilder
						.create(LuphieMushroomChestBlockEntity::new, LuphieclutteredmodModBlocks.LUPHIE_MUSHROOM_CHEST).build(null));
		LUPHIE_SAFE = Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(LuphieclutteredmodMod.MODID, "luphie_safe"),
				FabricBlockEntityTypeBuilder.create(LuphieSafeBlockEntity::new, LuphieclutteredmodModBlocks.LUPHIE_SAFE).build(null));
		LUPHIE_RETRO_PINK_FRIDGE = Registry.register(Registry.BLOCK_ENTITY_TYPE,
				new ResourceLocation(LuphieclutteredmodMod.MODID, "luphie_retro_pink_fridge"), FabricBlockEntityTypeBuilder
						.create(LuphieRetroPinkFridgeBlockEntity::new, LuphieclutteredmodModBlocks.LUPHIE_RETRO_PINK_FRIDGE).build(null));
	}
}
