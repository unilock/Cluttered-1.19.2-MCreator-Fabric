
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
import net.minecraft.world.BlockView;

import java.util.Collections;
import java.util.List;

public class LuphieDarkwoodClockBlock extends Block {
	public static AbstractBlock.Settings PROPERTIES = FabricBlockSettings.create().sounds(BlockSoundGroup.WOOD).strength(1f, 10f).nonOpaque()
			.solidBlock((bs, br, bp) -> false);
	public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

	public LuphieDarkwoodClockBlock() {
		super(PROPERTIES);
		this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
	}

	@Override
	public void appendTooltip(ItemStack itemstack, BlockView world, List<Text> list, TooltipContext flag) {
		super.appendTooltip(itemstack, world, list, flag);
		list.add(Text.literal("ACNH"));
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
			default -> createCuboidShape(3.5, 4.5, 0, 12.5, 13.5, 2);
			case NORTH -> createCuboidShape(3.5, 4.5, 14, 12.5, 13.5, 16);
			case EAST -> createCuboidShape(0, 4.5, 3.5, 2, 13.5, 12.5);
			case WEST -> createCuboidShape(14, 4.5, 3.5, 16, 13.5, 12.5);
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
		BlockRenderLayerMap.INSTANCE.putBlock(LuphieclutteredmodModBlocks.LUPHIE_DARKWOOD_CLOCK, RenderLayer.getCutout());
	}
}
