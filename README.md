# FLArcheology

My own experiments in creating a custom tool to help me in Runescape3 Archeology skill.

# The JSON data

This project focuses on JSON data to get information about the skill.
The main JSON data file has its structure based on the following hierarchy:

```json
{
  "sites": [
    {
      "thisIsTheSiteName": "Site name!!",
      "thisIsArrayOfSiteHotspots": [
        {
          "thisIsTheNameOfTheHotspot": "Hotspot Name!!",
          "thisIsTheRequiredLevelToDigTheSite": 999,
          "allMaterialsThatCanBeFoundInThisSpot": [
            {
              "thisIsTheMaterialName": "Material Name!!",
              "thisIsTheChanceThatThisMaterialHaveToBeDigged": "0/10"
            }
          ],
          "allArtefactsThatCanBeFoundInThisHotspot": [
            {
              "theNameOfTheArtefact": "Artefact Name!!",
              "experienceWhenDigged": 0.0,
              "requiredLevelToDig(sameAsHotspot)": 999,
              "numberOfChronotesWhenStoreToCollectors": 999,
              "materialsNeededToRestore(includesStandardItems)": [
                {
                  "restoreMaterialName": "Name !!",
                  "quantityNeeded": 99999
                }
              ]
            }
          ]
        }
      ]
    }
  ],
  "collections": [
    {
      "nameOfThisCollection": "Collection Name!",
      "neededLevelToFullUnlock": 9999,
      "mainRewardWhenCompleteFirstTime": "This is the reward!!!",
      "collectorName": "Name of the collector of this collection!!",
      "rewardWhenThisCollectionWasAlreadyCompletedBefore": {
        "numberOfChronotesReceived": 99999
      },
      "artefactsToCompleteThisCollection": [
        "Artefact 1",
        "Artefact 2",
        "Artefact 3"
      ]
    }
  ]
}
```

# The Kotlin API

Currently, there's no pretty API to use.
However, we have some Kotlin code that
parses the JSON file into some kotlin `data classes`.
For example, it is possible to find all spots that are playable for someone of
archeology level 80 at "Saradomin" digsite by doing something like:

 ```kotlin
 fun getMyCustomInfo(): List<String> {
  val loadedDefinitions = getArcheologyJsonDefinitions()
  val desiredDigsiteName = "Saradomin"
  val desiredLevelRange = 50..80

  val searchResults = mutableListOf<String>()
  loadedDefinitions.sites.forEach { digsite ->
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
 ```

As seen, we can "easly"
use the kolin data classes to perform a large amount of filtering on the JSON data,
which should be enough to build some tool applications.

Also, in the future should be implemented, by default, some filters like that and other constant fields, in order to
avoid writing then in high-level applications.

# Download

This library can be retrieved via [JitPack](https://jitpack.io/).
Using gradle, you can grab it by adding the jitpack repository and this project dependency in the `build.gradle` file:

```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.LucasAlfare:FLArcheology:v1.0'
}
```