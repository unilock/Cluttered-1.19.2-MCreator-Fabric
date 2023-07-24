
package net.mcreator.clutteredmod.world.inventory;

import net.mcreator.clutteredmod.init.LuphieclutteredmodModMenus;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;

public class LuphieSmallStorageGuiMenu extends ScreenHandler {
	public final static HashMap<String, Object> guistate = new HashMap<>();
	public final World world;
	public final PlayerEntity entity;
	public int x, y, z;
	private BlockPos pos;
	private final Inventory inventory;
	private boolean bound = false;

	public LuphieSmallStorageGuiMenu(int id, PlayerInventory inv, PacketByteBuf extraData) {
		this(id, inv, new SimpleInventory(27));
		if (extraData != null) {
			pos = extraData.readBlockPos();
			this.x = pos.getX();
			this.y = pos.getY();
			this.z = pos.getZ();
		}
	}

	public LuphieSmallStorageGuiMenu(int id, PlayerInventory inv, Inventory container) {
		super(LuphieclutteredmodModMenus.LUPHIE_SMALL_STORAGE_GUI, id);
		this.entity = inv.player;
		this.world = inv.player.getWorld();
		this.inventory = container;
		this.addSlot(new Slot(inventory, 0, 7, 17) {
		});
		this.addSlot(new Slot(inventory, 1, 25, 17) {
		});
		this.addSlot(new Slot(inventory, 2, 43, 17) {
		});
		this.addSlot(new Slot(inventory, 3, 61, 17) {
		});
		this.addSlot(new Slot(inventory, 4, 79, 17) {
		});
		this.addSlot(new Slot(inventory, 5, 97, 17) {
		});
		this.addSlot(new Slot(inventory, 6, 115, 17) {
		});
		this.addSlot(new Slot(inventory, 7, 133, 17) {
		});
		this.addSlot(new Slot(inventory, 8, 151, 17) {
		});
		this.addSlot(new Slot(inventory, 9, 7, 35) {
		});
		this.addSlot(new Slot(inventory, 10, 25, 35) {
		});
		this.addSlot(new Slot(inventory, 11, 43, 35) {
		});
		this.addSlot(new Slot(inventory, 12, 61, 35) {
		});
		this.addSlot(new Slot(inventory, 13, 79, 35) {
		});
		this.addSlot(new Slot(inventory, 14, 97, 35) {
		});
		this.addSlot(new Slot(inventory, 15, 115, 35) {
		});
		this.addSlot(new Slot(inventory, 16, 133, 35) {
		});
		this.addSlot(new Slot(inventory, 17, 151, 35) {
		});
		this.addSlot(new Slot(inventory, 18, 7, 53) {
		});
		this.addSlot(new Slot(inventory, 19, 25, 53) {
		});
		this.addSlot(new Slot(inventory, 20, 43, 53) {
		});
		this.addSlot(new Slot(inventory, 21, 61, 53) {
		});
		this.addSlot(new Slot(inventory, 22, 79, 53) {
		});
		this.addSlot(new Slot(inventory, 23, 97, 53) {
		});
		this.addSlot(new Slot(inventory, 24, 115, 53) {
		});
		this.addSlot(new Slot(inventory, 25, 133, 53) {
		});
		this.addSlot(new Slot(inventory, 26, 151, 53) {
		});
		for (int si = 0; si < 3; ++si)
			for (int sj = 0; sj < 9; ++sj)
				this.addSlot(new Slot(inv, sj + (si + 1) * 9, 0 + 8 + sj * 18, 0 + 84 + si * 18));
		for (int si = 0; si < 9; ++si)
			this.addSlot(new Slot(inv, si, 0 + 8 + si * 18, 0 + 142));
	}

	@Override
	public boolean canUse(PlayerEntity player) {
		return this.inventory.canPlayerUse(player);
	}

	@Override
	public ItemStack quickMove(PlayerEntity player, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = (Slot) this.slots.get(index);
		if (slot != null && slot.hasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (index < 27) {
				if (!this.insertItem(itemstack1, 27, this.slots.size(), true))
					return ItemStack.EMPTY;
				slot.onQuickTransfer(itemstack1, itemstack);
			} else if (!this.insertItem(itemstack1, 0, 27, false)) {
				if (index < 27 + 27) {
					if (!this.insertItem(itemstack1, 27 + 27, this.slots.size(), true))
						return ItemStack.EMPTY;
				} else {
					if (!this.insertItem(itemstack1, 27, 27 + 27, false))
						return ItemStack.EMPTY;
				}
				return ItemStack.EMPTY;
			}
			if (itemstack1.isEmpty())
				slot.setStackNoCallbacks(ItemStack.EMPTY);
			else
				slot.markDirty();
			if (itemstack1.getCount() == itemstack.getCount())
				return ItemStack.EMPTY;
			slot.onTakeItem(player, itemstack1);
		}
		return itemstack;
	}

	@Override
	public void onClosed(PlayerEntity playerIn) {
		super.onClosed(playerIn);
	}
}
