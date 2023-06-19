
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

public class LuphiebunnyPlushieBlock extends Block {
	public static BlockBehaviour.Properties PROPERTIES = FabricBlockSettings.of(Material.WOOL).sound(SoundType.WOOL).strength(1f, 10f).noOcclusion()
			.isRedstoneConductor((bs, br, bp) -> false);
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public LuphiebunnyPlushieBlock() {
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
			default -> Shapes.or(box(5, 0, 4, 11, 7, 9), box(7, 0, 2, 9, 3, 4), box(5, 7, 5, 11, 11, 9), box(5, 0, 9, 7, 2, 11),
					box(6, 15, 7, 7, 16, 8), box(9, 0, 9, 11, 2, 11), box(5, 11, 7, 7, 15, 8), box(9, 11, 7, 11, 15, 8), box(6, 7, 9, 10, 9, 10),
					box(9, 15, 7, 10, 16, 8));
			case NORTH -> Shapes.or(box(5, 0, 7, 11, 7, 12), box(7, 0, 12, 9, 3, 14), box(5, 7, 7, 11, 11, 11), box(9, 0, 5, 11, 2, 7),
					box(9, 15, 8, 10, 16, 9), box(5, 0, 5, 7, 2, 7), box(9, 11, 8, 11, 15, 9), box(5, 11, 8, 7, 15, 9), box(6, 7, 6, 10, 9, 7),
					box(6, 15, 8, 7, 16, 9));
			case EAST ->
				Shapes.or(box(4, 0, 5, 9, 7, 11), box(2, 0, 7, 4, 3, 9), box(5, 7, 5, 9, 11, 11), box(9, 0, 9, 11, 2, 11), box(7, 15, 9, 8, 16, 10),
						box(9, 0, 5, 11, 2, 7), box(7, 11, 9, 8, 15, 11), box(7, 11, 5, 8, 15, 7), box(9, 7, 6, 10, 9, 10), box(7, 15, 6, 8, 16, 7));
			case WEST ->
				Shapes.or(box(7, 0, 5, 12, 7, 11), box(12, 0, 7, 14, 3, 9), box(7, 7, 5, 11, 11, 11), box(5, 0, 5, 7, 2, 7), box(8, 15, 6, 9, 16, 7),
						box(5, 0, 9, 7, 2, 11), box(8, 11, 5, 9, 15, 7), box(8, 11, 9, 9, 15, 11), box(6, 7, 6, 7, 9, 10), box(8, 15, 9, 9, 16, 10));
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
		BlockRenderLayerMap.INSTANCE.putBlock(LuphieclutteredmodModBlocks.LUPHIEBUNNY_PLUSHIE, RenderType.solid());
	}
}
