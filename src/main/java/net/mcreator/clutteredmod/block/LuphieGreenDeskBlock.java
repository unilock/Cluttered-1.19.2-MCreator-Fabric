
package net.mcreator.clutteredmod.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.BlockHitResult;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
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

public class LuphieGreenDeskBlock extends Block {
	public static BlockBehaviour.Properties PROPERTIES = FabricBlockSettings.of(Material.DECORATION).sound(SoundType.WOOD).strength(1f, 10f)
			.noOcclusion().isRedstoneConductor((bs, br, bp) -> false);
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public LuphieGreenDeskBlock() {
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
			default -> Shapes.or(box(5, -6, 2, 26, -1, 13), box(-2, -4, 14, 1, -2, 15), box(15, -4, 14, 18, -2, 15), box(-11, -1, 0, 27, 0, 16),
					box(-10, -6, 2, -9, -1, 13), box(-9, -6, 0, 25, -1, 14), box(25, -13, 13, 27, -1, 15), box(25, -13, 0, 27, -1, 2),
					box(25, -16, 0, 27, -9, 2), box(-11, -13, 13, -9, -1, 15), box(25, -16, 13, 27, -9, 15), box(-11, -16, 13, -9, -9, 15),
					box(-11, -13, 0, -9, -1, 2), box(-11, -16, 0, -9, -9, 2));
			case NORTH -> Shapes.or(box(-10, -6, 3, 11, -1, 14), box(15, -4, 1, 18, -2, 2), box(-2, -4, 1, 1, -2, 2), box(-11, -1, 0, 27, 0, 16),
					box(25, -6, 3, 26, -1, 14), box(-9, -6, 2, 25, -1, 16), box(-11, -13, 1, -9, -1, 3), box(-11, -13, 14, -9, -1, 16),
					box(-11, -16, 14, -9, -9, 16), box(25, -13, 1, 27, -1, 3), box(-11, -16, 1, -9, -9, 3), box(25, -16, 1, 27, -9, 3),
					box(25, -13, 14, 27, -1, 16), box(25, -16, 14, 27, -9, 16));
			case EAST -> Shapes.or(box(2, -6, -10, 13, -1, 11), box(14, -4, 15, 15, -2, 18), box(14, -4, -2, 15, -2, 1), box(0, -1, -11, 16, 0, 27),
					box(2, -6, 25, 13, -1, 26), box(0, -6, -9, 14, -1, 25), box(13, -13, -11, 15, -1, -9), box(0, -13, -11, 2, -1, -9),
					box(0, -16, -11, 2, -9, -9), box(13, -13, 25, 15, -1, 27), box(13, -16, -11, 15, -9, -9), box(13, -16, 25, 15, -9, 27),
					box(0, -13, 25, 2, -1, 27), box(0, -16, 25, 2, -9, 27));
			case WEST -> Shapes.or(box(3, -6, 5, 14, -1, 26), box(1, -4, -2, 2, -2, 1), box(1, -4, 15, 2, -2, 18), box(0, -1, -11, 16, 0, 27),
					box(3, -6, -10, 14, -1, -9), box(2, -6, -9, 16, -1, 25), box(1, -13, 25, 3, -1, 27), box(14, -13, 25, 16, -1, 27),
					box(14, -16, 25, 16, -9, 27), box(1, -13, -11, 3, -1, -9), box(1, -16, 25, 3, -9, 27), box(1, -16, -11, 3, -9, -9),
					box(14, -13, -11, 16, -1, -9), box(14, -16, -11, 16, -9, -9));
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

	@Override
	public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
		super.use(blockstate, world, pos, entity, hand, hit);
		return InteractionResult.SUCCESS;
	}

	@Environment(EnvType.CLIENT)
	public static void clientInit() {
		BlockRenderLayerMap.INSTANCE.putBlock(LuphieclutteredmodModBlocks.LUPHIE_GREEN_DESK, RenderType.solid());
	}
}
