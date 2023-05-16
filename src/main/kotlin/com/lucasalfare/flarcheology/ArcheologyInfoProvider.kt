package com.lucasalfare.flarcheology

data class HotspotInfo(
  val hotspot: Hotspot,
  val site: Site
)

data class ArtefactInfo(
  val artefact: Artefact,
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

  fun getBestSpotsToDig(
    targetLevel: Int = 120,
    targetSiteName: String = ""
  ): List<HotspotInfo> {
    val results = mutableListOf<HotspotInfo>()

    val nextData =
      if (targetSiteName.isNotEmpty()) data!!.sites.filter { it.name == targetSiteName }
      else data!!.sites.filter { it.name != "Special" }

    nextData.forEach { site ->
      var candidate = site.hotspots.first()
      if (candidate.level <= targetLevel) {
        site.hotspots.sortedBy { it.level }.forEach {
          if (it.level <= targetLevel) {
            candidate = it
          }
        }

        results += HotspotInfo(
          hotspot = candidate,
          site = site
        )
      }
    }

    return results
  }

  fun getBestArtefactsToRestoreOnLevel(
    targetLevel: Int = 120,
    targetSiteName: String = ""
  ): List<ArtefactInfo> {
    val bestSpotsForLevel = getBestSpotsToDig(
      targetLevel = targetLevel,
      targetSiteName = targetSiteName
    )
    val results = mutableListOf<ArtefactInfo>()

    bestSpotsForLevel.forEach { hotspotInfo ->
      hotspotInfo.hotspot.artefacts.forEach { artefact ->
        results += ArtefactInfo(
          artefact = artefact,
          hotspotInfo = hotspotInfo
        )
      }
    }

    return results
  }

  fun getNecessaryMaterialsForArtefactRestoration(
    targetArtefactName: String
  ): List<RestorationMaterial> {
    var results = listOf<RestorationMaterial>()

    data!!.sites.forEach { site ->
      site.hotspots.forEach { hotspot ->
        hotspot.artefacts.forEach { artefact ->
          if (artefact.name == targetArtefactName) {
            results = artefact.materials.toList()
          }
        }
      }
    }

    return results
  }

  //TODO: implement search only best?
  fun getMaterialLocations(
    materialName: String,
    targetLevel: Int = 120,
  ): List<HotspotInfo> {
    val results = mutableListOf<HotspotInfo>()

    data!!.sites.forEach { site ->
      site.hotspots.forEach { hotspot ->
        if (hotspot.level <= targetLevel) {
          if (hotspot.materials.any { it.name == materialName }) {
            results += HotspotInfo(
              hotspot = hotspot,
              site = site
            )
          }
        }
      }
    }

    return results
  }
}