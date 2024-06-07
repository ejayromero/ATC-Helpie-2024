//[app](../../index.md)/[com.example.helpie.tripPlanificator](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [OjpSdk](-ojp-sdk/index.md) | [androidJvm]<br>class [OjpSdk](-ojp-sdk/index.md)(baseUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), endpoint: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), requesterReference: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), token: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>SDK for making trip requests using the Open Journey Planner (OJP) service. |

## Functions

| Name | Summary |
|---|---|
| [extractLoca](extract-loca.md) | [androidJvm]<br>fun [extractLoca](extract-loca.md)(response: [OjpDto](../com.example.helpie.tripPlanificator.data.dto/-ojp-dto/index.md)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[PlaceInfoDto](../com.example.helpie.tripPlanificator.data.dto.response/-place-info-dto/index.md)&gt;<br>Extracts location information from the provided OJP response. |
| [extractTrip](extract-trip.md) | [androidJvm]<br>fun [extractTrip](extract-trip.md)(response: [OjpDto](../com.example.helpie.tripPlanificator.data.dto/-ojp-dto/index.md)): [TripDto](../com.example.helpie.tripPlanificator.data.dto.response/-trip-dto/index.md)<br>Extracts trip information from the provided OJP response. |
| [nextStep](next-step.md) | [androidJvm]<br>fun [nextStep](next-step.md)(trip: [TripDto](../com.example.helpie.tripPlanificator.data.dto.response/-trip-dto/index.md), stepID: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), contextLoca: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[PlaceInfoDto](../com.example.helpie.tripPlanificator.data.dto.response/-place-info-dto/index.md)&gt;): [StepInfo](../com.example.helpie/-step-info/index.md)<br>Retrieves information about the next step in the trip. |
| [tripSummary](trip-summary.md) | [androidJvm]<br>fun [tripSummary](trip-summary.md)(trip: [TripDto](../com.example.helpie.tripPlanificator.data.dto.response/-trip-dto/index.md)): [TripSummary](../com.example.helpie/-trip-summary/index.md)<br>Generates a summary of the provided trip. |
