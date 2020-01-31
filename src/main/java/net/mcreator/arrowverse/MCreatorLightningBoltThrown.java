
package net.mcreator.arrowverse;

@Elementsarrowverse.ModElement.Tag
public class MCreatorLightningBoltThrown extends Elementsarrowverse.ModElement{

	@ObjectHolder("arrowverse:lightningboltthrown")
	public static final Item block = null;

	@ObjectHolder("arrowverse:entitybulletlightningboltthrown")
	public static final EntityType arrow = null;

	public MCreatorLightningBoltThrown(Elementsarrowverse instance) {
		super(instance, 49);
	}

	@Override public void initElements() {
		elements.items.add(() -> new ItemRanged());
		elements.entities.add(() -> (EntityType.Builder.<ArrowCustomEntity>create(ArrowCustomEntity::new, EntityClassification.MISC)
					.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory(ArrowCustomEntity::new)
					.size(0.5f, 0.5f)).build("entitybulletlightningboltthrown").setRegistryName("entitybulletlightningboltthrown"));
	}

	@Override @OnlyIn(Dist.CLIENT) public void init(FMLCommonSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(ArrowCustomEntity.class, renderManager -> {
			return new CustomRender(renderManager);
		});
	}

	public static class ItemRanged extends Item {

		public ItemRanged() {
			super(new Item.Properties()
					.group(MCreatorArrowverseCoreTab.tab)
					.maxDamage(100)
					);

			setRegistryName("lightningboltthrown");
		}

		@Override public UseAction getUseAction(ItemStack stack) {
      		return UseAction.BOW;
   		}

		@Override public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity entity, Hand hand) {
			entity.setActiveHand(hand);
			return new ActionResult(ActionResultType.SUCCESS, entity.getHeldItem(hand));
		}

		@Override public int getUseDuration(ItemStack itemstack) {
			return 72000;
		}



			@Override
			public void onPlayerStoppedUsing(ItemStack itemstack, World world, LivingEntity entityLiving, int timeLeft) {
				if (!world.isRemote && entityLiving instanceof ServerPlayerEntity) {
					ServerPlayerEntity entity = (ServerPlayerEntity) entityLiving;
	float power = 1f;
	ArrowCustomEntity entityarrow = new ArrowCustomEntity(arrow, entity, world);
	entityarrow.shoot(entity.getLookVec().x, entity.getLookVec().y, entity.getLookVec().z, power * 2, 0);
	entityarrow.setSilent(true);
	entityarrow.setIsCritical(false);
	entityarrow.setDamage(5);
	entityarrow.setKnockbackStrength(5);

	itemstack.damageItem(1, entity, e -> e.sendBreakAnimation(entity.getActiveHand()));

	int x = (int) entity.posX;
	int y = (int) entity.posY;
	int z = (int) entity.posZ;
	world.playSound((PlayerEntity) null, (double) x, (double) y, (double) z, (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
		.getValue(new ResourceLocation("entity.lightning_bolt.impact")), SoundCategory.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));

	entityarrow.pickupStatus = AbstractArrowEntity.PickupStatus.DISALLOWED;

	world.addEntity(entityarrow);


				}
			}

	}

	@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
	public static class ArrowCustomEntity extends AbstractArrowEntity implements IRendersAsItem {

		public ArrowCustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
        	super(arrow, world);
    	}

		public ArrowCustomEntity(EntityType<? extends ArrowCustomEntity> type, World world) {
			super(type, world);
		}

		public ArrowCustomEntity(EntityType<? extends ArrowCustomEntity> type, double x, double y, double z, World world) {
			super(type, x, y, z, world);
		}

		public ArrowCustomEntity(EntityType<? extends ArrowCustomEntity> type, LivingEntity entity, World world) {
			super(type, entity, world);
		}

		@Override public IPacket<?> createSpawnPacket() {
        	return NetworkHooks.getEntitySpawningPacket(this);
    	}

		@Override @OnlyIn(Dist.CLIENT) public ItemStack getItem() {
			return new ItemStack(MCreatorLightningBoltCrafting.block, (int)(1));
		}

		@Override protected ItemStack getArrowStack() {
			return null;
		}


		@Override protected void arrowHit(LivingEntity entity) {
			super.arrowHit(entity);
			entity.setArrowCountInEntity(entity.getArrowCountInEntity() - 1); 
		}

		@Override public void tick() {
			super.tick();
			int x = (int) this.posX;
			int y = (int) this.posY;
			int z = (int) this.posZ;
			World world = this.world;
			Entity entity = this.getShooter();
			if (this.inGround) {
				this.remove();
			}
		}

	}

		public static class CustomRender extends EntityRenderer<ArrowCustomEntity> {
			private static final ResourceLocation texture = new ResourceLocation("arrowverse:textures/lightning_bolt.png");

			public CustomRender(EntityRendererManager renderManager) {
				super(renderManager);
			}

			@Override public void doRender(ArrowCustomEntity bullet, double d, double d1, double d2, float f, float f1) {
				this.bindEntityTexture(bullet);
				GlStateManager.pushMatrix();
				GlStateManager.translatef((float) d, (float) d1, (float) d2);
				GlStateManager.rotatef(f, 0, 1, 0);
				GlStateManager.rotatef(90f - bullet.prevRotationPitch - (bullet.rotationPitch - bullet.prevRotationPitch) * f1, 1, 0, 0);
				EntityModel model = new Modelcustom_model();
				model.render(bullet, 0, 0, 0, 0, 0, 0.0625f);
				GlStateManager.popMatrix();
			}

			@Override protected ResourceLocation getEntityTexture(ArrowCustomEntity entity) {
				return texture;
			}
		}

	//Made with Blockbench
