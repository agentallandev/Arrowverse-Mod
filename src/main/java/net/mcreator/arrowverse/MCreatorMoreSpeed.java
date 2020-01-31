package net.mcreator.arrowverse;

import net.minecraft.world.World;

@Elementsarrowverse.ModElement.Tag
public class MCreatorMoreSpeed extends Elementsarrowverse.ModElement {
	public MCreatorMoreSpeed(Elementsarrowverse instance) {
		super(instance, 54);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MCreatorMoreSpeed!");
			return;
		}
		World world = (World) dependencies.get("world");
		if ((((arrowverseVariables.WorldVariables.get(world).SpeedFactor) < 49) && ((arrowverseVariables.WorldVariables.get(world).SpeedFactor) >= 0))) {
			arrowverseVariables.WorldVariables.get(world).SpeedFactor = (double) ((arrowverseVariables.WorldVariables.get(world).SpeedFactor) + 1);
			arrowverseVariables.WorldVariables.get(world).syncData(world);
		}
	}
}
