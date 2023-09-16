package net.mcreator.clutteredmod.block.entity;

import net.mcreator.clutteredmod.init.LuphieclutteredmodModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

import java.util.stream.IntStream;

public class LuphieLightGreenInnerCornerCabinetBlockEntity extends LootableContainerBlockEntity implements ScreenHandlerFactory, SidedInventory {
	private DefaultedList<ItemStack> stacks = DefaultedList.<ItemStack>ofSize(27, ItemStack.EMPTY);

	public LuphieLightGreenInnerCornerCabinetBlockEntity(BlockPos position, BlockState state) {
		super(LuphieclutteredmodModBlockEntities.LUPHIE_LIGHT_GREEN_INNER_CORNER_CABINET, position, state);
	}

	@Override
	public void readNbt(NbtCompound compound) {
		super.readNbt(compound);
		if (!this.deserializeLootTable(compound))
			this.stacks = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
		Inventories.readNbt(compound, this.stacks);
	}

	@Override
	public void writeNbt(NbtCompound compound) {
		super.writeNbt(compound);
		if (!this.serializeLootTable(compound))
			Inventories.writeNbt(compound, this.stacks);
	}

	@Override
	public BlockEntityUpdateS2CPacket toUpdatePacket() {
		return BlockEntityUpdateS2CPacket.create(this);
	}

	@Override
	public NbtCompound toInitialChunkDataNbt() {
		return this.createNbt();
	}

	@Override
	public int size() {
		return stacks.size();
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.stacks)
			if (!itemstack.isEmpty())
				return false;
		return true;
	}

	@Override
	public Text getContainerName() {
		return Text.literal("luphie_light_green_inner_corner_cabinet");
	}

	@Override
	public int getMaxCountPerStack() {
		return 64;
	}

	@Override
	public ScreenHandler createScreenHandler(int id, PlayerInventory inventory) {
		return GenericContainerScreenHandler.createGeneric9x3(id, inventory, this);
	}

	@Override
	public Text getDisplayName() {
		return Text.literal("Light Green Inner Corner Cabinet");
	}

	@Override
	protected DefaultedList<ItemStack> getInvStackList() {
		return this.stacks;
	}

	@Override
	protected void setInvStackList(DefaultedList<ItemStack> stacks) {
		this.stacks = stacks;
	}

	@Override
	public boolean isValid(int index, ItemStack stack) {
		return true;
	}

	@Override
	public int[] getAvailableSlots(Direction side) {
		return IntStream.range(0, this.size()).toArray();
	}

	@Override
	public boolean canInsert(int index, ItemStack stack, @Nullable Direction direction) {
		return this.isValid(index, stack);
	}

	@Override
	public boolean canExtract(int index, ItemStack stack, Direction direction) {
		return true;
	}
}
