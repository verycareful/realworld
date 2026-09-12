# Changelog

All notable changes to Realworld are documented here.

The format follows [Keep a Changelog](https://keepachangelog.com/en/1.1.0/). Versions are four-part, `<line>.<major>.<minor>.<patch>`, where the first component names the Minecraft line: `1` for 1.21, `2` for 26.2. The two lines move in lockstep on the remaining components, so a change to shared content (datapack, config, docs) appears in both. Fabric Loader's semver parser accepts any number of components, so dependency ranges work on these as normal.

## [Unreleased]

Two versions in preparation, one per line: `2.1.2.0` on `main` (Minecraft 26.2) and `1.1.2.0` on the `1.21` branch. Both are the first builds from source.

### Added

- `/realworld locate <biome> [radius] [step]`, on the 26.2 line only. Vanilla's `/locate biome` hardcodes a 6400 block radius, which at 16x scaling covers roughly what 400 blocks covers unscaled. Defaults to 64000 blocks at step 64, accepts a radius up to 10,000,000. Ported from Terramax.
- Source repository. The mod previously existed only as a built jar.
- GitHub Actions workflow building on Ubuntu.
- README, this changelog, and a `LICENSE.txt`.

### Changed

- Relicensed from CC0-1.0 to Apache-2.0.
- Version scheme is now four-part (see the note at the top). The 26.2 line begins at `2.1.2.0` to sit level with the 1.21 line rather than restarting.
- Config class rewritten from the decompiled output into maintainable source: named constants for the vanilla multiplier, vanilla octave offset and default, and cleaner file handling. Behaviour is unchanged, including the on-disk format of `config/realworld.json`.
- 26.2 line depends on Fabric Loader 0.19.3, Fabric API, and Java 25, and declares `minecraft >=26.2`.
- 1.21 line declares `minecraft >=1.21 <26`. The upper bound exists so the two lines never both claim the same game version.

### Removed

- `weirdness_large.json` override. No such vanilla noise exists in any 1.21 or 26.x version, so the file never did anything.
- `Realworld.id()`. Never called. With it gone the 1.21 line contains no Minecraft symbols at all, which is what makes its wide version range safe rather than lucky.

### Known

- `biomeScaleMultiplier` in the config is read and logged but not applied. The four noise overrides are fixed at 16x. Tracked in the issue tracker.

## [1.1.1.0] - 2026-09-11 21:06 IST

A hand-patched jar, never published. Superseded by `1.1.2.0`. Recorded because it is where the version range was widened.

### Changed

- `minecraft` dependency widened from `~1.21.0` (which resolves to `>=1.21.0 <1.22.0`) to `>=1.21`. Verified against Minecraft 26.2 that all four live noise overrides still exist and are still referenced by the Large Biomes preset.
- `fabric-api` moved from `depends` to `suggests`. The mod uses only Fabric Loader (`ModInitializer`, `FabricLoader`), confirmed against the compiled class constant pools, so the hard dependency blocked loading on new game versions for nothing.

## [1.1.0.0] - 2025-10-17

The original release, shipped under the version string `1.0.0` against Minecraft 1.21.8. Time of day not recorded.

### Added

- Datapack overrides for `continentalness_large`, `erosion_large`, `temperature_large`, `vegetation_large` and `weirdness_large`, each with `firstOctave` lowered by two from vanilla's Large Biomes values for a 16x scale.
- `config/realworld.json` with `biomeScaleMultiplier`, created on first launch.
