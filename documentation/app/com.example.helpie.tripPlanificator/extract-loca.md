//[app](../../index.md)/[com.example.helpie.tripPlanificator](index.md)/[extractLoca](extract-loca.md)

# extractLoca

[androidJvm]\
fun [extractLoca](extract-loca.md)(response: [OjpDto](../com.example.helpie.tripPlanificator.data.dto/-ojp-dto/index.md)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[PlaceInfoDto](../com.example.helpie.tripPlanificator.data.dto.response/-place-info-dto/index.md)&gt;

Extracts location information from the provided OJP response.

#### Return

The list of extracted [PlaceInfoDto](../com.example.helpie.tripPlanificator.data.dto.response/-place-info-dto/index.md) objects.

#### Parameters

androidJvm

| | |
|---|---|
| response | The OJP response containing location information. |

#### Throws

| | |
|---|---|
| [IllegalStateException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-state-exception/index.html) | if no places are found in the response. |
