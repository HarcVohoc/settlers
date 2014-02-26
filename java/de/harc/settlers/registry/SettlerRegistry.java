package de.harc.settlers.registry;

import java.util.List;

import com.google.common.collect.Lists;

import de.harc.settlers.entity.EntitySettler;

public class SettlerRegistry {

	private static final SettlerRegistry instance = new SettlerRegistry();

	public static final List<EntitySettler> settlers = Lists.newArrayList();

	public static SettlerRegistry getInstance() {
		return instance;
	}

	private SettlerRegistry() {

	}
}
