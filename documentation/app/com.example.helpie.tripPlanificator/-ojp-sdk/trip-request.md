//[app](../../../index.md)/[com.example.helpie.tripPlanificator](../index.md)/[OjpSdk](index.md)/[tripRequest](trip-request.md)

# tripRequest

[androidJvm]\
suspend fun [tripRequest](trip-request.md)(here: LatLng, target: [Localisation](../../com.example.helpie/-localisation/index.md)): [OjpDto](../../com.example.helpie.tripPlanificator.data.dto/-ojp-dto/index.md)

Asynchronously requests trip information from the OJP service.

#### Return

The OJP response containing trip information.

#### Parameters

androidJvm

| | |
|---|---|
| here | The current location represented by latitude and longitude coordinates. |
| target | The target location for the trip. |
