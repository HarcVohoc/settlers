package de.harc.settlers.proxy;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import de.harc.settlers.SettlersMod;
import de.harc.settlers.block.BlockGatheringStone;

/**
 * CommonProxy is used to set up the mod and start it running. It contains all
 * the code that should run on both the normal Minecraft client and the
 * dedicated server.
 */
public class ProxyCommon {

	/**
	 * Do your mod setup. Build whatever data structures you care about.
	 * Register recipes, send FMLInterModComms messages to other mods.
	 */
	public void init() {
		// register event handlers

		// register my Recipies

		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.farmland, 1), Blocks.dirt, Items.wooden_hoe);
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.anvil, 1), "XXX", " X ", "XXX", 'X', Items.iron_ingot);
		GameRegistry.addShapedRecipe(new ItemStack(SettlersMod.gatheringBlock, 9), "XXX", "XYX", "XXX", 'X', Blocks.cobblestone, 'Y', Items.redstone);
	}

	/**
	 * Handle interaction with other mods, complete your setup based on this.
	 */
	public void postInit() {
	}

	/**
	 * Run before anything else. Read your config, create blocks, items, etc,
	 * and register them with the GameRegistry
	 */
	public void preInit() {
		// register my Items, Blocks, Entities, etc
		SettlersMod.gatheringBlock = new BlockGatheringStone().setBlockTextureName("bedrock").setCreativeTab(SettlersMod.tabSettlers);
		GameRegistry.registerBlock(SettlersMod.gatheringBlock, "gathering");
	}

}