//Paste this code into your mod.

import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.RendererModel;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class custom_model extends EntityModel<Entity> {
	private final RendererModel bone;

	public custom_model() {
		textureWidth = 16;
		textureHeight = 16;

		bone = new RendererModel(this);
		bone.setRotationPoint(0.049F, 22.5F, 0.0294F);
		setRotationAngle(bone, 3.1416F, 0.6981F, -1.5708F);
		bone.cubeList.add(new ModelBox(bone, 0, 0, 0.951F, -0.5F, -2.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, 0.951F, 0.5F, -2.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, 0.951F, -1.5F, -2.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, 1.951F, -0.5F, -2.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, 1.951F, -0.5F, -1.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, 1.951F, -0.5F, -0.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, 2.951F, -0.5F, -0.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, 2.951F, -0.5F, 0.9706F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, 0.951F, -0.5F, -1.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, 0.951F, 0.5F, -1.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, 0.951F, -1.5F, -1.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, 0.951F, -0.5F, -0.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, 0.951F, 0.5F, -0.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, 0.951F, -1.5F, -0.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -0.049F, -0.5F, -0.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, 1.951F, -0.5F, 0.9706F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, 1.951F, 0.5F, 0.9706F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, 1.951F, -1.5F, 0.9706F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, 0.951F, -0.5F, 0.9706F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, 2.951F, -0.5F, 1.9706F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, 2.951F, 0.5F, 1.9706F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, 2.951F, -1.5F, 1.9706F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, 2.951F, -0.5F, 2.9706F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, 3.951F, -0.5F, 2.9706F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, 3.951F, -0.5F, 1.9706F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, 1.951F, -0.5F, 1.9706F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -0.049F, -0.5F, -3.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -0.049F, 0.5F, -3.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -0.049F, -1.5F, -3.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, 0.951F, -0.5F, -3.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -1.049F, -0.5F, -3.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -1.049F, -0.5F, -4.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -1.049F, 0.5F, -4.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -1.049F, -1.5F, -4.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -0.049F, -0.5F, -4.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -2.049F, -0.5F, -4.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -2.049F, -0.5F, -5.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -2.049F, 0.5F, -5.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -2.049F, -1.5F, -5.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -1.049F, -0.5F, -5.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -3.049F, -0.5F, -5.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -3.049F, -0.5F, -6.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -2.049F, -0.5F, -6.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -0.049F, -0.5F, -2.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -0.049F, 0.5F, -2.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -0.049F, -1.5F, -2.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -1.049F, -0.5F, -2.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -0.049F, -0.5F, -1.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -0.049F, 0.5F, -1.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -0.049F, -1.5F, -1.0294F, 1, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -1.049F, -0.5F, -1.0294F, 1, 1, 1, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone.render(f5);
	}
	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}


}

