
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

public class LuphieAncientCodexBlock extends Block {
	public static BlockBehaviour.Properties PROPERTIES = FabricBlockSettings.of(Material.WOOD).sound(SoundType.WOOD).strength(1f, 10f).noOcclusion()
			.isRedstoneConductor((bs, br, bp) -> false);
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public LuphieAncientCodexBlock() {
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
			default -> Shapes.or(box(13, 0, 13, 15, 1, 15), box(1, 0, 13, 3, 1, 15), box(1, 1, 11, 3, 2, 15), box(3, 1, 13, 5, 2, 15),
					box(3, 1, 1, 5, 2, 3), box(11, 1, 1, 13, 2, 3), box(11, 1, 13, 13, 2, 15), box(13, 1, 11, 15, 2, 15), box(1, 2, 1, 15, 3, 15),
					box(2, 3, 2, 14, 4, 14), box(4, 4, 4, 12, 5, 12), box(7, 5, 7, 9, 20, 9), box(13, 0, 1, 15, 1, 3), box(1, 0, 1, 3, 1, 3),
					box(1, 1, 1, 3, 2, 5), box(13, 1, 1, 15, 2, 5), box(3, 17, 4, 13, 27, 14));
			case NORTH -> Shapes.or(box(1, 0, 1, 3, 1, 3), box(13, 0, 1, 15, 1, 3), box(13, 1, 1, 15, 2, 5), box(11, 1, 1, 13, 2, 3),
					box(11, 1, 13, 13, 2, 15), box(3, 1, 13, 5, 2, 15), box(3, 1, 1, 5, 2, 3), box(1, 1, 1, 3, 2, 5), box(1, 2, 1, 15, 3, 15),
					box(2, 3, 2, 14, 4, 14), box(4, 4, 4, 12, 5, 12), box(7, 5, 7, 9, 20, 9), box(1, 0, 13, 3, 1, 15), box(13, 0, 13, 15, 1, 15),
					box(13, 1, 11, 15, 2, 15), box(1, 1, 11, 3, 2, 15), box(3, 17, 2, 13, 27, 12));
			case EAST -> Shapes.or(box(13, 0, 1, 15, 1, 3), box(13, 0, 13, 15, 1, 15), box(11, 1, 13, 15, 2, 15), box(13, 1, 11, 15, 2, 13),
					box(1, 1, 11, 3, 2, 13), box(1, 1, 3, 3, 2, 5), box(13, 1, 3, 15, 2, 5), box(11, 1, 1, 15, 2, 3), box(1, 2, 1, 15, 3, 15),
					box(2, 3, 2, 14, 4, 14), box(4, 4, 4, 12, 5, 12), box(7, 5, 7, 9, 20, 9), box(1, 0, 1, 3, 1, 3), box(1, 0, 13, 3, 1, 15),
					box(1, 1, 13, 5, 2, 15), box(1, 1, 1, 5, 2, 3), box(4, 17, 3, 14, 27, 13));
			case WEST -> Shapes.or(box(1, 0, 13, 3, 1, 15), box(1, 0, 1, 3, 1, 3), box(1, 1, 1, 5, 2, 3), box(1, 1, 3, 3, 2, 5),
					box(13, 1, 3, 15, 2, 5), box(13, 1, 11, 15, 2, 13), box(1, 1, 11, 3, 2, 13), box(1, 1, 13, 5, 2, 15), box(1, 2, 1, 15, 3, 15),
					box(2, 3, 2, 14, 4, 14), box(4, 4, 4, 12, 5, 12), box(7, 5, 7, 9, 20, 9), box(13, 0, 13, 15, 1, 15), box(13, 0, 1, 15, 1, 3),
					box(11, 1, 1, 15, 2, 3), box(11, 1, 13, 15, 2, 15), box(2, 17, 3, 12, 27, 13));
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
		BlockRenderLayerMap.INSTANCE.putBlock(LuphieclutteredmodModBlocks.LUPHIE_ANCIENT_CODEX, RenderType.solid());
	}
}
