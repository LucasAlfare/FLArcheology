import core.getSites

fun main() {
  getSites().getSiteByName("Zaros").hotspots.forEach { println(it) }
}