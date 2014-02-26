package de.harc.settlers.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIMoveToPlayer extends EntityAIBase {

	/**
	 * If the distance to the target entity is further than this, this AI task
	 * will not run.
	 */
	private final float maxTargetDistance;
	private final float maxTargetDistanceSq;
	private double movePosX;
	private double movePosY;
	private double movePosZ;
	private final double speed;

	private EntityLivingBase targetEntity;
	private final EntityCreature theEntity;

	public EntityAIMoveToPlayer(final EntityCreature theEntity, final double speed, final float maxTargetDistance) {
		this.theEntity = theEntity;
		this.speed = speed;
		this.maxTargetDistance = maxTargetDistance;
		maxTargetDistanceSq = maxTargetDistance * maxTargetDistance;
		setMutexBits(1);
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	@Override
	public boolean continueExecuting() {
		boolean hasPath = !theEntity.getNavigator().noPath();
		boolean isTargetAlive = targetEntity.isEntityAlive();
		double distanceSqToEntity = targetEntity.getDistanceSqToEntity(theEntity);
		return hasPath && isTargetAlive && distanceSqToEntity < maxTargetDistanceSq;
	}

	/**
	 * Resets the task
	 */
	@Override
	public void resetTask() {
		targetEntity = null;
	}

	/**
	 * Sets the target entity for the entity to move to
	 * 
	 * @param targetEntity
	 */
	public void setTargetEntity(final EntityLivingBase targetEntity) {
		this.targetEntity = targetEntity;
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	@Override
	public boolean shouldExecute() {

		if (targetEntity == null) {
			return false;
		}
		else if (targetEntity.getDistanceSqToEntity(theEntity) > maxTargetDistance * maxTargetDistance) {
			return false;
		}

		movePosX = targetEntity.posX;
		movePosY = targetEntity.posY;
		movePosZ = targetEntity.posZ;

		return true;
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	@Override
	public void startExecuting() {
		if (!theEntity.getNavigator().tryMoveToXYZ(movePosX, movePosY, movePosZ, speed)) {
			System.out.println("Problems finding path ... ");
		}
	}

}
