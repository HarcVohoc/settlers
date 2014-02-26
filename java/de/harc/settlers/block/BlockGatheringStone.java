package de.harc.settlers.block;

import java.text.MessageFormat;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import de.harc.settlers.SettlersUtil;
import de.harc.settlers.building.gathering.BuildingGatheringPlace;
import de.harc.settlers.registry.BuildingRegistry;

public class BlockGatheringStone extends Block {

	public BlockGatheringStone() {
		super(Material.rock);
		setBlockName("gathering");
		setStepSound(soundTypeStone);
		setHardness(4f);
		setResistance(5f);
	}

	@Override
	public void onBlockAdded(final World world, final int x, final int y, final int z) {
		super.onBlockAdded(world, x, y, z);
//		SettlersUtils.chatMessage("Gathering Place Block added");
//		SettlersUtils.spawnSettler(world, x, y + 1, z);

		BuildingGatheringPlace closestGatheringPlace = BuildingRegistry.getClosestGatheringPlace(new ChunkCoordinates(x, y, z));
		BuildingGatheringPlace gatheringPlace = null;

		// add block to existing gathering place
		if (closestGatheringPlace != null) {
			float outerRadius = closestGatheringPlace.getRadius();
			float outerRadiusSq = outerRadius * outerRadius;
			float distance = closestGatheringPlace.getCenter().getDistanceSquared(x, y, z);
			if (distance <= outerRadiusSq) {
				gatheringPlace = closestGatheringPlace;
				closestGatheringPlace.addBlock(x, y, z);
				SettlersUtil.chatMessage(MessageFormat.format("Gathering Place Block added [{0},{1},{2}]", x, y, z));
			}
		}

		// create new Gathering Place
		if (gatheringPlace == null) {
			BuildingGatheringPlace buildingGatheringPlace = new BuildingGatheringPlace();
			buildingGatheringPlace.setCenter(x, y, z);
			BuildingRegistry.gatheringPlaces.add(buildingGatheringPlace);
			SettlersUtil.chatMessage("Create new Gathering Place");
		}

	}

	@Override
	public void onBlockDestroyedByPlayer(final World world, final int x, final int y, final int z, final int metaData) {
		super.onBlockDestroyedByPlayer(world, x, y, z, metaData);
	}

	@Override
	public int onBlockPlaced(final World world, final int x, final int y, final int z, final int side, final float hitX, final float hitY, final float hitZ, final int metaData) {
		return super.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, metaData);
	}

	@Override
	public void onNeighborBlockChange(final World world, final int x, final int y, final int z, final Block neighbor) {
		super.onNeighborBlockChange(world, x, y, z, neighbor);
		if (neighbor instanceof BlockGatheringStone) {
			SettlersUtil.chatMessage("Lost a neighbor");
		}
	}

}
