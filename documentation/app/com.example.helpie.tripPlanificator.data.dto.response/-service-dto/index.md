//[app](../../../index.md)/[com.example.helpie.tripPlanificator.data.dto.response](../index.md)/[ServiceDto](index.md)

# ServiceDto

[androidJvm]\
@Xml(name = &quot;ojp:Service&quot;)

data class [ServiceDto](index.md)(@PropertyElement(name = &quot;ojp:IndividualMode&quot;)val individualMode: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, @Element(name = &quot;ojp:Mode&quot;)val mode: [ModeDto](../-mode-dto/index.md)? = null, @Element(name = &quot;ojp:PublishedLineName&quot;)val line: [LocationDto](../-location-dto/index.md)? = null, @Element(name = &quot;ojp:DestinationText&quot;)val way: [LocationDto](../-location-dto/index.md)? = null)

## Constructors

| | |
|---|---|
| [ServiceDto](-service-dto.md) | [androidJvm]<br>constructor(@PropertyElement(name = &quot;ojp:IndividualMode&quot;)individualMode: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, @Element(name = &quot;ojp:Mode&quot;)mode: [ModeDto](../-mode-dto/index.md)? = null, @Element(name = &quot;ojp:PublishedLineName&quot;)line: [LocationDto](../-location-dto/index.md)? = null, @Element(name = &quot;ojp:DestinationText&quot;)way: [LocationDto](../-location-dto/index.md)? = null) |

## Properties

| Name | Summary |
|---|---|
| [individualMode](individual-mode.md) | [androidJvm]<br>val [individualMode](individual-mode.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
| [line](line.md) | [androidJvm]<br>val [line](line.md): [LocationDto](../-location-dto/index.md)? = null |
| [mode](mode.md) | [androidJvm]<br>val [mode](mode.md): [ModeDto](../-mode-dto/index.md)? = null |
| [way](way.md) | [androidJvm]<br>val [way](way.md): [LocationDto](../-location-dto/index.md)? = null |
