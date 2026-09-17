# Changelog

All notable changes to Realworld are documented here.

The format follows [Keep a Changelog](https://keepachangelog.com/en/1.1.0/). Versions are four-part, `<line>.<major>.<minor>.<patch>`, where the first component names the Minecraft line: `1` for 1.21, `2` for 26.2. The two lines move in lockstep on the remaining components, so a change to shared content (datapack, config, docs) appears in both. Fabric Loader's semver parser accepts any number of components, so dependency ranges work on these as normal.

## [1.1.3.0] - 2026-09-17 13:02 IST

### Changed

- Build target moved to Minecraft 26.1. The jar remains compatible with Minecraft 1.21 through 26.1 because it uses only Fabric Loader APIs and contains no Minecraft code.

## [1.1.2.0] - 2026-09-12 13:34 IST

First build from source on the 1.21 line. Minecraft 1.21 through 1.21.11, Fabric Loader 0.15 or later, Java 21. No Fabric API needed.

This line contains no Minecraft code at all, only the datapack and the config reader, which is what lets it declare such a wide version range. `/realworld locate` is not on this line: it needs Minecraft's command API, which would pin it to one version.

### Added

- Source repository. The mod previously existed only as a built jar.
- Release workflow, as on the 26.2 line but pinned to line 1 and Java 21. Releases here are never marked as the repository's latest, so a 1.21 maintenance release does not displace the 26.2 line.
- Build workflow on every push and pull request.
- README, this changelog, and the full Apache-2.0 text in `LICENSE.txt`.

### Changed

- Relicensed from CC0-1.0 to Apache-2.0.
- Version scheme is now four-part (see the note at the top).
- Config class rewritten from the decompiled output, as on the 26.2 line. Behaviour and on-disk format unchanged.
- Declares `minecraft >=1.21 <26`. The upper bound exists so the two lines never both claim the same game version. Fabric API is no longer a dependency: the mod never used it.

### Removed

- `weirdness_large.json` override. Never did anything on any version.
- `Realworld.id()`. Never called. With it gone this line has no `net.minecraft` symbols at all, verified on the built jar.

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
