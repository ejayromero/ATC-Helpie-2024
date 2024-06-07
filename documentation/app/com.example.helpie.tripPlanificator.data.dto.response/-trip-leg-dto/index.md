//[app](../../../index.md)/[com.example.helpie.tripPlanificator.data.dto.response](../index.md)/[TripLegDto](index.md)

# TripLegDto

[androidJvm]\
@Xml(name = &quot;ojp:TripLeg&quot;)

data class [TripLegDto](index.md)(@PropertyElement(name = &quot;ojp:LegId&quot;)val id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, @Element(name = &quot;ojp:ContinuousLeg&quot;)val cLeg: [ContinuousLegDto](../-continuous-leg-dto/index.md)? = null, @Element(name = &quot;ojp:TimedLeg&quot;)val tLeg: [TimedLegDto](../-timed-leg-dto/index.md)? = null, @Element(name = &quot;ojp:TransferLeg&quot;)val transLeg: [TransLegDto](../-trans-leg-dto/index.md)? = null)

## Constructors

| | |
|---|---|
| [TripLegDto](-trip-leg-dto.md) | [androidJvm]<br>constructor(@PropertyElement(name = &quot;ojp:LegId&quot;)id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, @Element(name = &quot;ojp:ContinuousLeg&quot;)cLeg: [ContinuousLegDto](../-continuous-leg-dto/index.md)? = null, @Element(name = &quot;ojp:TimedLeg&quot;)tLeg: [TimedLegDto](../-timed-leg-dto/index.md)? = null, @Element(name = &quot;ojp:TransferLeg&quot;)transLeg: [TransLegDto](../-trans-leg-dto/index.md)? = null) |

## Properties

| Name | Summary |
|---|---|
| [cLeg](c-leg.md) | [androidJvm]<br>val [cLeg](c-leg.md): [ContinuousLegDto](../-continuous-leg-dto/index.md)? = null |
| [id](id.md) | [androidJvm]<br>val [id](id.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null |
| [tLeg](t-leg.md) | [androidJvm]<br>val [tLeg](t-leg.md): [TimedLegDto](../-timed-leg-dto/index.md)? = null |
| [transLeg](trans-leg.md) | [androidJvm]<br>val [transLeg](trans-leg.md): [TransLegDto](../-trans-leg-dto/index.md)? = null |
