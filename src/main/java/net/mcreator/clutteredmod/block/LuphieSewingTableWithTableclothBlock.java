
package net.mcreator.clutteredmod.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.mcreator.clutteredmod.init.LuphieclutteredmodModBlocks;
import net.minecraft.block.*;
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

public class LuphieSewingTableWithTableclothBlock extends Block {
	public static AbstractBlock.Settings PROPERTIES = FabricBlockSettings.create().sounds(BlockSoundGroup.WOOD).strength(1f, 10f).nonOpaque()
			.solidBlock((bs, br, bp) -> false);
	public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

	public LuphieSewingTableWithTableclothBlock() {
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
			default -> VoxelShapes.union(createCuboidShape(1, 0, 1, 16, 13, 15), createCuboidShape(16, 0, 1, 31, 13, 15), createCuboidShape(16, 13, 0, 32, 16, 16), createCuboidShape(0, 13, 0, 16, 16, 16));
			case NORTH -> VoxelShapes.union(createCuboidShape(0, 0, 1, 15, 13, 15), createCuboidShape(-15, 0, 1, 0, 13, 15), createCuboidShape(-16, 13, 0, 0, 16, 16), createCuboidShape(0, 13, 0, 16, 16, 16));
			case EAST -> VoxelShapes.union(createCuboidShape(1, 0, 0, 15, 13, 15), createCuboidShape(1, 0, -15, 15, 13, 0), createCuboidShape(0, 13, -16, 16, 16, 0), createCuboidShape(0, 13, 0, 16, 16, 16));
			case WEST -> VoxelShapes.union(createCuboidShape(1, 0, 1, 15, 13, 16), createCuboidShape(1, 0, 16, 15, 13, 31), createCuboidShape(0, 13, 16, 16, 16, 32), createCuboidShape(0, 13, 0, 16, 16, 16));
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
		BlockRenderLayerMap.INSTANCE.putBlock(LuphieclutteredmodModBlocks.LUPHIE_SEWING_TABLE_WITH_TABLECLOTH, RenderLayer.getSolid());
	}
}
