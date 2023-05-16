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

  // TODO
  fun getAllSites(
    targetLevel: Int = 120,
    shouldContainsMaterialName: String = "",
    shouldContainsArtefactName: String = ""
  ): List<Site> {
    val results = mutableListOf<Site>()

    return results
  }
}