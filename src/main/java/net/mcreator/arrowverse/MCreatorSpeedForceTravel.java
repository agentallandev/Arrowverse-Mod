package net.mcreator.arrowverse;

import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.potion.EffectInstance;
import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
import net.minecraft.network.play.server.SPlaySoundEventPacket;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.network.play.server.SChangeGameStatePacket;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

@Elementsarrowverse.ModElement.Tag
public class MCreatorSpeedForceTravel extends Elementsarrowverse.ModElement {
	public MCreatorSpeedForceTravel(Elementsarrowverse instance) {
		super(instance, 61);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MCreatorSpeedForceTravel!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((((((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.armorInventory.get(3) : ItemStack.EMPTY).getItem() == new ItemStack(
				MCreatorFlashSuit.helmet, (int) (1)).getItem()) && (((entity instanceof PlayerEntity)
				? ((PlayerEntity) entity).inventory.armorInventory.get(2)
				: ItemStack.EMPTY).getItem() == new ItemStack(MCreatorFlashSuit.body, (int) (1)).getItem())) && ((((entity instanceof PlayerEntity)
				? ((PlayerEntity) entity).inventory.armorInventory.get(1)
				: ItemStack.EMPTY).getItem() == new ItemStack(MCreatorFlashSuit.legs, (int) (1)).getItem()) && (((entity instanceof PlayerEntity)
				? ((PlayerEntity) entity).inventory.armorInventory.get(0)
				: ItemStack.EMPTY).getItem() == new ItemStack(MCreatorFlashSuit.boots, (int) (1)).getItem())))) {
			if (((entity.dimension.getId()) == (0))) {
				if (!entity.world.isRemote && entity instanceof ServerPlayerEntity) {
					DimensionType destinationType = MCreatorSpeedForce.type;
					ObfuscationReflectionHelper.setPrivateValue(ServerPlayerEntity.class, (ServerPlayerEntity) entity, true, "field_184851_cj");
					ServerWorld nextWorld = entity.getServer().getWorld(destinationType);
					((ServerPlayerEntity) entity).connection.sendPacket(new SChangeGameStatePacket(4, 0));
					((ServerPlayerEntity) entity).teleport(nextWorld, nextWorld.getSpawnPoint().getX(), nextWorld.getSpawnPoint().getY() + 1,
							nextWorld.getSpawnPoint().getZ(), entity.rotationYaw, entity.rotationPitch);
					((ServerPlayerEntity) entity).connection.sendPacket(new SPlayerAbilitiesPacket(((ServerPlayerEntity) entity).abilities));
					for (EffectInstance effectinstance : ((ServerPlayerEntity) entity).getActivePotionEffects()) {
						((ServerPlayerEntity) entity).connection.sendPacket(new SPlayEntityEffectPacket(entity.getEntityId(), effectinstance));
					}
					((ServerPlayerEntity) entity).connection.sendPacket(new SPlaySoundEventPacket(1032, BlockPos.ZERO, 0, false));
				}
			} else if (((entity.dimension.getId()) == (MCreatorSpeedForce.type.getId()))) {
				if (!entity.world.isRemote && entity instanceof ServerPlayerEntity) {
					DimensionType destinationType = DimensionType.OVERWORLD;
					ObfuscationReflectionHelper.setPrivateValue(ServerPlayerEntity.class, (ServerPlayerEntity) entity, true, "field_184851_cj");
					ServerWorld nextWorld = entity.getServer().getWorld(destinationType);
					((ServerPlayerEntity) entity).connection.sendPacket(new SChangeGameStatePacket(4, 0));
					((ServerPlayerEntity) entity).teleport(nextWorld, nextWorld.getSpawnPoint().getX(), nextWorld.getSpawnPoint().getY() + 1,
							nextWorld.getSpawnPoint().getZ(), entity.rotationYaw, entity.rotationPitch);
					((ServerPlayerEntity) entity).connection.sendPacket(new SPlayerAbilitiesPacket(((ServerPlayerEntity) entity).abilities));
					for (EffectInstance effectinstance : ((ServerPlayerEntity) entity).getActivePotionEffects()) {
						((ServerPlayerEntity) entity).connection.sendPacket(new SPlayEntityEffectPacket(entity.getEntityId(), effectinstance));
					}
					((ServerPlayerEntity) entity).connection.sendPacket(new SPlaySoundEventPacket(1032, BlockPos.ZERO, 0, false));
				}
			}
		}
	}
}
