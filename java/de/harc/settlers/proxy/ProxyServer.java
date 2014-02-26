package de.harc.settlers.proxy;

/**
 * DedicatedServerProxy is used to set up the mod and start it running when
 * installed on a dedicated server.
 * 
 * It should not contain (or refer to) any client-side code at all, since the
 * dedicated server has no client-side code.
 */

public class ProxyServer extends ProxyCommon {

	/**
	 * Do your mod setup. Build whatever data structures you care about. send
	 * FMLInterModComms messages to other mods.
	 */
	@Override
	public void init() {
		super.init();
	}

	/**
	 * Handle interaction with other mods, complete your setup based on this.
	 */
	@Override
	public void postInit() {
		super.postInit();
	}

	/**
	 * Run before anything else. Read your config.
	 */
	@Override
	public void preInit() {
		super.preInit();
	}

}
