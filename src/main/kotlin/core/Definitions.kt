package core

import load.jsonArtefacts
import load.jsonHotspots

data class HotspotMaterialInfo(
  val materialName: String,
  val chance: String
)

data class Hotspot(
  val name: String,
  val materials: MutableList<HotspotMaterialInfo>
)

data class Site(
  val name: String,
  val hotspots: MutableList<Hotspot> = mutableListOf()
)

class Sites(
  val sites: MutableList<Site> = mutableListOf()
) {
  fun getSiteByName(name: String) = sites.first { it.name == name }
}

private val sites = Sites()

fun getSites(): Sites {
  if (sites.sites.isEmpty()) {
    jsonArtefacts.forEach { a ->
      if (!(sites.sites.any { it.name == a.site })) {
        sites.sites += Site(a.site)
      }
    }

    sites.sites.forEach { s ->
      val nextHotspots = jsonHotspots.filter { it.site == s.name }.map { currentcurrHotspot ->
        val nextHotspotMaterialsInfos = currentcurrHotspot.materials.map { currMaterial ->
          HotspotMaterialInfo(
            materialName = currMaterial.material,
            chance = currMaterial.chance
          )
        }

        Hotspot(
          name = currentcurrHotspot.name,
          materials = nextHotspotMaterialsInfos.toMutableList()
        )
      }

      s.hotspots.addAll(nextHotspots)
    }
  }

  return sites
}