//[app](../../../index.md)/[com.example.helpie.tripPlanificator.data.dto.response](../index.md)/[TripDto](index.md)

# TripDto

[androidJvm]\
@Xml(name = &quot;Trip&quot;)

data class [TripDto](index.md)(@PropertyElement(name = &quot;ojp:TripId&quot;)val id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, @PropertyElement(name = &quot;ojp:Duration&quot;)val duration: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, @PropertyElement(name = &quot;ojp:StartTime&quot;)val start: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, @PropertyElement(name = &quot;ojp:EndTime&quot;)val end: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, @Element(name = &quot;ojp:TripLeg&quot;)val step: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[TripLegDto](../-trip-leg-dto/index.md)&gt;? = null)

## Constructors

| | |
|---|---|
| [TripDto](-trip-dto.md) | [androidJvm]<br>constructor(@PropertyElement(name = &quot;ojp:TripId&quot;)id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, @PropertyElement(name = &quot;ojp:Duration&quot;)duration: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, @PropertyElement(name = &quot;ojp:StartTime&quot;)start: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, @PropertyElement(name = &quot;ojp:EndTime&quot;)end: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, @Element(name = &quot;ojp:TripLeg&quot;)step: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[TripLegDto](../-trip-leg-dto/index.md)&gt;? = null) |

## Properties

| Name | Summary |
|---|---|
| [duration](duration.md) | [androidJvm]<br>val [duration](duration.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
| [end](end.md) | [androidJvm]<br>val [end](end.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
| [id](id.md) | [androidJvm]<br>val [id](id.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
| [start](start.md) | [androidJvm]<br>val [start](start.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
| [step](step.md) | [androidJvm]<br>val [step](step.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[TripLegDto](../-trip-leg-dto/index.md)&gt;? = null |
