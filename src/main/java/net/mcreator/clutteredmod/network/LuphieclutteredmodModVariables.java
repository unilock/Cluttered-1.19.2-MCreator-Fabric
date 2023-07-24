package net.mcreator.clutteredmod.network;

import net.fabricmc.fabric.api.entity.event.v1.ServerEntityWorldChangeEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class LuphieclutteredmodModVariables {
	public static void SyncJoin() {
		ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
			if (entity instanceof PlayerEntity) {
				if (!world.isClient()) {
					PersistentState mapdata = MapVariables.get(world);
					PersistentState worlddata = WorldVariables.get(world);
				}
			}
		});
	}

	public static void SyncChangeWorld() {
		ServerEntityWorldChangeEvents.AFTER_PLAYER_CHANGE_WORLD.register((player, origin, destination) -> {
			if (!destination.isClient()) {
				PersistentState worlddata = WorldVariables.get(destination);
			}
		});
	}

	public static class WorldVariables extends PersistentState {
		public static final String DATA_NAME = "luphieclutteredmod_worldvars";
		public double bedTimer = 0;
		public boolean bedTimerEnabled = false;

		public static WorldVariables load(NbtCompound tag) {
			WorldVariables data = new WorldVariables();
			data.read(tag);
			return data;
		}

		public void read(NbtCompound nbt) {
			bedTimer = nbt.getDouble("bedTimer");
			bedTimerEnabled = nbt.getBoolean("bedTimerEnabled");
		}

		@Override
		public NbtCompound writeNbt(NbtCompound nbt) {
			nbt.putDouble("bedTimer", bedTimer);
			nbt.putBoolean("bedTimerEnabled", bedTimerEnabled);
			return nbt;
		}

		public void syncData(WorldAccess world) {
			this.markDirty();
		}

		static WorldVariables clientSide = new WorldVariables();

		public static WorldVariables get(WorldAccess world) {
			if (world instanceof ServerWorld level) {
				return level.getPersistentStateManager().getOrCreate(e -> WorldVariables.load(e), WorldVariables::new, DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class MapVariables extends PersistentState {
		public static final String DATA_NAME = "luphieclutteredmod_mapvars";

		public static MapVariables load(NbtCompound tag) {
			MapVariables data = new MapVariables();
			data.read(tag);
			return data;
		}

		public void read(NbtCompound nbt) {
		}

		@Override
		public NbtCompound writeNbt(NbtCompound nbt) {
			return nbt;
		}

		public void syncData(WorldAccess world) {
			this.markDirty();
		}

		static MapVariables clientSide = new MapVariables();

		public static MapVariables get(WorldAccess world) {
			if (world instanceof ServerWorldAccess serverLevelAcc) {
				return serverLevelAcc.toServerWorld().getServer().getWorld(World.OVERWORLD).getPersistentStateManager().getOrCreate(e -> MapVariables.load(e),
						MapVariables::new, DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class SavedDataSyncMessage {
		public int type;
		public PersistentState data;

		public SavedDataSyncMessage(PacketByteBuf buffer) {
			this.type = buffer.readInt();
			this.data = this.type == 0 ? new MapVariables() : new WorldVariables();
			if (this.data instanceof MapVariables _mapvars)
				_mapvars.read(buffer.readNbt());
			else if (this.data instanceof WorldVariables _worldvars)
				_worldvars.read(buffer.readNbt());
		}

		public SavedDataSyncMessage(int type, PersistentState data) {
			this.type = type;
			this.data = data;
		}

		public static void buffer(SavedDataSyncMessage message, PacketByteBuf buffer) {
			buffer.writeInt(message.type);
			buffer.writeNbt(message.data.writeNbt(new NbtCompound()));
		}
	}
}
