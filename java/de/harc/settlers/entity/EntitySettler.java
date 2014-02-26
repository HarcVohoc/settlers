package de.harc.settlers.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;
import de.harc.settlers.entity.ai.EntityAIMoveToPlayer;

/**
 * Settlers are the most basic villagers. They summon at the gathering place and
 * may take any availlabe work.
 * 
 * @author Harc Vohoc
 */
public class EntitySettler extends EntityCreature {

	private static final int AI_MOVE_DISTANCE = 32;
	private static final int AI_UPDATE_PERIOD = 5000;

	private final int periodShift = EntitySettler.AI_UPDATE_PERIOD + (int) (Math.random() * 1000);
	private final long nextUpdateTime = Minecraft.getMinecraft().getSystemTime() + periodShift;
	private final EntityAIMoveToPlayer taskMoveToPlayer = new EntityAIMoveToPlayer(this, 0.5f, EntitySettler.AI_MOVE_DISTANCE);

	public EntitySettler(final World world) {
		super(world);
		getNavigator().setCanSwim(true);
		getNavigator().setAvoidsWater(true);
		getNavigator().setEnterDoors(true);
		getNavigator().setSpeed(1.0f);

		// tasks.addTask(0, taskMoveToPlayer);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
	}

	@Override
	public void setAIMoveSpeed(final float moveSpeed) {
		super.setAIMoveSpeed(moveSpeed);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntitySettler.AI_MOVE_DISTANCE * EntitySettler.AI_MOVE_DISTANCE);
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	@Override
	protected String getLivingSound() {
		return null;
	}

	@Override
	protected boolean isAIEnabled() {
		return true;
	}

	@Override
	protected void updateAITasks() {
		super.updateAITasks();

//		Minecraft.getMinecraft();
//		// a test running ai tasks not that often
//		long currentTime = Minecraft.getSystemTime();
//		if (currentTime > nextUpdateTime) {
//			nextUpdateTime = currentTime + periodShift;
//			System.out.println("EntityWorker[" + getEntityId() + "] >>> updateAITasks()");
////			taskMoveToPlayer.setTargetEntity(Minecraft.getMinecraft().thePlayer);
//		}
	}

	@Override
	protected void updateAITick() {
		// no-op
	}

}
