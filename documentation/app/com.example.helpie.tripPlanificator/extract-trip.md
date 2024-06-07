//[app](../../index.md)/[com.example.helpie.tripPlanificator](index.md)/[extractTrip](extract-trip.md)

# extractTrip

[androidJvm]\
fun [extractTrip](extract-trip.md)(response: [OjpDto](../com.example.helpie.tripPlanificator.data.dto/-ojp-dto/index.md)): [TripDto](../com.example.helpie.tripPlanificator.data.dto.response/-trip-dto/index.md)

Extracts trip information from the provided OJP response.

#### Return

The extracted [TripDto](../com.example.helpie.tripPlanificator.data.dto.response/-trip-dto/index.md).

#### Parameters

androidJvm

| | |
|---|---|
| response | The OJP response containing trip information. |

#### Throws

| | |
|---|---|
| [IllegalStateException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-state-exception/index.html) | if no trip is found in the response. |
