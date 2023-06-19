
package net.mcreator.clutteredmod.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.Vec3;
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
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ItemStack;
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

public class LuphieMushroomTableBlock extends Block {
	public static BlockBehaviour.Properties PROPERTIES = FabricBlockSettings.of(Material.DECORATION).sound(SoundType.WOOD).strength(1f, 10f)
			.noOcclusion().isRedstoneConductor((bs, br, bp) -> false);
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public LuphieMushroomTableBlock() {
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
			default -> Shapes.or(box(10, 7, -4, 18, 9, 4), box(0, 11, -16, 32, 14, 16), box(1, 14, -15, 31, 16, 15), box(11, 0, -2, 15, 7, 2),
					box(13, 7, -2, 17, 11, 2));
			case NORTH -> Shapes.or(box(-2, 7, 12, 6, 9, 20), box(-16, 11, 0, 16, 14, 32), box(-15, 14, 1, 15, 16, 31), box(1, 0, 14, 5, 7, 18),
					box(-1, 7, 14, 3, 11, 18));
			case EAST -> Shapes.or(box(-4, 7, -2, 4, 9, 6), box(-16, 11, -16, 16, 14, 16), box(-15, 14, -15, 15, 16, 15), box(-2, 0, 1, 2, 7, 5),
					box(-2, 7, -1, 2, 11, 3));
			case WEST -> Shapes.or(box(12, 7, 10, 20, 9, 18), box(0, 11, 0, 32, 14, 32), box(1, 14, 1, 31, 16, 31), box(14, 0, 11, 18, 7, 15),
					box(14, 7, 13, 18, 11, 17));
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

	@Environment(EnvType.CLIENT)
	public static void clientInit() {
		BlockRenderLayerMap.INSTANCE.putBlock(LuphieclutteredmodModBlocks.LUPHIE_MUSHROOM_TABLE, RenderType.solid());
	}
}
