//[app](../../index.md)/[com.example.helpie.tripPlanificator](index.md)/[tripSummary](trip-summary.md)

# tripSummary

[androidJvm]\
fun [tripSummary](trip-summary.md)(trip: [TripDto](../com.example.helpie.tripPlanificator.data.dto.response/-trip-dto/index.md)): [TripSummary](../com.example.helpie/-trip-summary/index.md)

Generates a summary of the provided trip.

#### Return

The generated [TripSummary](../com.example.helpie/-trip-summary/index.md).

#### Parameters

androidJvm

| | |
|---|---|
| trip | The trip for which to generate the summary. |

#### Throws

| | |
|---|---|
| [IllegalStateException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-state-exception/index.html) | if no steps are found in the trip. |
