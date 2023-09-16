
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

public class PinkBicycleBlock extends Block {
	public static Settings PROPERTIES = Settings.create().sounds(BlockSoundGroup.METAL).strength(1f, 10f).nonOpaque().solidBlock((bs, br, bp) -> false);
	public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

	public PinkBicycleBlock() {
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
			default -> VoxelShapes.union(createCuboidShape(0, 0, 4, 16, 16, 12), createCuboidShape(16, 0, 4, 32, 16, 12), createCuboidShape(16, 16, 4, 32, 22, 12), createCuboidShape(0, 16, 4, 16, 22, 12));
			case NORTH -> VoxelShapes.union(createCuboidShape(0, 0, 4, 16, 16, 12), createCuboidShape(-16, 0, 4, 0, 16, 12), createCuboidShape(-16, 16, 4, 0, 22, 12), createCuboidShape(0, 16, 4, 16, 22, 12));
			case EAST -> VoxelShapes.union(createCuboidShape(4, 0, 0, 12, 16, 16), createCuboidShape(4, 0, -16, 12, 16, 0), createCuboidShape(4, 16, -16, 12, 22, 0), createCuboidShape(4, 16, 0, 12, 22, 16));
			case WEST -> VoxelShapes.union(createCuboidShape(4, 0, 0, 12, 16, 16), createCuboidShape(4, 0, 16, 12, 16, 32), createCuboidShape(4, 16, 16, 12, 22, 32), createCuboidShape(4, 16, 0, 12, 22, 16));
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
		BlockRenderLayerMap.INSTANCE.putBlock(LuphieclutteredmodModBlocks.PINK_BICYCLE, RenderLayer.getCutout());
	}
}