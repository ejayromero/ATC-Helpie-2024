//[app](../../../index.md)/[com.example.helpie.tripPlanificator.data.dto.request.tr](../index.md)/[PlaceRefDto](index.md)

# PlaceRefDto

[androidJvm]\
@Xml(name = &quot;PlaceRef&quot;)

data class [PlaceRefDto](index.md)(@Element(name = &quot;GeoPosition&quot;)val position: [GeoPositionDto](../-geo-position-dto/index.md)? = null, @PropertyElement(name = &quot;StopPlaceRef&quot;)val stopPlaceRef: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, @Element(name = &quot;LocationName&quot;)val locationName: [LocationNameDto](../-location-name-dto/index.md))

## Constructors

| | |
|---|---|
| [PlaceRefDto](-place-ref-dto.md) | [androidJvm]<br>constructor(@Element(name = &quot;GeoPosition&quot;)position: [GeoPositionDto](../-geo-position-dto/index.md)? = null, @PropertyElement(name = &quot;StopPlaceRef&quot;)stopPlaceRef: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, @Element(name = &quot;LocationName&quot;)locationName: [LocationNameDto](../-location-name-dto/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [locationName](location-name.md) | [androidJvm]<br>val [locationName](location-name.md): [LocationNameDto](../-location-name-dto/index.md) |
| [position](position.md) | [androidJvm]<br>val [position](position.md): [GeoPositionDto](../-geo-position-dto/index.md)? = null |
| [stopPlaceRef](stop-place-ref.md) | [androidJvm]<br>val [stopPlaceRef](stop-place-ref.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
