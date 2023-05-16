package loader


import com.google.gson.Gson
import java.io.File

/**
 * This file describes, in terms of Kotlin language, the data structure
 * that the main JSON data file has.
 *
 * The main purpose of these data classes in this file is to define where
 * each JSON field should go.
 * However, these classes -- and their data fields -- can be used to build
 * other useful functions, such as filters that retrieve information about
 * a certain piece of the JSON data.
 *
 * For example, it is possible to find all spots that are playable for someone of
 * archeology level 80 at "Saradomin" digsite by doing something like:
 *
 * ```kotlin
 * fun getInfo(): List<String> {
 *   val definitions = getArcheologyJsonDefinitions()
 *   val desiredDigsiteName = "Saradomin"
 *   val desiredLevelRange = 50..80
 *
 *   val searchResults = mutableListOf<String>()
 *   definitions.sites.forEach {  digsite ->
 *     if (digsite.name == desiredDigsiteName) {
 *       digsite.hotspots.forEach {  hotspot ->
 *         if (hotspot.level in desiredLevelRange) {
 *           searchResults += hotspot.name
 *         }
 *       }
 *     }
 *   }
 *
 *   return searchResults
 * }
 * ```
 */

data class SpotMaterial(
  val name: String,
  val chance: String
)

data class RestorationMaterial(
  val name: String,
  val quantity: Int
)

data class Artefact(
  val name: String,
  val xp: Float,
  val level: Int,
  val chronotes: Int,
  val materials: Array<RestorationMaterial>
) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as Artefact

    if (name != other.name) return false
    if (xp != other.xp) return false
    if (level != other.level) return false
    if (chronotes != other.chronotes) return false
    return materials.contentEquals(other.materials)
  }

  override fun hashCode(): Int {
    var result = name.hashCode()
    result = 31 * result + xp.hashCode()
    result = 31 * result + level
    result = 31 * result + chronotes
    result = 31 * result + materials.contentHashCode()
    return result
  }
}

data class Hotspot(
  val name: String,
  val level: Int,
  val materials: Array<SpotMaterial>,
  val artefacts: Array<Artefact>
) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as Hotspot

    if (name != other.name) return false
    if (level != other.level) return false
    if (!materials.contentEquals(other.materials)) return false
    return artefacts.contentEquals(other.artefacts)
  }

  override fun hashCode(): Int {
    var result = name.hashCode()
    result = 31 * result + level
    result = 31 * result + materials.contentHashCode()
    result = 31 * result + artefacts.contentHashCode()
    return result
  }
}

data class Site(
  val name: String,
  val hotspots: Array<Hotspot>
) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as Site

    if (name != other.name) return false
    return hotspots.contentEquals(other.hotspots)
  }

  override fun hashCode(): Int {
    var result = name.hashCode()
    result = 31 * result + hotspots.contentHashCode()
    return result
  }
}

data class RepeatableReward(val chronotes: Int)

data class Collection(
  val name: String,
  val level: Int,
  val reward: String?,
  val collector: String,
  val repeatableReward: RepeatableReward,
  val artefacts: Array<String>
) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as Collection

    if (name != other.name) return false
    if (level != other.level) return false
    if (reward != other.reward) return false
    if (collector != other.collector) return false
    if (repeatableReward != other.repeatableReward) return false
    return artefacts.contentEquals(other.artefacts)
  }

  override fun hashCode(): Int {
    var result = name.hashCode()
    result = 31 * result + level
    result = 31 * result + (reward?.hashCode() ?: 0)
    result = 31 * result + collector.hashCode()
    result = 31 * result + repeatableReward.hashCode()
    result = 31 * result + artefacts.contentHashCode()
    return result
  }
}

data class ArcheologyDefinition(
  val sites: Array<Site>,
  val collections: Array<Collection>
) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as ArcheologyDefinition

    return sites.contentEquals(other.sites)
  }

  override fun hashCode(): Int {
    return sites.contentHashCode()
  }
}

/**
 * Parses the root data JSON file to text/string format,
 * reads it using the defined data structure and return the main result.
 */
fun getArcheologyJsonDefinitions(pathname: String = "json/archeology_data.json"): ArcheologyDefinition {
  val jsonDataText = File(pathname).readText()
  return Gson().fromJson(jsonDataText, ArcheologyDefinition::class.java)
}

fun getInfo(): List<String> {
  val definitions = getArcheologyJsonDefinitions()
  val desiredDigsiteName = "Saradomin"
  val desiredLevelRange = 50..80

  val searchResults = mutableListOf<String>()
  definitions.sites.forEach { digsite ->
    if (digsite.name == desiredDigsiteName) {
      digsite.hotspots.forEach { hotspot ->
        if (hotspot.level in desiredLevelRange) {
          searchResults += hotspot.name
        }
      }
    }
  }

  return searchResults
}