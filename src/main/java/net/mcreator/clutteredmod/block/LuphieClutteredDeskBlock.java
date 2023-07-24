
package net.mcreator.clutteredmod.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.mcreator.clutteredmod.init.LuphieclutteredmodModBlocks;
import net.minecraft.block.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.List;

public class LuphieClutteredDeskBlock extends Block {
	public static AbstractBlock.Settings PROPERTIES = FabricBlockSettings.create().sounds(BlockSoundGroup.WOOD).strength(1f, 10f)
			.nonOpaque().solidBlock((bs, br, bp) -> false);
	public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

	public LuphieClutteredDeskBlock() {
		super(PROPERTIES);
		this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
	}

	@Override
	public boolean isTransparent(BlockState state, BlockView reader, BlockPos pos) {
		return true;
	}

	@Override
	public int getOpacity(BlockState state, BlockView worldIn, BlockPos pos) {
		return 0;
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		Vec3d offset = state.getModelOffset(world, pos);
		return (switch (state.get(FACING)) {
			default -> VoxelShapes.union(createCuboidShape(5, -6, 2, 26, -1, 13), createCuboidShape(-2, -4, 14, 1, -2, 15), createCuboidShape(15, -4, 14, 18, -2, 15), createCuboidShape(-11, -1, 0, 27, 0, 16),
					createCuboidShape(-10, -6, 2, -9, -1, 13), createCuboidShape(-9, -6, 0, 25, -1, 14), createCuboidShape(25, -13, 13, 27, -1, 15), createCuboidShape(25, -13, 0, 27, -1, 2),
					createCuboidShape(25, -16, 0, 27, -9, 2), createCuboidShape(-11, -13, 13, -9, -1, 15), createCuboidShape(25, -16, 13, 27, -9, 15), createCuboidShape(-11, -16, 13, -9, -9, 15),
					createCuboidShape(-11, -13, 0, -9, -1, 2), createCuboidShape(-11, -16, 0, -9, -9, 2));
			case NORTH -> VoxelShapes.union(createCuboidShape(-10, -6, 3, 11, -1, 14), createCuboidShape(15, -4, 1, 18, -2, 2), createCuboidShape(-2, -4, 1, 1, -2, 2), createCuboidShape(-11, -1, 0, 27, 0, 16),
					createCuboidShape(25, -6, 3, 26, -1, 14), createCuboidShape(-9, -6, 2, 25, -1, 16), createCuboidShape(-11, -13, 1, -9, -1, 3), createCuboidShape(-11, -13, 14, -9, -1, 16),
					createCuboidShape(-11, -16, 14, -9, -9, 16), createCuboidShape(25, -13, 1, 27, -1, 3), createCuboidShape(-11, -16, 1, -9, -9, 3), createCuboidShape(25, -16, 1, 27, -9, 3),
					createCuboidShape(25, -13, 14, 27, -1, 16), createCuboidShape(25, -16, 14, 27, -9, 16));
			case EAST -> VoxelShapes.union(createCuboidShape(2, -6, -10, 13, -1, 11), createCuboidShape(14, -4, 15, 15, -2, 18), createCuboidShape(14, -4, -2, 15, -2, 1), createCuboidShape(0, -1, -11, 16, 0, 27),
					createCuboidShape(2, -6, 25, 13, -1, 26), createCuboidShape(0, -6, -9, 14, -1, 25), createCuboidShape(13, -13, -11, 15, -1, -9), createCuboidShape(0, -13, -11, 2, -1, -9),
					createCuboidShape(0, -16, -11, 2, -9, -9), createCuboidShape(13, -13, 25, 15, -1, 27), createCuboidShape(13, -16, -11, 15, -9, -9), createCuboidShape(13, -16, 25, 15, -9, 27),
					createCuboidShape(0, -13, 25, 2, -1, 27), createCuboidShape(0, -16, 25, 2, -9, 27));
			case WEST -> VoxelShapes.union(createCuboidShape(3, -6, 5, 14, -1, 26), createCuboidShape(1, -4, -2, 2, -2, 1), createCuboidShape(1, -4, 15, 2, -2, 18), createCuboidShape(0, -1, -11, 16, 0, 27),
					createCuboidShape(3, -6, -10, 14, -1, -9), createCuboidShape(2, -6, -9, 16, -1, 25), createCuboidShape(1, -13, 25, 3, -1, 27), createCuboidShape(14, -13, 25, 16, -1, 27),
					createCuboidShape(14, -16, 25, 16, -9, 27), createCuboidShape(1, -13, -11, 3, -1, -9), createCuboidShape(1, -16, 25, 3, -9, 27), createCuboidShape(1, -16, -11, 3, -9, -9),
					createCuboidShape(14, -13, -11, 16, -1, -9), createCuboidShape(14, -16, -11, 16, -9, -9));
		}).offset(offset.x, offset.y, offset.z);
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext context) {
		return this.getDefaultState().with(FACING, context.getHorizontalPlayerFacing().getOpposite());
	}

	public BlockState rotate(BlockState state, BlockRotation rot) {
		return state.with(FACING, rot.rotate(state.get(FACING)));
	}

	public BlockState mirror(BlockState state, BlockMirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.get(FACING)));
	}

	@Override
	public List<ItemStack> getDroppedStacks(BlockState state, LootContextParameterSet.Builder builder) {
		List<ItemStack> dropsOriginal = super.getDroppedStacks(state, builder);
		if (!dropsOriginal.isEmpty())
			return dropsOriginal;
		return Collections.singletonList(new ItemStack(this, 1));
	}

	@Override
	public ActionResult onUse(BlockState blockstate, World world, BlockPos pos, PlayerEntity entity, Hand hand, BlockHitResult hit) {
		super.onUse(blockstate, world, pos, entity, hand, hit);
		return ActionResult.SUCCESS;
	}

	@Environment(EnvType.CLIENT)
	public static void clientInit() {
		BlockRenderLayerMap.INSTANCE.putBlock(LuphieclutteredmodModBlocks.LUPHIE_CLUTTERED_DESK, RenderLayer.getCutout());
	}
}
