//[app](../../../index.md)/[com.example.helpie.tripPlanificator.data.dto.response](../index.md)/[LegEndDto](index.md)

# LegEndDto

[androidJvm]\
@Xml(name = &quot;ojp:LegEnd&quot;)

data class [LegEndDto](index.md)(@Element(name = &quot;ojp:GeoPosition&quot;)val position: [GeoPositionDto](../../com.example.helpie.tripPlanificator.data.dto.request.tr/-geo-position-dto/index.md)? = null, @Element(name = &quot;ojp:LocationName&quot;)val name: [LocationDto](../-location-dto/index.md)? = null, @PropertyElement(name = &quot;siri:StopPointRef&quot;)val stopPlaceRef: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null)

## Constructors

| | |
|---|---|
| [LegEndDto](-leg-end-dto.md) | [androidJvm]<br>constructor(@Element(name = &quot;ojp:GeoPosition&quot;)position: [GeoPositionDto](../../com.example.helpie.tripPlanificator.data.dto.request.tr/-geo-position-dto/index.md)? = null, @Element(name = &quot;ojp:LocationName&quot;)name: [LocationDto](../-location-dto/index.md)? = null, @PropertyElement(name = &quot;siri:StopPointRef&quot;)stopPlaceRef: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null) |

## Properties

| Name | Summary |
|---|---|
| [name](name.md) | [androidJvm]<br>val [name](name.md): [LocationDto](../-location-dto/index.md)? = null |
| [position](position.md) | [androidJvm]<br>val [position](position.md): [GeoPositionDto](../../com.example.helpie.tripPlanificator.data.dto.request.tr/-geo-position-dto/index.md)? = null |
| [stopPlaceRef](stop-place-ref.md) | [androidJvm]<br>val [stopPlaceRef](stop-place-ref.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
