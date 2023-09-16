
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

public class LuphieAngryBeeLampBlock extends Block {
	public static Settings PROPERTIES = Settings.create().sounds(BlockSoundGroup.HONEY).strength(1f, 10f).luminance(s -> 7).nonOpaque().postProcess((bs, br, bp) -> true).emissiveLighting((bs, br, bp) -> true)
			.solidBlock((bs, br, bp) -> false);
	public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

	public LuphieAngryBeeLampBlock() {
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
			default -> VoxelShapes.union(createCuboidShape(4.5, 1, 3, 11.5, 8, 13), createCuboidShape(5.5, 0, 8, 6.5, 1, 9), createCuboidShape(9.5, 0, 8, 10.5, 1, 9), createCuboidShape(6.5, 0, 10, 7.5, 1, 11), createCuboidShape(9.5, 0, 6, 10.5, 1, 7), createCuboidShape(8.5, 0, 10, 9.5, 1, 11), createCuboidShape(5.5, 0, 6, 6.5, 1, 7));
			case NORTH -> VoxelShapes.union(createCuboidShape(4.5, 1, 3, 11.5, 8, 13), createCuboidShape(9.5, 0, 7, 10.5, 1, 8), createCuboidShape(5.5, 0, 7, 6.5, 1, 8), createCuboidShape(8.5, 0, 5, 9.5, 1, 6), createCuboidShape(5.5, 0, 9, 6.5, 1, 10), createCuboidShape(6.5, 0, 5, 7.5, 1, 6), createCuboidShape(9.5, 0, 9, 10.5, 1, 10));
			case EAST -> VoxelShapes.union(createCuboidShape(3, 1, 4.5, 13, 8, 11.5), createCuboidShape(8, 0, 9.5, 9, 1, 10.5), createCuboidShape(8, 0, 5.5, 9, 1, 6.5), createCuboidShape(10, 0, 8.5, 11, 1, 9.5), createCuboidShape(6, 0, 5.5, 7, 1, 6.5), createCuboidShape(10, 0, 6.5, 11, 1, 7.5), createCuboidShape(6, 0, 9.5, 7, 1, 10.5));
			case WEST -> VoxelShapes.union(createCuboidShape(3, 1, 4.5, 13, 8, 11.5), createCuboidShape(7, 0, 5.5, 8, 1, 6.5), createCuboidShape(7, 0, 9.5, 8, 1, 10.5), createCuboidShape(5, 0, 6.5, 6, 1, 7.5), createCuboidShape(9, 0, 9.5, 10, 1, 10.5), createCuboidShape(5, 0, 8.5, 6, 1, 9.5), createCuboidShape(9, 0, 5.5, 10, 1, 6.5));
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
		BlockRenderLayerMap.INSTANCE.putBlock(LuphieclutteredmodModBlocks.LUPHIE_ANGRY_BEE_LAMP, RenderLayer.getCutout());
	}
}
