import core.Hotspot
import core.getDigSites

fun main() {
  val result = mutableListOf<Hotspot>()

  getDigSites().sites.forEach {
    result.addAll(it.hotspots.filter { h -> h.level <= 88 })
  }

  println(result.count { it.digSite.name == "Bandos" })
}