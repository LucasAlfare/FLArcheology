package core

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import load.jsonArtefacts
import load.jsonHotspots
import java.io.File

data class HotspotMaterialInfo(
  val materialName: String,
  val chance: String
)

data class Hotspot(
  val name: String,
  val level: Int,
  val digSiteName: String,
  val materials: MutableList<HotspotMaterialInfo>
)

data class DigSite(
  val name: String,
  val hotspots: MutableList<Hotspot> = mutableListOf()
)

class DigSites(
  @SerializedName("sites") val sites: MutableList<DigSite> = mutableListOf()
) {
  fun getSiteByName(name: String) = sites.first { it.name == name }
}

var sites = DigSites()

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
          digSiteName = s.name,
          materials = nextHotspotMaterialsInfos.toMutableList()
        )
      }

      s.hotspots.addAll(nextHotspots)
    }
  }

  return sites
}

fun generateFinalArqueologyJsonDataFile() {
  val g = Gson()
  val res = Gson().toJson(getDigSites())
  val finalArqueologyData = File("json/final_arqueology_data.json")
  finalArqueologyData.writeText(res)
}