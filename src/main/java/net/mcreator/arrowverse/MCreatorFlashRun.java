package net.mcreator.arrowverse;

import net.minecraft.world.World;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

@Elementsarrowverse.ModElement.Tag
public class MCreatorFlashRun extends Elementsarrowverse.ModElement {
	public MCreatorFlashRun(Elementsarrowverse instance) {
		super(instance, 53);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MCreatorFlashRun!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MCreatorFlashRun!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if ((((((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.armorInventory.get(3) : ItemStack.EMPTY).getItem() == new ItemStack(
				MCreatorFlashSuit.helmet, (int) (1)).getItem()) && (((entity instanceof PlayerEntity)
				? ((PlayerEntity) entity).inventory.armorInventory.get(2)
				: ItemStack.EMPTY).getItem() == new ItemStack(MCreatorFlashSuit.body, (int) (1)).getItem())) && ((((entity instanceof PlayerEntity)
				? ((PlayerEntity) entity).inventory.armorInventory.get(1)
				: ItemStack.EMPTY).getItem() == new ItemStack(MCreatorFlashSuit.legs, (int) (1)).getItem()) && (((entity instanceof PlayerEntity)
				? ((PlayerEntity) entity).inventory.armorInventory.get(0)
				: ItemStack.EMPTY).getItem() == new ItemStack(MCreatorFlashSuit.boots, (int) (1)).getItem())))) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SPEED, (int) 1, (int) (arrowverseVariables.WorldVariables
						.get(world).SpeedFactor), (true), (false)));
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SPEED, (int) 1, (int) (((arrowverseVariables.WorldVariables
						.get(world).SpeedFactor) / 3) - 0), (true), (false)));
		}
	}
}
