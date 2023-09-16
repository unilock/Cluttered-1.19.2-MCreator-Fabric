
package net.mcreator.clutteredmod.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.mcreator.clutteredmod.init.LuphieclutteredmodModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import java.util.Collections;
import java.util.List;

public class AnchorwallpaperwainscotBlock extends Block {
	public static Settings PROPERTIES = Settings.create().sounds(BlockSoundGroup.WOOD).strength(1f, 10f);

	public AnchorwallpaperwainscotBlock() {
		super(PROPERTIES);
	}

	@Override
	public void appendTooltip(ItemStack itemstack, BlockView world, List<Text> list, TooltipContext flag) {
		super.appendTooltip(itemstack, world, list, flag);
		list.add(Text.literal("Valorant"));
	}

	@Override
	public int getOpacity(BlockState state, BlockView worldIn, BlockPos pos) {
		return 15;
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
		BlockRenderLayerMap.INSTANCE.putBlock(LuphieclutteredmodModBlocks.ANCHORWALLPAPERWAINSCOT, RenderLayer.getSolid());
	}
}