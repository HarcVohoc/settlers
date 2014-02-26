package de.harc.settlers.registry;

import java.util.List;

import net.minecraft.util.ChunkCoordinates;

import com.google.common.collect.Lists;

import de.harc.settlers.building.gathering.BuildingGatheringPlace;

public class BuildingRegistry {

	private static final BuildingRegistry instance = new BuildingRegistry();

	public static final List<BuildingGatheringPlace> gatheringPlaces = Lists.newArrayList();

	/**
	 * Returns the closes gathering place related to the provided
	 * {@code coordinate}.
	 * 
	 * @param coordinate
	 *            to find closest gathering place for
	 * @return the closest gathering place or {@code null}
	 */
	public static BuildingGatheringPlace getClosestGatheringPlace(final ChunkCoordinates coordinate) {
		float closestDistance = Float.MAX_VALUE;
		BuildingGatheringPlace closestGatheringPlace = null;
		for (BuildingGatheringPlace gatheringPlace : gatheringPlaces) {
			float distance = gatheringPlace.getCenter().getDistanceSquaredToChunkCoordinates(coordinate);
			if (distance < closestDistance) {
				closestDistance = distance;
				closestGatheringPlace = gatheringPlace;
			}
		}
		return closestGatheringPlace;
	}

	public static BuildingRegistry getInstance() {
		return instance;
	}

	private BuildingRegistry() {

	}

}
