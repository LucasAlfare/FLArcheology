import com.google.gson.Gson
import core.DigSites
import core.generateFinalArqueologyJsonDataFile
import core.getDigSites
import core.sites
import java.io.File

fun main() {
  sites = Gson().fromJson(File("json/final_arqueology_data.json").readText(), DigSites::class.java)
  val res = sites
  res.sites.forEach {
    it.hotspots.forEach { h ->
      h.materials.forEach { m ->
        if (m.materialName == "White marble") {
          println("White marble can be found in ${h.name}, that exists in ${it.name}. You need  level ${h.level} to dig it.")
        }
      }
    }
  }
}