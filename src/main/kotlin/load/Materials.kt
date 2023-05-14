package load

data class JsonMaterial(
  val name: String,
  val type: String,
  val cacheXP: Float = 0.0f,
  val cacheAsName: Boolean = false
)

data class JsonMaterials(
  val materials: Array<JsonMaterial>
) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as JsonMaterials

    return materials.contentEquals(other.materials)
  }

  override fun hashCode(): Int {
    return materials.contentHashCode()
  }
}