package load

data class JsonHotspotMaterialInfo(
  val material: String,
  val chance: String
)

data class JsonHotspot(
  val name: String,
  val site: String,
  val level: Int,
  val materials: Array<JsonHotspotMaterialInfo>,
) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as JsonHotspot

    if (name != other.name) return false
    if (site != other.site) return false
    if (level != other.level) return false
    return materials.contentEquals(other.materials)
  }

  override fun hashCode(): Int {
    var result = name.hashCode()
    result = 31 * result + site.hashCode()
    result = 31 * result + level
    result = 31 * result + materials.contentHashCode()
    return result
  }
}

data class JsonHotspots(
  val hotspots: Array<JsonHotspot>
) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as JsonHotspots

    return hotspots.contentEquals(other.hotspots)
  }

  override fun hashCode(): Int {
    return hotspots.contentHashCode()
  }
}