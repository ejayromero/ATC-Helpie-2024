//[app](../../index.md)/[com.example.helpie.tripPlanificator](index.md)/[nextStep](next-step.md)

# nextStep

[androidJvm]\
fun [nextStep](next-step.md)(trip: [TripDto](../com.example.helpie.tripPlanificator.data.dto.response/-trip-dto/index.md), stepID: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), contextLoca: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[PlaceInfoDto](../com.example.helpie.tripPlanificator.data.dto.response/-place-info-dto/index.md)&gt;): [StepInfo](../com.example.helpie/-step-info/index.md)

Retrieves information about the next step in the trip.

#### Return

The [StepInfo](../com.example.helpie/-step-info/index.md) object representing the next step.

#### Parameters

androidJvm

| | |
|---|---|
| trip | The trip for which to retrieve the next step. |
| stepID | The ID of the current step. |
| contextLoca | The list of location information used for context. |

#### Throws

| | |
|---|---|
| [IllegalStateException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-state-exception/index.html) | if no steps are found in the trip. |
