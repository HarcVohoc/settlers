package de.harc.settlers;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SettlersUtil {

	@SideOnly(Side.CLIENT)
	public static void chatMessage(final String message) {
		Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
	}

	public static ChunkCoordinates getFreeSpawnPlaceOnTopOf(final World world, final int x, final int y, final int z) {
		int checkY = y;
		Block block = world.getBlock(x, checkY, z);
		boolean up = !(block instanceof BlockAir);
		if (up) {
			while (!(block instanceof BlockAir) && checkY < 512) {
				block = world.getBlock(x, checkY++, z);
			}
		}
		else {
			while (block instanceof BlockAir && checkY > 0) {
				block = world.getBlock(x, checkY--, z);
			}
		}
		return new ChunkCoordinates(x, checkY, z);
	}

	public static EntityVillager spawnSettler(final World world, final int x, final int y, final int z) {
		// EntitySettler settler = new EntitySettler(world);
		EntityVillager settler = new EntityVillager(world);
		ChunkCoordinates topPosition = getFreeSpawnPlaceOnTopOf(world, x, y, z);
		settler.setPosition(topPosition.posX, topPosition.posY, topPosition.posZ);
		world.spawnEntityInWorld(settler);
		return settler;
	}

}
