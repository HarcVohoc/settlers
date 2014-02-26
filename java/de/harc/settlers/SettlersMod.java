package de.harc.settlers;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

import com.google.common.collect.Lists;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import de.harc.settlers.building.gathering.BuildingGatheringPlace;
import de.harc.settlers.entity.EntitySettler;
import de.harc.settlers.event.WorldTickEventHandler;
import de.harc.settlers.proxy.ProxyCommon;
import de.harc.settlers.renderer.entity.RendererSettler;

@Mod(modid = SettlersMod.MODID, version = SettlersMod.VERSION)
public class SettlersMod {

	public static final String MODID = "settlers";
	public static final String VERSION = "0.1";

	/*
	 * The proxy reference will be set to either ProxyClient or
	 * DedicatedServerProxy depending on whether this mod is running in a normal
	 * Minecraft client or a dedicated server.
	 * 
	 * NB this is not the same as client-side vs server-side: ProxyClient
	 * contains both client-side code and server-side code (used by the
	 * integrated server) ProxyServer doesn't contain any client-side code
	 */
	@SidedProxy(clientSide = "de.harc.settlers.proxy.ProxyClient", serverSide = "de.harc.settlers.proxy.ProxyServer")
	public static ProxyCommon proxy;

	public static CreativeTabs tabSettlers = new CreativeTabs("tabSettlers") {
		@Override
		public Item getTabIconItem() {
			return Items.iron_axe;
		}
	};

	public static Block gatheringBlock;
	public static List<BuildingGatheringPlace> list = Lists.newArrayList();

	@EventHandler
	public void init(final FMLInitializationEvent event) {
		proxy.init();

		MinecraftForge.EVENT_BUS.register(new WorldTickEventHandler());
	}

	/**
	 * Handle interaction with other mods, complete your setup based on this.
	 */
	@EventHandler
	public void postInit(final FMLPostInitializationEvent event) {
		proxy.postInit();
	}

	@EventHandler
	public void preInit(final FMLPreInitializationEvent event) {
		proxy.preInit();

		EntityRegistry.registerModEntity(EntitySettler.class, "Worker", 0, this, 80, 1, true);
		RenderingRegistry.registerEntityRenderingHandler(EntitySettler.class, new RendererSettler());
	}

}