package de.harc.settlers.building.gathering;

import java.util.HashMap;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.ChunkCoordinates;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.harc.settlers.SettlersUtil;
import de.harc.settlers.block.BlockGatheringStone;

/**
 * A gathering place is the starting building of every settlement. New workers
 * will spawn at its center and materials are brought or send from here.
 * 
 * @author Harc Vohoc
 */
public class BuildingGatheringPlace {

	/**
	 * This is the gathering place center
	 */
	private ChunkCoordinates center = new ChunkCoordinates(0, 0, 0);
	private float radius = 5;
	private int maxSettlers;
	private final HashMap<ChunkCoordinates, BlockGatheringStone> blocks = Maps.newHashMap();
	private long timeNextUpdate;
	private final long timePeriodShift = 5000;
	private final List<EntityLiving> settlers = Lists.newArrayList();

	public BuildingGatheringPlace() {
		updateMaxSettlers();
	}

	public BuildingGatheringPlace(final int x, final int y, final int z) {
		this();
		setCenter(x, y, z);
	}

	public void addBlock(final int x, final int y, final int z) {

	}

	public boolean canSpawnSettler() {
		if (settlers.size() < maxSettlers) {
			return true;
		}
		return false;
	}

	public ChunkCoordinates getCenter() {
		return center;
	}

	public float getRadius() {
		return radius;
	}

	public void recalculateCenter() {

	}

	public void setCenter(final ChunkCoordinates center) {
		assert center != null;
		this.center = center;
	}

	public void setCenter(final int x, final int y, final int z) {
		center.posX = x;
		center.posY = y;
		center.posZ = z;
	}

	public void setRadius(final float radius) {
		assert radius > 0;
		this.radius = radius;
		updateMaxSettlers();
	}

	public void spawnSettler() {
		int x = (int) (Math.random() * radius * Math.signum(Math.random() - 0.5) + getCenter().posX);
		int y = getCenter().posY;
		int z = (int) (Math.random() * radius * Math.signum(Math.random() - 0.5) + getCenter().posZ);
		EntityVillager settler = SettlersUtil.spawnSettler(Minecraft.getMinecraft().theWorld, x, y, z);
		settlers.add(settler);
	}

	public void updateMaxSettlers() {
		maxSettlers = (int) (radius * 2);
	}

}
