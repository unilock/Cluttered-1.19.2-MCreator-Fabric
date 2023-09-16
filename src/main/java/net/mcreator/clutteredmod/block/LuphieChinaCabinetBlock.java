
package net.mcreator.clutteredmod.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.mcreator.clutteredmod.block.entity.LuphieChinaCabinetBlockEntity;
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

public class LuphieChinaCabinetBlock extends Block implements BlockEntityProvider {
	public static Settings PROPERTIES = Settings.create().sounds(BlockSoundGroup.WOOD).strength(1f, 10f).nonOpaque().solidBlock((bs, br, bp) -> false);
	public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

	public LuphieChinaCabinetBlock() {
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
			default -> VoxelShapes.union(createCuboidShape(0, 0, 0, 16, 2, 16), createCuboidShape(2, 3, 0, 3, 30, 14), createCuboidShape(0, 31, 0, 16, 32, 16), createCuboidShape(13, 3, 0, 14, 30, 14), createCuboidShape(3, 3, 0, 13, 30, 2), createCuboidShape(3, 4, 13, 13, 29, 14), createCuboidShape(3, 4, 2, 13, 29, 12), createCuboidShape(3, 29, 2, 13, 30, 14),
					createCuboidShape(3, 3, 2, 13, 4, 14), createCuboidShape(1, 2, 0, 15, 3, 15), createCuboidShape(1, 30, 0, 15, 31, 15));
			case NORTH -> VoxelShapes.union(createCuboidShape(0, 0, 0, 16, 2, 16), createCuboidShape(13, 3, 2, 14, 30, 16), createCuboidShape(0, 31, 0, 16, 32, 16), createCuboidShape(2, 3, 2, 3, 30, 16), createCuboidShape(3, 3, 14, 13, 30, 16), createCuboidShape(3, 4, 2, 13, 29, 3), createCuboidShape(3, 4, 4, 13, 29, 14), createCuboidShape(3, 29, 2, 13, 30, 14),
					createCuboidShape(3, 3, 2, 13, 4, 14), createCuboidShape(1, 2, 1, 15, 3, 16), createCuboidShape(1, 30, 1, 15, 31, 16));
			case EAST -> VoxelShapes.union(createCuboidShape(0, 0, 0, 16, 2, 16), createCuboidShape(0, 3, 13, 14, 30, 14), createCuboidShape(0, 31, 0, 16, 32, 16), createCuboidShape(0, 3, 2, 14, 30, 3), createCuboidShape(0, 3, 3, 2, 30, 13), createCuboidShape(13, 4, 3, 14, 29, 13), createCuboidShape(2, 4, 3, 12, 29, 13), createCuboidShape(2, 29, 3, 14, 30, 13),
					createCuboidShape(2, 3, 3, 14, 4, 13), createCuboidShape(0, 2, 1, 15, 3, 15), createCuboidShape(0, 30, 1, 15, 31, 15));
			case WEST -> VoxelShapes.union(createCuboidShape(0, 0, 0, 16, 2, 16), createCuboidShape(2, 3, 2, 16, 30, 3), createCuboidShape(0, 31, 0, 16, 32, 16), createCuboidShape(2, 3, 13, 16, 30, 14), createCuboidShape(14, 3, 3, 16, 30, 13), createCuboidShape(2, 4, 3, 3, 29, 13), createCuboidShape(4, 4, 3, 14, 29, 13), createCuboidShape(2, 29, 3, 14, 30, 13),
					createCuboidShape(2, 3, 3, 14, 4, 13), createCuboidShape(1, 2, 1, 16, 3, 15), createCuboidShape(1, 30, 1, 16, 31, 15));
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
		return new LuphieChinaCabinetBlockEntity(pos, state);
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
			if (blockEntity instanceof LuphieChinaCabinetBlockEntity be) {
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
		if (tileentity instanceof LuphieChinaCabinetBlockEntity)
			return ScreenHandler.calculateComparatorOutput(tileentity);
		else
			return 0;
	}

	@Environment(EnvType.CLIENT)
	public static void clientInit() {
		BlockRenderLayerMap.INSTANCE.putBlock(LuphieclutteredmodModBlocks.LUPHIE_CHINA_CABINET, RenderLayer.getCutout());
	}
}
