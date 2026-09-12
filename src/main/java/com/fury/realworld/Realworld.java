package com.fury.realworld;

import com.fury.realworld.command.RealworldCommands;
import com.fury.realworld.config.RealworldConfig;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entry point. The mod's effect on world generation comes entirely from the
 * bundled datapack under {@code data/minecraft/worldgen/noise/}, which Fabric
 * loads as a built-in resource pack; nothing here touches worldgen directly.
 */
public class Realworld implements ModInitializer {
	public static final String MOD_ID = "realworld";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		RealworldConfig config = RealworldConfig.getInstance();
		RealworldCommands.register();

		LOGGER.info("Realworld initialised");
		LOGGER.info("Biome scale multiplier: {}x (vanilla Large Biomes = {}x)",
				config.biomeScaleMultiplier, RealworldConfig.VANILLA_LARGE_BIOMES_MULTIPLIER);
		LOGGER.info("Octave offset: {} (vanilla Large Biomes = {})",
				config.getOctaveOffset(), RealworldConfig.VANILLA_LARGE_BIOMES_OCTAVE_OFFSET);
		LOGGER.info("Select the 'Large Biomes' world type to use this mod");
	}
}
