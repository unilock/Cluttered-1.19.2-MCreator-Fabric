
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

public class LuphieGreenGumballMachineBlock extends Block {
	public static AbstractBlock.Settings PROPERTIES = FabricBlockSettings.create().sounds(BlockSoundGroup.METAL).strength(1f, 10f)
			.nonOpaque().solidBlock((bs, br, bp) -> false);
	public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

	public LuphieGreenGumballMachineBlock() {
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
			default -> VoxelShapes.union(createCuboidShape(3, 27, 2, 13, 28, 12), createCuboidShape(3, 0, 3, 13, 1, 13), createCuboidShape(5, 1, 5, 11, 2, 11), createCuboidShape(7, 2, 7, 9, 7, 9),
					createCuboidShape(4, 7, 4, 12, 16, 11), createCuboidShape(2, 16, 2, 14, 27, 13));
			case NORTH -> VoxelShapes.union(createCuboidShape(3, 27, 4, 13, 28, 14), createCuboidShape(3, 0, 3, 13, 1, 13), createCuboidShape(5, 1, 5, 11, 2, 11), createCuboidShape(7, 2, 7, 9, 7, 9),
					createCuboidShape(4, 7, 5, 12, 16, 12), createCuboidShape(2, 16, 3, 14, 27, 14));
			case EAST -> VoxelShapes.union(createCuboidShape(2, 27, 3, 12, 28, 13), createCuboidShape(3, 0, 3, 13, 1, 13), createCuboidShape(5, 1, 5, 11, 2, 11), createCuboidShape(7, 2, 7, 9, 7, 9),
					createCuboidShape(4, 7, 4, 11, 16, 12), createCuboidShape(2, 16, 2, 13, 27, 14));
			case WEST -> VoxelShapes.union(createCuboidShape(4, 27, 3, 14, 28, 13), createCuboidShape(3, 0, 3, 13, 1, 13), createCuboidShape(5, 1, 5, 11, 2, 11), createCuboidShape(7, 2, 7, 9, 7, 9),
					createCuboidShape(5, 7, 4, 12, 16, 12), createCuboidShape(3, 16, 2, 14, 27, 14));
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
		BlockRenderLayerMap.INSTANCE.putBlock(LuphieclutteredmodModBlocks.LUPHIE_GREEN_GUMBALL_MACHINE, RenderLayer.getCutout());
	}
}
