# FLArqueology

My own experiments in creating a custom tool to help me in Runescape3 Archeology skill.

# JSON data

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