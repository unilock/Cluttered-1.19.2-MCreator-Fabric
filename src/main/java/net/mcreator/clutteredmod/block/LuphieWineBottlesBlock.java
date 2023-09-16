
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

public class LuphieWineBottlesBlock extends Block {
	public static Settings PROPERTIES = Settings.create().sounds(BlockSoundGroup.GLASS).strength(1f, 10f).nonOpaque().solidBlock((bs, br, bp) -> false);
	public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

	public LuphieWineBottlesBlock() {
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
			default -> VoxelShapes.union(createCuboidShape(1, 0, 1, 5, 9, 5), createCuboidShape(2, 9, 2, 4, 14, 4), createCuboidShape(10, 0, 2, 14, 7, 6), createCuboidShape(11, 7, 3, 13, 12, 5), createCuboidShape(5, 0, 6, 8, 7, 9), createCuboidShape(5.5, 7, 6.5, 7.5, 10, 8.5));
			case NORTH -> VoxelShapes.union(createCuboidShape(11, 0, 11, 15, 9, 15), createCuboidShape(12, 9, 12, 14, 14, 14), createCuboidShape(2, 0, 10, 6, 7, 14), createCuboidShape(3, 7, 11, 5, 12, 13), createCuboidShape(8, 0, 7, 11, 7, 10), createCuboidShape(8.5, 7, 7.5, 10.5, 10, 9.5));
			case EAST -> VoxelShapes.union(createCuboidShape(1, 0, 11, 5, 9, 15), createCuboidShape(2, 9, 12, 4, 14, 14), createCuboidShape(2, 0, 2, 6, 7, 6), createCuboidShape(3, 7, 3, 5, 12, 5), createCuboidShape(6, 0, 8, 9, 7, 11), createCuboidShape(6.5, 7, 8.5, 8.5, 10, 10.5));
			case WEST -> VoxelShapes.union(createCuboidShape(11, 0, 1, 15, 9, 5), createCuboidShape(12, 9, 2, 14, 14, 4), createCuboidShape(10, 0, 10, 14, 7, 14), createCuboidShape(11, 7, 11, 13, 12, 13), createCuboidShape(7, 0, 5, 10, 7, 8), createCuboidShape(7.5, 7, 5.5, 9.5, 10, 7.5));
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
		BlockRenderLayerMap.INSTANCE.putBlock(LuphieclutteredmodModBlocks.LUPHIE_WINE_BOTTLES, RenderLayer.getSolid());
	}
}
