//[app](../../../index.md)/[com.example.helpie.tripPlanificator.data.dto.response](../index.md)/[LegBoardDto](index.md)

# LegBoardDto

[androidJvm]\
@Xml(name = &quot;ojp:LegBoard&quot;)

data class [LegBoardDto](index.md)(@PropertyElement(name = &quot;siri:StopPointRef&quot;)val stopPlaceRef: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, @Element(name = &quot;ojp:StopPointName&quot;)val name: [LocationDto](../-location-dto/index.md)? = null, @Element(name = &quot;ojp:PlannedQuay&quot;)val quay: [LocationDto](../-location-dto/index.md)? = null, @Element(name = &quot;ojp:ServiceDeparture&quot;)val time: [DepartureDto](../-departure-dto/index.md)? = null)

## Constructors

| | |
|---|---|
| [LegBoardDto](-leg-board-dto.md) | [androidJvm]<br>constructor(@PropertyElement(name = &quot;siri:StopPointRef&quot;)stopPlaceRef: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, @Element(name = &quot;ojp:StopPointName&quot;)name: [LocationDto](../-location-dto/index.md)? = null, @Element(name = &quot;ojp:PlannedQuay&quot;)quay: [LocationDto](../-location-dto/index.md)? = null, @Element(name = &quot;ojp:ServiceDeparture&quot;)time: [DepartureDto](../-departure-dto/index.md)? = null) |

## Properties

| Name | Summary |
|---|---|
| [name](name.md) | [androidJvm]<br>val [name](name.md): [LocationDto](../-location-dto/index.md)? = null |
| [quay](quay.md) | [androidJvm]<br>val [quay](quay.md): [LocationDto](../-location-dto/index.md)? = null |
| [stopPlaceRef](stop-place-ref.md) | [androidJvm]<br>val [stopPlaceRef](stop-place-ref.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
| [time](time.md) | [androidJvm]<br>val [time](time.md): [DepartureDto](../-departure-dto/index.md)? = null |
