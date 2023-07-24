
package net.mcreator.clutteredmod.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.mcreator.clutteredmod.init.LuphieclutteredmodModBlocks;
import net.minecraft.block.*;
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

public class LuphieHoneycombLampBlock extends Block {
	public static AbstractBlock.Settings PROPERTIES = FabricBlockSettings.create().sounds(BlockSoundGroup.WOOL).strength(1f, 10f)
			.luminance(s -> 15).nonOpaque().solidBlock((bs, br, bp) -> false);
	public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

	public LuphieHoneycombLampBlock() {
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
			default -> VoxelShapes.union(createCuboidShape(7, 1, 6, 9, 9, 8), createCuboidShape(4, 0, 3, 12, 1, 11), createCuboidShape(2, 9, 1, 14, 19, 13), createCuboidShape(3.5, 5, 5.5, 5.5, 7, 8.5));
			case NORTH -> VoxelShapes.union(createCuboidShape(7, 1, 8, 9, 9, 10), createCuboidShape(4, 0, 5, 12, 1, 13), createCuboidShape(2, 9, 3, 14, 19, 15), createCuboidShape(10.5, 5, 7.5, 12.5, 7, 10.5));
			case EAST -> VoxelShapes.union(createCuboidShape(6, 1, 7, 8, 9, 9), createCuboidShape(3, 0, 4, 11, 1, 12), createCuboidShape(1, 9, 2, 13, 19, 14), createCuboidShape(5.5, 5, 10.5, 8.5, 7, 12.5));
			case WEST -> VoxelShapes.union(createCuboidShape(8, 1, 7, 10, 9, 9), createCuboidShape(5, 0, 4, 13, 1, 12), createCuboidShape(3, 9, 2, 15, 19, 14), createCuboidShape(7.5, 5, 3.5, 10.5, 7, 5.5));
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
		BlockRenderLayerMap.INSTANCE.putBlock(LuphieclutteredmodModBlocks.LUPHIE_HONEYCOMB_LAMP, RenderLayer.getSolid());
	}
}
