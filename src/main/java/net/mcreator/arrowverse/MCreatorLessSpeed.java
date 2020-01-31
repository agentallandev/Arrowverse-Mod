package net.mcreator.arrowverse;

import net.minecraft.world.World;

@Elementsarrowverse.ModElement.Tag
public class MCreatorLessSpeed extends Elementsarrowverse.ModElement {
	public MCreatorLessSpeed(Elementsarrowverse instance) {
		super(instance, 55);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MCreatorLessSpeed!");
			return;
		}
		World world = (World) dependencies.get("world");
		if ((((arrowverseVariables.WorldVariables.get(world).SpeedFactor) < 49) && ((arrowverseVariables.WorldVariables.get(world).SpeedFactor) >= 0))) {
			arrowverseVariables.WorldVariables.get(world).SpeedFactor = (double) ((arrowverseVariables.WorldVariables.get(world).SpeedFactor) - 1);
			arrowverseVariables.WorldVariables.get(world).syncData(world);
		}
	}
}
