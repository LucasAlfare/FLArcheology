import core.getDigSites

fun main() {
  getDigSites().getSiteByName("Zaros").hotspots.filter { it.level >= 100 }.forEach { println(it.name) }
}