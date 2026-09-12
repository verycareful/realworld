# Realworld
<!-- Language & platform -->
[![Java](https://img.shields.io/badge/Java-21+-ED8B00?style=flat-square&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Minecraft](https://img.shields.io/badge/Minecraft-1.21-62B47A?style=flat-square&logo=minecraft&logoColor=white)](https://www.minecraft.net/)
<!-- Mod toolchain. Fabric and Loom have no shields.io logos; swap for logo badges if any appear. -->
[![Fabric Loader](https://img.shields.io/badge/Fabric%20Loader-0.19.3-DBD0B4?style=flat-square)](https://fabricmc.net/)
[![Fabric Loom](https://img.shields.io/badge/Fabric%20Loom-1.17--SNAPSHOT-DBD0B4?style=flat-square)](https://github.com/FabricMC/fabric-loom)
<!-- Build -->
[![Gradle](https://img.shields.io/badge/Gradle-9.5.1-02303A?style=flat-square&logo=gradle&logoColor=white)](https://gradle.org/)
<!-- Project -->
[![Version](https://img.shields.io/badge/version-1.1.2.0-blue?style=flat-square)](gradle.properties)
[![License: Apache 2.0](https://img.shields.io/badge/License-Apache%202.0-blue.svg?style=flat-square)](LICENSE.txt)
[![Status: Active](https://img.shields.io/badge/Status-Active-brightgreen?style=flat-square)](.)

A Fabric mod that makes Minecraft's **Large Biomes** preset larger still: 16x vanilla instead of 4x, so a single biome can run for tens of thousands of blocks.

Nothing else changes. Vanilla's generator, structures and biome set are untouched; the mod only lowers the octave of the four noises that drive large-biome placement. Default worlds are not affected at all.

## Usage

1. Install [Fabric Loader](https://fabricmc.net/use/). Fabric API is not required on this line.
2. Drop the Realworld jar into `mods/`.
3. Create a new world and pick **Large Biomes** as the world type.

Existing worlds keep the noise settings they were created with. Adding or removing the mod on an existing Large Biomes world will produce a seam at the edge of the generated area.

## Finding biomes

At 16x, vanilla's `/locate biome` is useless: it searches a fixed 6400 blocks, which covers roughly what 400 blocks covers in an unscaled world. The mod adds a command that reaches much further:

```
/realworld locate <biome> [radius] [step]
```

| Argument | Default | Limit |
|---|---|---|
| `radius` | 64000 | 10,000,000 |
| `step` | 64 | 4096 |

`step` is the sampling interval in blocks. Vanilla uses 32. A coarser step covers more ground for the same time, at the risk of skipping very small biomes. The command requires the same permission level as `/locate`.

## Configuration

`config/realworld.json` is created on first launch:

```json
{
  "biomeScaleMultiplier": 16.0
}
```

**The multiplier is currently read and logged but does not affect generation.** The noise overrides are fixed at 16x. Making the setting live is tracked in the issue tracker.

## Minecraft versions

Two lines are maintained as branches. The first component of the version number names the line.

| Branch | Minecraft | Version | Command |
|---|---|---|---|
| `main` | 26.2 and later | `2.x.y.z` | yes |
| `1.21` | 1.21 up to 1.21.11 | `1.x.y.z` | no |

The `1.21` line contains no Minecraft code at all, only the datapack and config, which is what lets it declare such a wide range. The command needs Minecraft's command API and pins the `main` line to 26.2.

## Building

```
./gradlew build
```

Requires JDK 25 (`main`) or JDK 21 (`1.21`). The jar lands in `build/libs/`.

## License

Copyright © 2026 Sricharan Suresh (github.com/verycareful)

Realworld is licensed under the **[Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0)**.
You may use, modify and redistribute it, including commercially, provided you
retain the copyright and license notices, state any changes you made, and
include a copy of the license. The license also grants an explicit patent
licence from contributors, and provides the software without warranty.

See the [LICENSE.txt](LICENSE.txt) file for the full license text.
