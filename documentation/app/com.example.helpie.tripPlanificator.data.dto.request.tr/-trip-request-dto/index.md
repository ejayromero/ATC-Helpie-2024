//[app](../../../index.md)/[com.example.helpie.tripPlanificator.data.dto.request.tr](../index.md)/[TripRequestDto](index.md)

# TripRequestDto

[androidJvm]\
@Xml(name = &quot;OJPTripRequest&quot;)

data class [TripRequestDto](index.md)(@PropertyElement(name = &quot;siri:RequestTimestamp&quot;)val requestTimestamp: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Element(name = &quot;Origin&quot;)val origin: [OriginDto](../-origin-dto/index.md), @Element(name = &quot;Destination&quot;)val destination: [DestinationDto](../-destination-dto/index.md), @Element(name = &quot;Params&quot;)val params: [ParamsDto](../-params-dto/index.md))

## Constructors

| | |
|---|---|
| [TripRequestDto](-trip-request-dto.md) | [androidJvm]<br>constructor(@PropertyElement(name = &quot;siri:RequestTimestamp&quot;)requestTimestamp: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Element(name = &quot;Origin&quot;)origin: [OriginDto](../-origin-dto/index.md), @Element(name = &quot;Destination&quot;)destination: [DestinationDto](../-destination-dto/index.md), @Element(name = &quot;Params&quot;)params: [ParamsDto](../-params-dto/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [destination](destination.md) | [androidJvm]<br>val [destination](destination.md): [DestinationDto](../-destination-dto/index.md) |
| [origin](origin.md) | [androidJvm]<br>val [origin](origin.md): [OriginDto](../-origin-dto/index.md) |
| [params](params.md) | [androidJvm]<br>val [params](params.md): [ParamsDto](../-params-dto/index.md) |
| [requestTimestamp](request-timestamp.md) | [androidJvm]<br>val [requestTimestamp](request-timestamp.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
