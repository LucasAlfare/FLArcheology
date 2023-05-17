package com.lucasalfare.flarcheology

data class HotspotInfo(
  val hotspot: Hotspot,
  val site: Site
)

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

// TODO: CREATE a function to find ALL spots until target level
class ArcheologyInfoProvider(
  jsonPathname: String = DEFAULT_JSON_DATA_LOCATION
) {

  init {
    if (data == null) {
      data = getArcheologyJsonDefinitions(jsonPathname)
    }
  }

  fun getAllSites() = data!!.sites.toList()

  fun getAllHotspots(
    targetLevel: Int = 120
  ): List<HotspotInfo> {
    val hotspotInfos = mutableListOf<HotspotInfo>()
    getAllSites().forEach { site ->
      site.hotspots.forEach { hotspot ->
        if (hotspot.level <= targetLevel) {
          hotspotInfos += HotspotInfo(
            hotspot = hotspot,
            site = site
          )
        }
      }
    }

    return hotspotInfos.sortedBy { it.hotspot.level }
  }

  fun getAllArtefacts(
    targetLevel: Int = 120
  ): List<ArtefactInfo> {
    val artefacts = mutableListOf<ArtefactInfo>()
    getAllHotspots(targetLevel).forEach { hotspotInfo ->
      if (hotspotInfo.hotspot.level <= targetLevel) {
        hotspotInfo.hotspot.artefacts.forEach { artefact ->
          artefacts += ArtefactInfo(
            artefact = artefact,
            hotspotInfo = hotspotInfo
          )
        }
      }
    }

    return artefacts.sortedBy { it.artefact.level }
  }

  fun getAllMaterials(
    targetLevel: Int = 120
  ): List<MaterialInfo> {
    val materials = mutableListOf<MaterialInfo>()
    getAllHotspots(targetLevel).forEach { hotspotInfo ->
      if (hotspotInfo.hotspot.level <= targetLevel) {
        hotspotInfo.hotspot.materials.forEach { material ->
          materials += MaterialInfo(
            material = material,
            hotspotInfo = hotspotInfo
          )
        }
      }
    }

    return materials
  }
}