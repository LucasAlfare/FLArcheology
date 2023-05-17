package com.lucasalfare.flarcheology

data class HotspotInfo(
  val hotspot: Hotspot,
  val site: Site
) {

  fun containsMaterial(materialName: String) = hotspot.materials.any { it.name == materialName }

  fun containsArtefact(artefactName: String) = hotspot.artefacts.any { it.name == artefactName }
}

data class ArtefactInfo(
  val artefact: Artefact,
  val hotspotInfo: HotspotInfo
)

data class MaterialInfo(
  val material: SpotMaterial,
  val hotspotInfo: HotspotInfo
)

private var data: ArcheologyDefinition? = null
private const val DEFAULT_JSON_DATA_LOCATION = "src/main/resources/archeology_data.json"

val notExcavationMaterials = arrayOf(
  "Dragonstone",
  "Molten glass",
  "Death rune",
  "Ruby",
  "White candle",
  "Bronze bar",
  "Silver bar",
  "Sapphire",
  "Clockwork",
  "Diamond",
  "Phoenix feather",
  "Rope",
  "Black mushroom ink",
  "Weapon poison (3)",
  "Emerald"
)

class ArcheologyInfoProvider(jsonPathname: String = DEFAULT_JSON_DATA_LOCATION) {

  init {
    if (data == null) data = getArcheologyJsonDefinitions(jsonPathname)
  }

  fun getAllSites() = data!!.sites.toList()

  fun getAllHotspots(): List<HotspotInfo> {
    val hotspotInfos = mutableListOf<HotspotInfo>()
    getAllSites().forEach { site ->
      site.hotspots.forEach { hotspot ->
        hotspotInfos += HotspotInfo(
          hotspot = hotspot,
          site = site
        )
      }
    }

    return hotspotInfos
  }

  fun getAllArtefacts(): List<ArtefactInfo> {
    val artefacts = mutableListOf<ArtefactInfo>()
    getAllHotspots().forEach { hotspotInfo ->
      hotspotInfo.hotspot.artefacts.forEach { artefact ->
        artefacts += ArtefactInfo(
          artefact = artefact,
          hotspotInfo = hotspotInfo
        )
      }
    }

    return artefacts
  }

  fun getAllMaterials(): List<MaterialInfo> {
    val materials = mutableListOf<MaterialInfo>()
    getAllHotspots().forEach { hotspotInfo ->
      hotspotInfo.hotspot.materials.forEach { material ->
        materials += MaterialInfo(
          material = material,
          hotspotInfo = hotspotInfo
        )
      }
    }

    return materials
  }

  fun containsMaterial(materialName: String) = getAllMaterials().any { it.material.name == materialName }
}