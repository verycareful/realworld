package com.fury.realworld.config;

import com.fury.realworld.Realworld;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * User configuration, persisted as {@code config/realworld.json}.
 *
 * <p>The file is created with defaults on first launch. A non-positive
 * multiplier is treated as corrupt and reset, since the octave arithmetic in
 * {@link #getOctaveOffset()} takes its logarithm.
 */
public class RealworldConfig {
	/** Vanilla's Large Biomes preset scales biomes by this factor over default worlds. */
	public static final double VANILLA_LARGE_BIOMES_MULTIPLIER = 4.0;

	/** The {@code firstOctave} shift that produces {@link #VANILLA_LARGE_BIOMES_MULTIPLIER}. */
	public static final int VANILLA_LARGE_BIOMES_OCTAVE_OFFSET = -2;

	public static final double DEFAULT_MULTIPLIER = 16.0;

	private static final String FILE_NAME = "realworld.json";
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve(FILE_NAME);

	private static RealworldConfig instance;

	public double biomeScaleMultiplier = DEFAULT_MULTIPLIER;
	public String description = "Scale multiplier for the Large Biomes world type. Higher values give larger biomes. "
			+ "Vanilla Large Biomes = " + VANILLA_LARGE_BIOMES_MULTIPLIER + "x. Default = " + DEFAULT_MULTIPLIER + "x.";
	public String howItWorks = "Each doubling of the multiplier lowers every large-biome noise's firstOctave by one. "
			+ "Vanilla uses " + VANILLA_LARGE_BIOMES_OCTAVE_OFFSET + " (" + VANILLA_LARGE_BIOMES_MULTIPLIER + "x).";

	public static RealworldConfig getInstance() {
		if (instance == null) {
			instance = load();
		}
		return instance;
	}

	/**
	 * Octave offset equivalent to the configured multiplier. Each doubling of
	 * scale is one octave lower, so the offset is vanilla's minus
	 * {@code log2(multiplier / vanilla)}, rounded. Multipliers at or below
	 * vanilla's clamp to vanilla's offset.
	 */
	public int getOctaveOffset() {
		if (biomeScaleMultiplier <= VANILLA_LARGE_BIOMES_MULTIPLIER) {
			return VANILLA_LARGE_BIOMES_OCTAVE_OFFSET;
		}
		double ratio = biomeScaleMultiplier / VANILLA_LARGE_BIOMES_MULTIPLIER;
		int additionalOffset = (int) Math.round(Math.log(ratio) / Math.log(2.0));
		return VANILLA_LARGE_BIOMES_OCTAVE_OFFSET - additionalOffset;
	}

	private static RealworldConfig load() {
		RealworldConfig config;
		if (Files.exists(CONFIG_PATH)) {
			try {
				config = GSON.fromJson(Files.readString(CONFIG_PATH), RealworldConfig.class);
				Realworld.LOGGER.info("Loaded config from {}", CONFIG_PATH);
			} catch (IOException e) {
				Realworld.LOGGER.error("Failed to load config, using defaults", e);
				config = new RealworldConfig();
			}
		} else {
			config = new RealworldConfig();
			config.save();
			Realworld.LOGGER.info("Created default config at {}", CONFIG_PATH);
		}

		if (config.biomeScaleMultiplier <= 0.0) {
			Realworld.LOGGER.warn("Invalid biomeScaleMultiplier ({}), resetting to {}",
					config.biomeScaleMultiplier, DEFAULT_MULTIPLIER);
			config.biomeScaleMultiplier = DEFAULT_MULTIPLIER;
			config.save();
		}
		return config;
	}

	public void save() {
		try {
			Files.createDirectories(CONFIG_PATH.getParent());
			Files.writeString(CONFIG_PATH, GSON.toJson(this));
			Realworld.LOGGER.info("Saved config to {}", CONFIG_PATH);
		} catch (IOException e) {
			Realworld.LOGGER.error("Failed to save config", e);
		}
	}
}
