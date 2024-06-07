//[app](../../../index.md)/[com.example.helpie.tripPlanificator.data.dto.response](../index.md)/[LegAlightDto](index.md)

# LegAlightDto

[androidJvm]\
@Xml(name = &quot;ojp:LegAlight&quot;)

data class [LegAlightDto](index.md)(@PropertyElement(name = &quot;siri:StopPointRef&quot;)val stopPlaceRef: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, @Element(name = &quot;ojp:StopPointName&quot;)val name: [LocationDto](../-location-dto/index.md)? = null, @Element(name = &quot;ojp:PlannedQuay&quot;)val quay: [LocationDto](../-location-dto/index.md)? = null, @Element(name = &quot;ojp:ServiceArrival&quot;)val time: [DepartureDto](../-departure-dto/index.md)? = null)

## Constructors

| | |
|---|---|
| [LegAlightDto](-leg-alight-dto.md) | [androidJvm]<br>constructor(@PropertyElement(name = &quot;siri:StopPointRef&quot;)stopPlaceRef: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, @Element(name = &quot;ojp:StopPointName&quot;)name: [LocationDto](../-location-dto/index.md)? = null, @Element(name = &quot;ojp:PlannedQuay&quot;)quay: [LocationDto](../-location-dto/index.md)? = null, @Element(name = &quot;ojp:ServiceArrival&quot;)time: [DepartureDto](../-departure-dto/index.md)? = null) |

## Properties

| Name | Summary |
|---|---|
| [name](name.md) | [androidJvm]<br>val [name](name.md): [LocationDto](../-location-dto/index.md)? = null |
| [quay](quay.md) | [androidJvm]<br>val [quay](quay.md): [LocationDto](../-location-dto/index.md)? = null |
| [stopPlaceRef](stop-place-ref.md) | [androidJvm]<br>val [stopPlaceRef](stop-place-ref.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
| [time](time.md) | [androidJvm]<br>val [time](time.md): [DepartureDto](../-departure-dto/index.md)? = null |
