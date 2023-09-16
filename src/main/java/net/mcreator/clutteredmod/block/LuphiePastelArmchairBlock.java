
package net.mcreator.clutteredmod.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.mcreator.clutteredmod.init.LuphieclutteredmodModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

import java.util.Collections;
import java.util.List;

public class LuphiePastelArmchairBlock extends Block {
	public static Settings PROPERTIES = Settings.create().sounds(BlockSoundGroup.WOOL).strength(1f, 10f).nonOpaque().solidBlock((bs, br, bp) -> false);
	public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

	public LuphiePastelArmchairBlock() {
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
			default -> VoxelShapes.union(createCuboidShape(-14, 10, 0, 14, 23, 4), createCuboidShape(8, 10, 0, 12, 17, 16), createCuboidShape(-12, 10, 0, -8, 17, 16), createCuboidShape(-14, 12, 0, -12, 17, 16), createCuboidShape(-12, 0, 0, 12, 10, 16), createCuboidShape(12, 12, 0, 14, 17, 16));
			case NORTH -> VoxelShapes.union(createCuboidShape(2, 10, 12, 30, 23, 16), createCuboidShape(4, 10, 0, 8, 17, 16), createCuboidShape(24, 10, 0, 28, 17, 16), createCuboidShape(28, 12, 0, 30, 17, 16), createCuboidShape(4, 0, 0, 28, 10, 16), createCuboidShape(2, 12, 0, 4, 17, 16));
			case EAST -> VoxelShapes.union(createCuboidShape(0, 10, 2, 4, 23, 30), createCuboidShape(0, 10, 4, 16, 17, 8), createCuboidShape(0, 10, 24, 16, 17, 28), createCuboidShape(0, 12, 28, 16, 17, 30), createCuboidShape(0, 0, 4, 16, 10, 28), createCuboidShape(0, 12, 2, 16, 17, 4));
			case WEST -> VoxelShapes.union(createCuboidShape(12, 10, -14, 16, 23, 14), createCuboidShape(0, 10, 8, 16, 17, 12), createCuboidShape(0, 10, -12, 16, 17, -8), createCuboidShape(0, 12, -14, 16, 17, -12), createCuboidShape(0, 0, -12, 16, 10, 12), createCuboidShape(0, 12, 12, 16, 17, 14));
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

	@Environment(EnvType.CLIENT)
	public static void clientInit() {
		BlockRenderLayerMap.INSTANCE.putBlock(LuphieclutteredmodModBlocks.LUPHIE_PASTEL_ARMCHAIR, RenderLayer.getSolid());
	}
}
