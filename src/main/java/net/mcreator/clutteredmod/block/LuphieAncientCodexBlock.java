
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

public class LuphieAncientCodexBlock extends Block {
	public static Settings PROPERTIES = Settings.create().sounds(BlockSoundGroup.WOOD).strength(1f, 10f).nonOpaque().solidBlock((bs, br, bp) -> false);
	public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

	public LuphieAncientCodexBlock() {
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
			default -> VoxelShapes.union(createCuboidShape(13, 0, 13, 15, 1, 15), createCuboidShape(1, 0, 13, 3, 1, 15), createCuboidShape(1, 1, 11, 3, 2, 15), createCuboidShape(3, 1, 13, 5, 2, 15), createCuboidShape(3, 1, 1, 5, 2, 3), createCuboidShape(11, 1, 1, 13, 2, 3), createCuboidShape(11, 1, 13, 13, 2, 15), createCuboidShape(13, 1, 11, 15, 2, 15),
					createCuboidShape(1, 2, 1, 15, 3, 15), createCuboidShape(2, 3, 2, 14, 4, 14), createCuboidShape(4, 4, 4, 12, 5, 12), createCuboidShape(7, 5, 7, 9, 20, 9), createCuboidShape(13, 0, 1, 15, 1, 3), createCuboidShape(1, 0, 1, 3, 1, 3), createCuboidShape(1, 1, 1, 3, 2, 5), createCuboidShape(13, 1, 1, 15, 2, 5), createCuboidShape(3, 17, 4, 13, 27, 14));
			case NORTH -> VoxelShapes.union(createCuboidShape(1, 0, 1, 3, 1, 3), createCuboidShape(13, 0, 1, 15, 1, 3), createCuboidShape(13, 1, 1, 15, 2, 5), createCuboidShape(11, 1, 1, 13, 2, 3), createCuboidShape(11, 1, 13, 13, 2, 15), createCuboidShape(3, 1, 13, 5, 2, 15), createCuboidShape(3, 1, 1, 5, 2, 3), createCuboidShape(1, 1, 1, 3, 2, 5),
					createCuboidShape(1, 2, 1, 15, 3, 15), createCuboidShape(2, 3, 2, 14, 4, 14), createCuboidShape(4, 4, 4, 12, 5, 12), createCuboidShape(7, 5, 7, 9, 20, 9), createCuboidShape(1, 0, 13, 3, 1, 15), createCuboidShape(13, 0, 13, 15, 1, 15), createCuboidShape(13, 1, 11, 15, 2, 15), createCuboidShape(1, 1, 11, 3, 2, 15), createCuboidShape(3, 17, 2, 13, 27, 12));
			case EAST -> VoxelShapes.union(createCuboidShape(13, 0, 1, 15, 1, 3), createCuboidShape(13, 0, 13, 15, 1, 15), createCuboidShape(11, 1, 13, 15, 2, 15), createCuboidShape(13, 1, 11, 15, 2, 13), createCuboidShape(1, 1, 11, 3, 2, 13), createCuboidShape(1, 1, 3, 3, 2, 5), createCuboidShape(13, 1, 3, 15, 2, 5), createCuboidShape(11, 1, 1, 15, 2, 3),
					createCuboidShape(1, 2, 1, 15, 3, 15), createCuboidShape(2, 3, 2, 14, 4, 14), createCuboidShape(4, 4, 4, 12, 5, 12), createCuboidShape(7, 5, 7, 9, 20, 9), createCuboidShape(1, 0, 1, 3, 1, 3), createCuboidShape(1, 0, 13, 3, 1, 15), createCuboidShape(1, 1, 13, 5, 2, 15), createCuboidShape(1, 1, 1, 5, 2, 3), createCuboidShape(4, 17, 3, 14, 27, 13));
			case WEST -> VoxelShapes.union(createCuboidShape(1, 0, 13, 3, 1, 15), createCuboidShape(1, 0, 1, 3, 1, 3), createCuboidShape(1, 1, 1, 5, 2, 3), createCuboidShape(1, 1, 3, 3, 2, 5), createCuboidShape(13, 1, 3, 15, 2, 5), createCuboidShape(13, 1, 11, 15, 2, 13), createCuboidShape(1, 1, 11, 3, 2, 13), createCuboidShape(1, 1, 13, 5, 2, 15),
					createCuboidShape(1, 2, 1, 15, 3, 15), createCuboidShape(2, 3, 2, 14, 4, 14), createCuboidShape(4, 4, 4, 12, 5, 12), createCuboidShape(7, 5, 7, 9, 20, 9), createCuboidShape(13, 0, 13, 15, 1, 15), createCuboidShape(13, 0, 1, 15, 1, 3), createCuboidShape(11, 1, 1, 15, 2, 3), createCuboidShape(11, 1, 13, 15, 2, 15), createCuboidShape(2, 17, 3, 12, 27, 13));
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
		BlockRenderLayerMap.INSTANCE.putBlock(LuphieclutteredmodModBlocks.LUPHIE_ANCIENT_CODEX, RenderLayer.getSolid());
	}
}
