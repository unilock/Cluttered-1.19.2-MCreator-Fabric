
package net.mcreator.clutteredmod.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.client.renderer.RenderType;

import net.mcreator.clutteredmod.init.LuphieclutteredmodModBlocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

import java.util.List;
import java.util.Collections;

public class LuphieMushroomWardrobeBlock extends Block {
	public static BlockBehaviour.Properties PROPERTIES = FabricBlockSettings.of(Material.DECORATION).sound(SoundType.WOOD).strength(1f, 10f)
			.noOcclusion().isRedstoneConductor((bs, br, bp) -> false);
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public LuphieMushroomWardrobeBlock() {
		super(PROPERTIES);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	public void appendHoverText(ItemStack itemstack, BlockGetter world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		list.add(Component.literal("Starbound"));
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		Vec3 offset = state.getOffset(world, pos);
		return (switch (state.getValue(FACING)) {
			default -> Shapes.or(box(0, -16, 2, 4, -14, 12), box(0, -14, 2, 5, -11, 12), box(0, -11, 0, 11, 10, 14), box(0, 10, 0, 10, 13, 14),
					box(0, 13, 0, 7, 14, 14), box(0, 14, 6, 1, 16, 8), box(-3, 16, 4, 3, 18, 10), box(-1, 14, 6, 0, 16, 8), box(-7, 13, 0, 0, 14, 14),
					box(-10, 10, 0, 0, 13, 14), box(-11, -11, 0, 0, 10, 14), box(-5, -14, 2, 0, -11, 12), box(-4, -16, 2, 0, -14, 12));
			case NORTH -> Shapes.or(box(12, -16, 4, 16, -14, 14), box(11, -14, 4, 16, -11, 14), box(5, -11, 2, 16, 10, 16), box(6, 10, 2, 16, 13, 16),
					box(9, 13, 2, 16, 14, 16), box(15, 14, 8, 16, 16, 10), box(13, 16, 6, 19, 18, 12), box(16, 14, 8, 17, 16, 10),
					box(16, 13, 2, 23, 14, 16), box(16, 10, 2, 26, 13, 16), box(16, -11, 2, 27, 10, 16), box(16, -14, 4, 21, -11, 14),
					box(16, -16, 4, 20, -14, 14));
			case EAST -> Shapes.or(box(2, -16, 12, 12, -14, 16), box(2, -14, 11, 12, -11, 16), box(0, -11, 5, 14, 10, 16), box(0, 10, 6, 14, 13, 16),
					box(0, 13, 9, 14, 14, 16), box(6, 14, 15, 8, 16, 16), box(4, 16, 13, 10, 18, 19), box(6, 14, 16, 8, 16, 17),
					box(0, 13, 16, 14, 14, 23), box(0, 10, 16, 14, 13, 26), box(0, -11, 16, 14, 10, 27), box(2, -14, 16, 12, -11, 21),
					box(2, -16, 16, 12, -14, 20));
			case WEST -> Shapes.or(box(4, -16, 0, 14, -14, 4), box(4, -14, 0, 14, -11, 5), box(2, -11, 0, 16, 10, 11), box(2, 10, 0, 16, 13, 10),
					box(2, 13, 0, 16, 14, 7), box(8, 14, 0, 10, 16, 1), box(6, 16, -3, 12, 18, 3), box(8, 14, -1, 10, 16, 0),
					box(2, 13, -7, 16, 14, 0), box(2, 10, -10, 16, 13, 0), box(2, -11, -11, 16, 10, 0), box(4, -14, -5, 14, -11, 0),
					box(4, -16, -4, 14, -14, 0));
		}).move(offset.x, offset.y, offset.z);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
		List<ItemStack> dropsOriginal = super.getDrops(state, builder);
		if (!dropsOriginal.isEmpty())
			return dropsOriginal;
		return Collections.singletonList(new ItemStack(this, 1));
	}

	@Override
	public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
		super.use(blockstate, world, pos, entity, hand, hit);
		return InteractionResult.SUCCESS;
	}

	@Environment(EnvType.CLIENT)
	public static void clientInit() {
		BlockRenderLayerMap.INSTANCE.putBlock(LuphieclutteredmodModBlocks.LUPHIE_MUSHROOM_WARDROBE, RenderType.solid());
	}
}
