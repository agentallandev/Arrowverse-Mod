package net.mcreator.arrowverse;

import net.minecraft.world.World;

@Elementsarrowverse.ModElement.Tag
public class MCreatorSetSpeedster extends Elementsarrowverse.ModElement {
	public MCreatorSetSpeedster(Elementsarrowverse instance) {
		super(instance, 89);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MCreatorSetSpeedster!");
			return;
		}
		World world = (World) dependencies.get("world");
		arrowverseVariables.MapVariables.get(world).Speedster = (boolean) (true);
		arrowverseVariables.MapVariables.get(world).syncData(world);
	}
}
