//[app](../../../index.md)/[com.example.helpie.tripPlanificator.data.dto.response](../index.md)/[TripRequestResponseDto](index.md)

# TripRequestResponseDto

[androidJvm]\
@Xml(name = &quot;OJPTripDelivery&quot;)

data class [TripRequestResponseDto](index.md)(@Element(name = &quot;ojp:TripResponseContext&quot;)val context: [TripContextDto](../-trip-context-dto/index.md), @Element(name = &quot;ojp:TripResult&quot;)val tripResults: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[TripResultDto](../-trip-result-dto/index.md)&gt;?)

## Constructors

| | |
|---|---|
| [TripRequestResponseDto](-trip-request-response-dto.md) | [androidJvm]<br>constructor(@Element(name = &quot;ojp:TripResponseContext&quot;)context: [TripContextDto](../-trip-context-dto/index.md), @Element(name = &quot;ojp:TripResult&quot;)tripResults: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[TripResultDto](../-trip-result-dto/index.md)&gt;?) |

## Properties

| Name | Summary |
|---|---|
| [context](context.md) | [androidJvm]<br>val [context](context.md): [TripContextDto](../-trip-context-dto/index.md) |
| [tripResults](trip-results.md) | [androidJvm]<br>val [tripResults](trip-results.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[TripResultDto](../-trip-result-dto/index.md)&gt;? |
