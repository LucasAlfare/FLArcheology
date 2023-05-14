package load

import com.google.gson.Gson
import java.io.File

private val gson = Gson()
private val jsonMaterialsFileContent = File("json/materials.json").readText()
private val jsonHotspotsFileContent = File("json/hotspots.json").readText()
private val jsonArtefactsFileContent = File("json/artefacts.json").readText()

val jsonMaterials = gson.fromJson(jsonMaterialsFileContent, JsonMaterials::class.java).materials
val jsonHotspots = gson.fromJson(jsonHotspotsFileContent, JsonHotspots::class.java).hotspots
val jsonArtefacts = gson.fromJson(jsonArtefactsFileContent, JsonArtefacts::class.java).artefacts