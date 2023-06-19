
package net.mcreator.clutteredmod.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.client.renderer.RenderType;

import net.mcreator.clutteredmod.init.LuphieclutteredmodModBlocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

import java.util.List;
import java.util.Collections;

public class LuphieBeeLampBlock extends Block {
	public static BlockBehaviour.Properties PROPERTIES = FabricBlockSettings.of(Material.DECORATION).sound(SoundType.HONEY_BLOCK).strength(1f, 10f)
			.lightLevel(s -> 14).noOcclusion().hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true)
			.isRedstoneConductor((bs, br, bp) -> false);
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public LuphieBeeLampBlock() {
		super(PROPERTIES);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		Vec3 offset = state.getOffset(world, pos);
		return (switch (state.getValue(FACING)) {
			default -> Shapes.or(box(4.5, 1, 3, 11.5, 8, 13), box(5.5, 0, 8, 6.5, 1, 9), box(9.5, 0, 8, 10.5, 1, 9), box(6.5, 0, 10, 7.5, 1, 11),
					box(9.5, 0, 6, 10.5, 1, 7), box(8.5, 0, 10, 9.5, 1, 11), box(5.5, 0, 6, 6.5, 1, 7));
			case NORTH -> Shapes.or(box(4.5, 1, 3, 11.5, 8, 13), box(9.5, 0, 7, 10.5, 1, 8), box(5.5, 0, 7, 6.5, 1, 8), box(8.5, 0, 5, 9.5, 1, 6),
					box(5.5, 0, 9, 6.5, 1, 10), box(6.5, 0, 5, 7.5, 1, 6), box(9.5, 0, 9, 10.5, 1, 10));
			case EAST -> Shapes.or(box(3, 1, 4.5, 13, 8, 11.5), box(8, 0, 9.5, 9, 1, 10.5), box(8, 0, 5.5, 9, 1, 6.5), box(10, 0, 8.5, 11, 1, 9.5),
					box(6, 0, 5.5, 7, 1, 6.5), box(10, 0, 6.5, 11, 1, 7.5), box(6, 0, 9.5, 7, 1, 10.5));
			case WEST -> Shapes.or(box(3, 1, 4.5, 13, 8, 11.5), box(7, 0, 5.5, 8, 1, 6.5), box(7, 0, 9.5, 8, 1, 10.5), box(5, 0, 6.5, 6, 1, 7.5),
					box(9, 0, 9.5, 10, 1, 10.5), box(5, 0, 8.5, 6, 1, 9.5), box(9, 0, 5.5, 10, 1, 6.5));
		}).move(offset.x, offset.y, offset.z);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
		List<ItemStack> dropsOriginal = super.getDrops(state, builder);
		if (!dropsOriginal.isEmpty())
			return dropsOriginal;
		return Collections.singletonList(new ItemStack(this, 1));
	}

	@Environment(EnvType.CLIENT)
	public static void clientInit() {
		BlockRenderLayerMap.INSTANCE.putBlock(LuphieclutteredmodModBlocks.LUPHIE_BEE_LAMP, RenderType.cutout());
	}
}
