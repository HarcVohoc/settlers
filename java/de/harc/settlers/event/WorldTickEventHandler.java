package de.harc.settlers.event;

import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
import de.harc.settlers.building.gathering.BuildingGatheringPlace;
import de.harc.settlers.registry.BuildingRegistry;

public class WorldTickEventHandler {

	private long timeNextUpdate;
	private final long timePeriodShift = 5000;

	@SubscribeEvent
	public void onWorldTickEvent(final WorldTickEvent event) {
		long currentTime = Minecraft.getSystemTime();
		if (currentTime > timeNextUpdate) {
			timeNextUpdate = currentTime + timePeriodShift;
			for (BuildingGatheringPlace gatheringPlace : BuildingRegistry.gatheringPlaces) {
				if (gatheringPlace.canSpawnSettler()) {
					gatheringPlace.spawnSettler();
				}
			}
		}
	}

}
