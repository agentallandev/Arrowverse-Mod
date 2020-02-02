package net.mcreator.arrowverse;

import net.minecraft.world.World;

@Elementsarrowverse.ModElement.Tag
public class MCreatorRemoveSpeedster extends Elementsarrowverse.ModElement {
	public MCreatorRemoveSpeedster(Elementsarrowverse instance) {
		super(instance, 90);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MCreatorRemoveSpeedster!");
			return;
		}
		World world = (World) dependencies.get("world");
		arrowverseVariables.MapVariables.get(world).Speedster = (boolean) (false);
		arrowverseVariables.MapVariables.get(world).syncData(world);
	}
}
