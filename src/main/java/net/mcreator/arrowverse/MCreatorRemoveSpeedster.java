package net.mcreator.arrowverse;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

@Elementsarrowverse.ModElement.Tag
public class MCreatorRemoveSpeedster extends Elementsarrowverse.ModElement {
	public MCreatorRemoveSpeedster(Elementsarrowverse instance) {
		super(instance, 90);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MCreatorRemoveSpeedster!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MCreatorRemoveSpeedster!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if (((arrowverseVariables.MapVariables.get(world).Speedster) == (true))) {
			arrowverseVariables.MapVariables.get(world).Speedster = (boolean) (false);
			arrowverseVariables.MapVariables.get(world).syncData(world);
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).inventory.clearMatchingItems(
						p -> new ItemStack(MCreatorEmptySyringe.block, (int) (1)).getItem() == p.getItem(), (int) 1);
			if (entity instanceof PlayerEntity)
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), new ItemStack(MCreatorSpeedForceSyringe.block, (int) (1)));
		}
	}
}
