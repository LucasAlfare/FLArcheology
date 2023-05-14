package core

import load.jsonArtefacts
import load.jsonHotspots

data class HotspotMaterialInfo(
  val materialName: String,
  val chance: String
)

data class Hotspot(
  val name: String,
  val level: Int,
  val digSite: DigSite,
  val materials: MutableList<HotspotMaterialInfo>
)

data class DigSite(
  val name: String,
  val hotspots: MutableList<Hotspot> = mutableListOf()
)

class DigSites(
  val sites: MutableList<DigSite> = mutableListOf()
) {
  fun getSiteByName(name: String) = sites.first { it.name == name }
}

private val sites = DigSites()

fun getDigSites(): DigSites {
  if (sites.sites.isEmpty()) {
    jsonArtefacts.forEach { a ->
      if (!(sites.sites.any { it.name == a.site })) {
        sites.sites += DigSite(a.site)
      }
    }

    sites.sites.forEach { s ->
      val nextHotspots = jsonHotspots.filter { it.site == s.name }.map { currHostspot ->
        val nextHotspotMaterialsInfos = currHostspot.materials.map { currMaterial ->
          HotspotMaterialInfo(
            materialName = currMaterial.material,
            chance = currMaterial.chance
          )
        }

        Hotspot(
          name = currHostspot.name,
          level = currHostspot.level,
          digSite = s,
          materials = nextHotspotMaterialsInfos.toMutableList()
        )
      }

      s.hotspots.addAll(nextHotspots)
    }
  }

  return sites
}