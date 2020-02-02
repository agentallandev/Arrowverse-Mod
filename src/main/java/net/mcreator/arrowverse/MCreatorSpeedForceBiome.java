package net.mcreator.arrowverse;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.Biome;

@Elementsarrowverse.ModElement.Tag
public class MCreatorSpeedForceBiome extends Elementsarrowverse.ModElement {
	@ObjectHolder("arrowverse:speedforcebiome")
	public static final CustomBiome biome = null;

	public MCreatorSpeedForceBiome(Elementsarrowverse instance) {
		super(instance, 70);
	}

	@Override
	public void initElements() {
		elements.biomes.add(() -> new CustomBiome());
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
	}

	static class CustomBiome extends Biome {
		public CustomBiome() {
			super(new Biome.Builder()
					.downfall(0f)
					.depth(0f)
					.scale(0f)
					.temperature(0f)
					.precipitation(Biome.RainType.NONE)
					.category(Biome.Category.NONE)
					.waterColor(4159204)
					.waterFogColor(329011)
					.surfaceBuilder(
							SurfaceBuilder.DEFAULT,
							new SurfaceBuilderConfig(MCreatorSpeedForceBlock.block.getDefaultState(), MCreatorSpeedForceOre.block.getDefaultState(),
									MCreatorSpeedForceOre.block.getDefaultState())));
			setRegistryName("speedforcebiome");
			DefaultBiomeFeatures.addCarvers(this);
			DefaultBiomeFeatures.addStructures(this);
			DefaultBiomeFeatures.addMonsterRooms(this);
			DefaultBiomeFeatures.addOres(this);
		}
	}
}
