
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

import java.util.Collections;
import java.util.List;

public class LuphiebunnyPlushieBlock extends Block {
	public static Settings PROPERTIES = Settings.create().sounds(BlockSoundGroup.WOOL).strength(1f, 10f).nonOpaque().solidBlock((bs, br, bp) -> false);
	public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

	public LuphiebunnyPlushieBlock() {
		super(PROPERTIES);
		this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
	}

	@Override
	public void appendTooltip(ItemStack itemstack, BlockView world, List<Text> list, TooltipContext flag) {
		super.appendTooltip(itemstack, world, list, flag);
		list.add(Text.literal("Starbound"));
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
			default -> VoxelShapes.union(createCuboidShape(5, 0, 4, 11, 7, 9), createCuboidShape(7, 0, 2, 9, 3, 4), createCuboidShape(5, 7, 5, 11, 11, 9), createCuboidShape(5, 0, 9, 7, 2, 11), createCuboidShape(6, 15, 7, 7, 16, 8), createCuboidShape(9, 0, 9, 11, 2, 11), createCuboidShape(5, 11, 7, 7, 15, 8), createCuboidShape(9, 11, 7, 11, 15, 8),
					createCuboidShape(6, 7, 9, 10, 9, 10), createCuboidShape(9, 15, 7, 10, 16, 8));
			case NORTH -> VoxelShapes.union(createCuboidShape(5, 0, 7, 11, 7, 12), createCuboidShape(7, 0, 12, 9, 3, 14), createCuboidShape(5, 7, 7, 11, 11, 11), createCuboidShape(9, 0, 5, 11, 2, 7), createCuboidShape(9, 15, 8, 10, 16, 9), createCuboidShape(5, 0, 5, 7, 2, 7), createCuboidShape(9, 11, 8, 11, 15, 9), createCuboidShape(5, 11, 8, 7, 15, 9),
					createCuboidShape(6, 7, 6, 10, 9, 7), createCuboidShape(6, 15, 8, 7, 16, 9));
			case EAST -> VoxelShapes.union(createCuboidShape(4, 0, 5, 9, 7, 11), createCuboidShape(2, 0, 7, 4, 3, 9), createCuboidShape(5, 7, 5, 9, 11, 11), createCuboidShape(9, 0, 9, 11, 2, 11), createCuboidShape(7, 15, 9, 8, 16, 10), createCuboidShape(9, 0, 5, 11, 2, 7), createCuboidShape(7, 11, 9, 8, 15, 11), createCuboidShape(7, 11, 5, 8, 15, 7),
					createCuboidShape(9, 7, 6, 10, 9, 10), createCuboidShape(7, 15, 6, 8, 16, 7));
			case WEST -> VoxelShapes.union(createCuboidShape(7, 0, 5, 12, 7, 11), createCuboidShape(12, 0, 7, 14, 3, 9), createCuboidShape(7, 7, 5, 11, 11, 11), createCuboidShape(5, 0, 5, 7, 2, 7), createCuboidShape(8, 15, 6, 9, 16, 7), createCuboidShape(5, 0, 9, 7, 2, 11), createCuboidShape(8, 11, 5, 9, 15, 7), createCuboidShape(8, 11, 9, 9, 15, 11),
					createCuboidShape(6, 7, 6, 7, 9, 10), createCuboidShape(8, 15, 9, 9, 16, 10));
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
		BlockRenderLayerMap.INSTANCE.putBlock(LuphieclutteredmodModBlocks.LUPHIEBUNNY_PLUSHIE, RenderLayer.getSolid());
	}
}
