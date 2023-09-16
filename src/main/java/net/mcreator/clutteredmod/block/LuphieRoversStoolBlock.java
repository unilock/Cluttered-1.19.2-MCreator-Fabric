
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

public class LuphieRoversStoolBlock extends Block {
	public static Settings PROPERTIES = Settings.create().sounds(BlockSoundGroup.WOOD).strength(1f, 10f).nonOpaque().solidBlock((bs, br, bp) -> false);
	public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

	public LuphieRoversStoolBlock() {
		super(PROPERTIES);
		this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
	}

	@Override
	public void appendTooltip(ItemStack itemstack, BlockView world, List<Text> list, TooltipContext flag) {
		super.appendTooltip(itemstack, world, list, flag);
		list.add(Text.literal("ACPC"));
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
			default -> VoxelShapes.union(createCuboidShape(3, 7, 3, 13, 9, 13), createCuboidShape(10, 0, 10, 12, 7, 12), createCuboidShape(4, 0, 10, 6, 7, 12), createCuboidShape(4, 0, 4, 6, 7, 6), createCuboidShape(10, 0, 4, 12, 7, 6), createCuboidShape(6, 2, 4.5, 10, 3, 5.5), createCuboidShape(6, 2, 10.5, 10, 3, 11.5), createCuboidShape(10.5, 2, 6, 11.5, 3, 10),
					createCuboidShape(4.5, 2, 6, 5.5, 3, 10));
			case NORTH -> VoxelShapes.union(createCuboidShape(3, 7, 3, 13, 9, 13), createCuboidShape(4, 0, 4, 6, 7, 6), createCuboidShape(10, 0, 4, 12, 7, 6), createCuboidShape(10, 0, 10, 12, 7, 12), createCuboidShape(4, 0, 10, 6, 7, 12), createCuboidShape(6, 2, 10.5, 10, 3, 11.5), createCuboidShape(6, 2, 4.5, 10, 3, 5.5), createCuboidShape(4.5, 2, 6, 5.5, 3, 10),
					createCuboidShape(10.5, 2, 6, 11.5, 3, 10));
			case EAST -> VoxelShapes.union(createCuboidShape(3, 7, 3, 13, 9, 13), createCuboidShape(10, 0, 4, 12, 7, 6), createCuboidShape(10, 0, 10, 12, 7, 12), createCuboidShape(4, 0, 10, 6, 7, 12), createCuboidShape(4, 0, 4, 6, 7, 6), createCuboidShape(4.5, 2, 6, 5.5, 3, 10), createCuboidShape(10.5, 2, 6, 11.5, 3, 10), createCuboidShape(6, 2, 4.5, 10, 3, 5.5),
					createCuboidShape(6, 2, 10.5, 10, 3, 11.5));
			case WEST -> VoxelShapes.union(createCuboidShape(3, 7, 3, 13, 9, 13), createCuboidShape(4, 0, 10, 6, 7, 12), createCuboidShape(4, 0, 4, 6, 7, 6), createCuboidShape(10, 0, 4, 12, 7, 6), createCuboidShape(10, 0, 10, 12, 7, 12), createCuboidShape(10.5, 2, 6, 11.5, 3, 10), createCuboidShape(4.5, 2, 6, 5.5, 3, 10), createCuboidShape(6, 2, 10.5, 10, 3, 11.5),
					createCuboidShape(6, 2, 4.5, 10, 3, 5.5));
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
		BlockRenderLayerMap.INSTANCE.putBlock(LuphieclutteredmodModBlocks.LUPHIE_ROVERS_STOOL, RenderLayer.getCutout());
	}
}
