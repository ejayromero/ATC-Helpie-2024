//[app](../../../index.md)/[com.example.helpie.tripPlanificator.data.dto.response](../index.md)/[PlaceInfoDto](index.md)

# PlaceInfoDto

[androidJvm]\
@Xml(name = &quot;ojp:Location&quot;)

data class [PlaceInfoDto](index.md)(@Element(name = &quot;ojp:LocationName&quot;)val locationName: [LocationDto](../-location-dto/index.md)? = null, @Element(name = &quot;ojp:GeoPosition&quot;)val position: [GeoPositionDto](../../com.example.helpie.tripPlanificator.data.dto.request.tr/-geo-position-dto/index.md)? = null)

## Constructors

| | |
|---|---|
| [PlaceInfoDto](-place-info-dto.md) | [androidJvm]<br>constructor(@Element(name = &quot;ojp:LocationName&quot;)locationName: [LocationDto](../-location-dto/index.md)? = null, @Element(name = &quot;ojp:GeoPosition&quot;)position: [GeoPositionDto](../../com.example.helpie.tripPlanificator.data.dto.request.tr/-geo-position-dto/index.md)? = null) |

## Properties

| Name | Summary |
|---|---|
| [locationName](location-name.md) | [androidJvm]<br>val [locationName](location-name.md): [LocationDto](../-location-dto/index.md)? = null |
| [position](position.md) | [androidJvm]<br>val [position](position.md): [GeoPositionDto](../../com.example.helpie.tripPlanificator.data.dto.request.tr/-geo-position-dto/index.md)? = null |
