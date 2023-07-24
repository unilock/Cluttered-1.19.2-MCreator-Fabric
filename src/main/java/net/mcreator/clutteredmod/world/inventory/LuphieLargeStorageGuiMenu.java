
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

public class LuphieLargeStorageGuiMenu extends ScreenHandler {
	public final static HashMap<String, Object> guistate = new HashMap<>();
	public final World world;
	public final PlayerEntity entity;
	public int x, y, z;
	private BlockPos pos;
	private final Inventory inventory;
	private boolean bound = false;

	public LuphieLargeStorageGuiMenu(int id, PlayerInventory inv, PacketByteBuf extraData) {
		this(id, inv, new SimpleInventory(54));
		if (extraData != null) {
			pos = extraData.readBlockPos();
			this.x = pos.getX();
			this.y = pos.getY();
			this.z = pos.getZ();
		}
	}

	public LuphieLargeStorageGuiMenu(int id, PlayerInventory inv, Inventory container) {
		super(LuphieclutteredmodModMenus.LUPHIE_LARGE_STORAGE_GUI, id);
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
		this.addSlot(new Slot(inventory, 27, 7, 71) {
		});
		this.addSlot(new Slot(inventory, 28, 25, 71) {
		});
		this.addSlot(new Slot(inventory, 29, 43, 71) {
		});
		this.addSlot(new Slot(inventory, 30, 61, 71) {
		});
		this.addSlot(new Slot(inventory, 31, 79, 71) {
		});
		this.addSlot(new Slot(inventory, 32, 97, 71) {
		});
		this.addSlot(new Slot(inventory, 33, 115, 71) {
		});
		this.addSlot(new Slot(inventory, 34, 133, 71) {
		});
		this.addSlot(new Slot(inventory, 35, 151, 71) {
		});
		this.addSlot(new Slot(inventory, 36, 7, 89) {
		});
		this.addSlot(new Slot(inventory, 37, 25, 89) {
		});
		this.addSlot(new Slot(inventory, 38, 43, 89) {
		});
		this.addSlot(new Slot(inventory, 39, 61, 89) {
		});
		this.addSlot(new Slot(inventory, 40, 79, 89) {
		});
		this.addSlot(new Slot(inventory, 41, 97, 89) {
		});
		this.addSlot(new Slot(inventory, 42, 115, 89) {
		});
		this.addSlot(new Slot(inventory, 43, 133, 89) {
		});
		this.addSlot(new Slot(inventory, 44, 151, 89) {
		});
		this.addSlot(new Slot(inventory, 45, 7, 107) {
		});
		this.addSlot(new Slot(inventory, 46, 25, 107) {
		});
		this.addSlot(new Slot(inventory, 47, 43, 107) {
		});
		this.addSlot(new Slot(inventory, 48, 61, 107) {
		});
		this.addSlot(new Slot(inventory, 49, 79, 107) {
		});
		this.addSlot(new Slot(inventory, 50, 97, 107) {
		});
		this.addSlot(new Slot(inventory, 51, 115, 107) {
		});
		this.addSlot(new Slot(inventory, 52, 133, 107) {
		});
		this.addSlot(new Slot(inventory, 53, 151, 107) {
		});
		for (int si = 0; si < 3; ++si)
			for (int sj = 0; sj < 9; ++sj)
				this.addSlot(new Slot(inv, sj + (si + 1) * 9, 0 + 8 + sj * 18, 54 + 84 + si * 18));
		for (int si = 0; si < 9; ++si)
			this.addSlot(new Slot(inv, si, 0 + 8 + si * 18, 54 + 142));
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
			if (index < 54) {
				if (!this.insertItem(itemstack1, 54, this.slots.size(), true))
					return ItemStack.EMPTY;
				slot.onQuickTransfer(itemstack1, itemstack);
			} else if (!this.insertItem(itemstack1, 0, 54, false)) {
				if (index < 54 + 27) {
					if (!this.insertItem(itemstack1, 54 + 27, this.slots.size(), true))
						return ItemStack.EMPTY;
				} else {
					if (!this.insertItem(itemstack1, 54, 54 + 27, false))
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
