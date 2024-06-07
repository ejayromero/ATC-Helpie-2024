//[app](../../../index.md)/[com.example.helpie.tripPlanificator.data.dto.response](../index.md)/[TransLegDto](index.md)

# TransLegDto

[androidJvm]\
@Xml(name = &quot;ojp:TransferLeg&quot;)

data class [TransLegDto](index.md)(@Element(name = &quot;ojp:LegStart&quot;)val start: [LegStartDto](../-leg-start-dto/index.md), @Element(name = &quot;ojp:LegEnd&quot;)val end: [LegEndDto](../-leg-end-dto/index.md), @PropertyElement(name = &quot;ojp:TransferMode&quot;)val service: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @PropertyElement(name = &quot;ojp:TimeWindowStart&quot;)val timeStart: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @PropertyElement(name = &quot;ojp:TimeWindowEnd&quot;)val timeEnd: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @PropertyElement(name = &quot;ojp:Duration&quot;)val duration: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @PropertyElement(name = &quot;ojp:WalkDuration&quot;)val walk: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))

## Constructors

| | |
|---|---|
| [TransLegDto](-trans-leg-dto.md) | [androidJvm]<br>constructor(@Element(name = &quot;ojp:LegStart&quot;)start: [LegStartDto](../-leg-start-dto/index.md), @Element(name = &quot;ojp:LegEnd&quot;)end: [LegEndDto](../-leg-end-dto/index.md), @PropertyElement(name = &quot;ojp:TransferMode&quot;)service: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @PropertyElement(name = &quot;ojp:TimeWindowStart&quot;)timeStart: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @PropertyElement(name = &quot;ojp:TimeWindowEnd&quot;)timeEnd: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @PropertyElement(name = &quot;ojp:Duration&quot;)duration: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @PropertyElement(name = &quot;ojp:WalkDuration&quot;)walk: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [duration](duration.md) | [androidJvm]<br>val [duration](duration.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [end](end.md) | [androidJvm]<br>val [end](end.md): [LegEndDto](../-leg-end-dto/index.md) |
| [service](service.md) | [androidJvm]<br>val [service](service.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [start](start.md) | [androidJvm]<br>val [start](start.md): [LegStartDto](../-leg-start-dto/index.md) |
| [timeEnd](time-end.md) | [androidJvm]<br>val [timeEnd](time-end.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [timeStart](time-start.md) | [androidJvm]<br>val [timeStart](time-start.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [walk](walk.md) | [androidJvm]<br>val [walk](walk.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
