
package net.mcreator.clutteredmod.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.mcreator.clutteredmod.init.LuphieclutteredmodModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.text.Text;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

import java.util.Collections;
import java.util.List;

public class LuphieUnlivingChairBlock extends Block {
	public static Settings PROPERTIES = Settings.create().sounds(BlockSoundGroup.WOOD).strength(1f, 10f).nonOpaque().solidBlock((bs, br, bp) -> false);
	public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

	public LuphieUnlivingChairBlock() {
		super(PROPERTIES);
		this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
	}

	@Override
	public void appendTooltip(ItemStack itemstack, BlockView world, List<Text> list, TooltipContext flag) {
		super.appendTooltip(itemstack, world, list, flag);
		list.add(Text.literal("Sims 4"));
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
			default -> VoxelShapes.combineAndSimplify(VoxelShapes.union(createCuboidShape(0, 0, 0, 16, 9, 16), createCuboidShape(0, 9, 4, 16, 14, 16), createCuboidShape(0, 9, 0, 16, 27, 4)), createCuboidShape(3, 9, 4, 13, 14, 16), BooleanBiFunction.ONLY_FIRST);
			case NORTH -> VoxelShapes.combineAndSimplify(VoxelShapes.union(createCuboidShape(0, 0, 0, 16, 9, 16), createCuboidShape(0, 9, 0, 16, 14, 12), createCuboidShape(0, 9, 12, 16, 27, 16)), createCuboidShape(3, 9, 0, 13, 14, 12), BooleanBiFunction.ONLY_FIRST);
			case EAST -> VoxelShapes.combineAndSimplify(VoxelShapes.union(createCuboidShape(0, 0, 0, 16, 9, 16), createCuboidShape(4, 9, 0, 16, 14, 16), createCuboidShape(0, 9, 0, 4, 27, 16)), createCuboidShape(4, 9, 3, 16, 14, 13), BooleanBiFunction.ONLY_FIRST);
			case WEST -> VoxelShapes.combineAndSimplify(VoxelShapes.union(createCuboidShape(0, 0, 0, 16, 9, 16), createCuboidShape(0, 9, 0, 12, 14, 16), createCuboidShape(12, 9, 0, 16, 27, 16)), createCuboidShape(0, 9, 3, 12, 14, 13), BooleanBiFunction.ONLY_FIRST);
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
		BlockRenderLayerMap.INSTANCE.putBlock(LuphieclutteredmodModBlocks.LUPHIE_UNLIVING_CHAIR, RenderLayer.getSolid());
	}
}
