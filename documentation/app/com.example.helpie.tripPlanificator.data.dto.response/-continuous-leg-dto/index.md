//[app](../../../index.md)/[com.example.helpie.tripPlanificator.data.dto.response](../index.md)/[ContinuousLegDto](index.md)

# ContinuousLegDto

[androidJvm]\
@Xml(name = &quot;ojp:ContinuousLeg&quot;)

data class [ContinuousLegDto](index.md)(@Element(name = &quot;ojp:LegStart&quot;)val start: [LegStartDto](../-leg-start-dto/index.md), @Element(name = &quot;ojp:LegEnd&quot;)val end: [LegEndDto](../-leg-end-dto/index.md), @Element(name = &quot;ojp:Service&quot;)val service: [ServiceDto](../-service-dto/index.md), @PropertyElement(name = &quot;ojp:TimeWindowStart&quot;)val timeStart: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @PropertyElement(name = &quot;ojp:TimeWindowEnd&quot;)val timeEnd: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @PropertyElement(name = &quot;ojp:Duration&quot;)val duration: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @PropertyElement(name = &quot;ojp:Length&quot;)val length: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html))

## Constructors

| | |
|---|---|
| [ContinuousLegDto](-continuous-leg-dto.md) | [androidJvm]<br>constructor(@Element(name = &quot;ojp:LegStart&quot;)start: [LegStartDto](../-leg-start-dto/index.md), @Element(name = &quot;ojp:LegEnd&quot;)end: [LegEndDto](../-leg-end-dto/index.md), @Element(name = &quot;ojp:Service&quot;)service: [ServiceDto](../-service-dto/index.md), @PropertyElement(name = &quot;ojp:TimeWindowStart&quot;)timeStart: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @PropertyElement(name = &quot;ojp:TimeWindowEnd&quot;)timeEnd: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @PropertyElement(name = &quot;ojp:Duration&quot;)duration: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @PropertyElement(name = &quot;ojp:Length&quot;)length: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [duration](duration.md) | [androidJvm]<br>val [duration](duration.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [end](end.md) | [androidJvm]<br>val [end](end.md): [LegEndDto](../-leg-end-dto/index.md) |
| [length](length.md) | [androidJvm]<br>val [length](length.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [service](service.md) | [androidJvm]<br>val [service](service.md): [ServiceDto](../-service-dto/index.md) |
| [start](start.md) | [androidJvm]<br>val [start](start.md): [LegStartDto](../-leg-start-dto/index.md) |
| [timeEnd](time-end.md) | [androidJvm]<br>val [timeEnd](time-end.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [timeStart](time-start.md) | [androidJvm]<br>val [timeStart](time-start.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
