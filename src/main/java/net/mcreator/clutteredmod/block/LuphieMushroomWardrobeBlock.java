
package net.mcreator.clutteredmod.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.mcreator.clutteredmod.block.entity.LuphieMushroomWardrobeBlockEntity;
import net.mcreator.clutteredmod.init.LuphieclutteredmodModBlocks;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.List;

public class LuphieMushroomWardrobeBlock extends Block implements BlockEntityProvider {
	public static Settings PROPERTIES = Settings.create().sounds(BlockSoundGroup.WOOD).strength(1f, 10f).nonOpaque().solidBlock((bs, br, bp) -> false);
	public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

	public LuphieMushroomWardrobeBlock() {
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
			default -> VoxelShapes.union(createCuboidShape(0, -16, 2, 4, -14, 12), createCuboidShape(0, -14, 2, 5, -11, 12), createCuboidShape(0, -11, 0, 11, 10, 14), createCuboidShape(0, 10, 0, 10, 13, 14), createCuboidShape(0, 13, 0, 7, 14, 14), createCuboidShape(0, 14, 6, 1, 16, 8), createCuboidShape(-3, 16, 4, 3, 18, 10), createCuboidShape(-1, 14, 6, 0, 16, 8),
					createCuboidShape(-7, 13, 0, 0, 14, 14), createCuboidShape(-10, 10, 0, 0, 13, 14), createCuboidShape(-11, -11, 0, 0, 10, 14), createCuboidShape(-5, -14, 2, 0, -11, 12), createCuboidShape(-4, -16, 2, 0, -14, 12));
			case NORTH -> VoxelShapes.union(createCuboidShape(12, -16, 4, 16, -14, 14), createCuboidShape(11, -14, 4, 16, -11, 14), createCuboidShape(5, -11, 2, 16, 10, 16), createCuboidShape(6, 10, 2, 16, 13, 16), createCuboidShape(9, 13, 2, 16, 14, 16), createCuboidShape(15, 14, 8, 16, 16, 10), createCuboidShape(13, 16, 6, 19, 18, 12),
					createCuboidShape(16, 14, 8, 17, 16, 10), createCuboidShape(16, 13, 2, 23, 14, 16), createCuboidShape(16, 10, 2, 26, 13, 16), createCuboidShape(16, -11, 2, 27, 10, 16), createCuboidShape(16, -14, 4, 21, -11, 14), createCuboidShape(16, -16, 4, 20, -14, 14));
			case EAST -> VoxelShapes.union(createCuboidShape(2, -16, 12, 12, -14, 16), createCuboidShape(2, -14, 11, 12, -11, 16), createCuboidShape(0, -11, 5, 14, 10, 16), createCuboidShape(0, 10, 6, 14, 13, 16), createCuboidShape(0, 13, 9, 14, 14, 16), createCuboidShape(6, 14, 15, 8, 16, 16), createCuboidShape(4, 16, 13, 10, 18, 19),
					createCuboidShape(6, 14, 16, 8, 16, 17), createCuboidShape(0, 13, 16, 14, 14, 23), createCuboidShape(0, 10, 16, 14, 13, 26), createCuboidShape(0, -11, 16, 14, 10, 27), createCuboidShape(2, -14, 16, 12, -11, 21), createCuboidShape(2, -16, 16, 12, -14, 20));
			case WEST -> VoxelShapes.union(createCuboidShape(4, -16, 0, 14, -14, 4), createCuboidShape(4, -14, 0, 14, -11, 5), createCuboidShape(2, -11, 0, 16, 10, 11), createCuboidShape(2, 10, 0, 16, 13, 10), createCuboidShape(2, 13, 0, 16, 14, 7), createCuboidShape(8, 14, 0, 10, 16, 1), createCuboidShape(6, 16, -3, 12, 18, 3),
					createCuboidShape(8, 14, -1, 10, 16, 0), createCuboidShape(2, 13, -7, 16, 14, 0), createCuboidShape(2, 10, -10, 16, 13, 0), createCuboidShape(2, -11, -11, 16, 10, 0), createCuboidShape(4, -14, -5, 14, -11, 0), createCuboidShape(4, -16, -4, 14, -14, 0));
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

	@Override
	public ActionResult onUse(BlockState blockstate, World world, BlockPos pos, PlayerEntity entity, Hand hand, BlockHitResult hit) {
		super.onUse(blockstate, world, pos, entity, hand, hit);
		if (!world.isClient) {
			NamedScreenHandlerFactory menuProvider = blockstate.createScreenHandlerFactory(world, pos);
			if (menuProvider != null)
				entity.openHandledScreen(menuProvider);
		}
		return ActionResult.SUCCESS;
	}

	@Override
	public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World worldIn, BlockPos pos) {
		BlockEntity tileEntity = worldIn.getBlockEntity(pos);
		return tileEntity instanceof NamedScreenHandlerFactory ? (NamedScreenHandlerFactory) tileEntity : null;
	}

	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new LuphieMushroomWardrobeBlockEntity(pos, state);
	}

	@Override
	public boolean onSyncedBlockEvent(BlockState state, World world, BlockPos pos, int eventID, int eventParam) {
		super.onSyncedBlockEvent(state, world, pos, eventID, eventParam);
		BlockEntity blockEntity = world.getBlockEntity(pos);
		return blockEntity == null ? false : blockEntity.onSyncedBlockEvent(eventID, eventParam);
	}

	@Override
	public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity instanceof LuphieMushroomWardrobeBlockEntity be) {
				ItemScatterer.spawn(world, pos, be);
				world.updateComparators(pos, this);
			}
			super.onStateReplaced(state, world, pos, newState, isMoving);
		}
	}

	@Override
	public boolean hasComparatorOutput(BlockState state) {
		return true;
	}

	@Override
	public int getComparatorOutput(BlockState blockState, World world, BlockPos pos) {
		BlockEntity tileentity = world.getBlockEntity(pos);
		if (tileentity instanceof LuphieMushroomWardrobeBlockEntity)
			return ScreenHandler.calculateComparatorOutput(tileentity);
		else
			return 0;
	}

	@Environment(EnvType.CLIENT)
	public static void clientInit() {
		BlockRenderLayerMap.INSTANCE.putBlock(LuphieclutteredmodModBlocks.LUPHIE_MUSHROOM_WARDROBE, RenderLayer.getSolid());
	}
}
