//[app](../../../index.md)/[com.example.helpie.tripPlanificator](../index.md)/[OjpSdk](index.md)

# OjpSdk

[androidJvm]\
class [OjpSdk](index.md)(baseUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), endpoint: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), requesterReference: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), token: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))

SDK for making trip requests using the Open Journey Planner (OJP) service.

## Constructors

| | |
|---|---|
| [OjpSdk](-ojp-sdk.md) | [androidJvm]<br>constructor(baseUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), endpoint: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), requesterReference: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), token: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [tripRequest](trip-request.md) | [androidJvm]<br>suspend fun [tripRequest](trip-request.md)(here: LatLng, target: [Localisation](../../com.example.helpie/-localisation/index.md)): [OjpDto](../../com.example.helpie.tripPlanificator.data.dto/-ojp-dto/index.md)<br>Asynchronously requests trip information from the OJP service. |
