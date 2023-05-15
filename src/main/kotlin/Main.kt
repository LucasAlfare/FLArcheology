import com.google.gson.Gson
import java.io.File

fun main() {
  val gson = Gson()
  val jsonDataText = File("json/archeology_data.json").readText()
  val result = gson.fromJson(jsonDataText, ArcheologyDefinition::class.java)
  val search = "kyzaj"
  result.sites.forEach { s ->
    s.hotspots.forEach { h ->
      h.materials.forEach {
        if (it.name.lowercase().contains(search)) {
          println("The material $search can be found in the spot called ${h.name}, in the site ${s.name}")
        }
      }
    }
  }
}