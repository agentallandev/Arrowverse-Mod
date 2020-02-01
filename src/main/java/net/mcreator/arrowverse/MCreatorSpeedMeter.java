package net.mcreator.arrowverse;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.client.Minecraft;

import java.util.Collection;

@Elementsarrowverse.ModElement.Tag
public class MCreatorSpeedMeter extends Elementsarrowverse.ModElement {
	public MCreatorSpeedMeter(Elementsarrowverse instance) {
		super(instance, 46);
	}

	@Override
	public void initElements() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void eventHandler(RenderGameOverlayEvent event) {
		if (!event.isCancelable() && event.getType() == RenderGameOverlayEvent.ElementType.HELMET) {
			int posX = (event.getWindow().getScaledWidth()) / 2;
			int posY = (event.getWindow().getScaledHeight()) / 2;
			PlayerEntity entity = Minecraft.getInstance().player;
			World world = entity.world;
			int x = (int) entity.posX;
			int y = (int) entity.posY;
			int z = (int) entity.posZ;
			if ((new Object() {
				boolean check() {
					if (entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == Effects.SPEED)
								return true;
						}
					}
					return false;
				}
			}.check())) {
				Minecraft.getInstance().fontRenderer.drawString("Speed : " + (arrowverseVariables.WorldVariables.get(world).SpeedFactor) + "", posX
						+ -205, posY + -112, -1);
			}
		}
	}
}
