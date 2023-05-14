package load

data class JsonArtefactMaterialInfo(
  val name: String,
  val quantity: Int
)

data class JsonArtefact(
  val name: String,
  val xp: Float,
  val site: String,
  val level: Int,
  val chronotes: Int,
  val materials: Array<JsonArtefactMaterialInfo>,
  val location: String
) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as JsonArtefact

    if (name != other.name) return false
    if (xp != other.xp) return false
    if (site != other.site) return false
    if (level != other.level) return false
    if (chronotes != other.chronotes) return false
    if (!materials.contentEquals(other.materials)) return false
    return location == other.location
  }

  override fun hashCode(): Int {
    var result = name.hashCode()
    result = 31 * result + xp.hashCode()
    result = 31 * result + site.hashCode()
    result = 31 * result + level
    result = 31 * result + chronotes
    result = 31 * result + materials.contentHashCode()
    result = 31 * result + location.hashCode()
    return result
  }
}

data class JsonArtefacts(
  val artefacts: Array<JsonArtefact>
) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as JsonArtefacts

    return artefacts.contentEquals(other.artefacts)
  }

  override fun hashCode(): Int {
    return artefacts.contentHashCode()
  }
}