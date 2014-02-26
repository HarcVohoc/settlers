package de.harc.settlers.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import de.harc.settlers.event.WorldTickEventHandler;

/**
 * CombinedClient is used to set up the mod and start it running when installed
 * on a normal minecraft client.
 * 
 * It should not contain any code necessary for proper operation on a
 * DedicatedServer. Code required for both normal minecraft client and dedicated
 * server should go into CommonProxy
 */
public class ProxyClient extends ProxyCommon {

	/**
	 * Do your mod setup. Build whatever data structures you care about.
	 * Register recipes, send FMLInterModComms messages to other mods.
	 */
	@Override
	public void init() {
		super.init();
		// register my event handler

		// register my Recipies
	}

	@SubscribeEvent
	public void onRenderTick(final TickEvent.RenderTickEvent tick) {
		if (tick.phase == Phase.END) {
			System.out.println("tic");
		}
	}

	@SubscribeEvent
	public void onWorldTick(final TickEvent.PlayerTickEvent tick) {
		if (tick.phase == Phase.END) {
			System.out.println("toc");
		}
	}

	@SubscribeEvent
	public void onWorldTick(final TickEvent.WorldTickEvent tick) {
		if (tick.phase == Phase.END) {
			System.out.println("tac");
		}
	}

	/**
	 * Handle interaction with other mods, complete your setup based on this.
	 */
	@Override
	public void postInit() {
		super.postInit();
	}

	/**
	 * Run before anything else. Read your config, create blocks, items, etc,
	 * and register them with the GameRegistry
	 */
	@Override
	public void preInit() {
		super.preInit();
		FMLCommonHandler.instance().bus().register(new WorldTickEventHandler());
		// register my Items, Blocks, Entities, etc
	}

}
