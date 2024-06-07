//[app](../../../index.md)/[com.example.helpie.tripPlanificator.data.dto.request](../index.md)/[ServiceRequestDto](index.md)

# ServiceRequestDto

[androidJvm]\
@Xml(name = &quot;ServiceRequest&quot;)

data class [ServiceRequestDto](index.md)(@PropertyElement(name = &quot;siri:RequestTimestamp&quot;)val requestTimestamp: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, @PropertyElement(name = &quot;siri:RequestorRef&quot;)val requestorRef: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, @Element(name = &quot;OJPTripRequest&quot;)val tripRequest: [TripRequestDto](../../com.example.helpie.tripPlanificator.data.dto.request.tr/-trip-request-dto/index.md)? = null)

## Constructors

| | |
|---|---|
| [ServiceRequestDto](-service-request-dto.md) | [androidJvm]<br>constructor(@PropertyElement(name = &quot;siri:RequestTimestamp&quot;)requestTimestamp: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, @PropertyElement(name = &quot;siri:RequestorRef&quot;)requestorRef: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, @Element(name = &quot;OJPTripRequest&quot;)tripRequest: [TripRequestDto](../../com.example.helpie.tripPlanificator.data.dto.request.tr/-trip-request-dto/index.md)? = null) |

## Properties

| Name | Summary |
|---|---|
| [requestorRef](requestor-ref.md) | [androidJvm]<br>val [requestorRef](requestor-ref.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [requestTimestamp](request-timestamp.md) | [androidJvm]<br>val [requestTimestamp](request-timestamp.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [tripRequest](trip-request.md) | [androidJvm]<br>val [tripRequest](trip-request.md): [TripRequestDto](../../com.example.helpie.tripPlanificator.data.dto.request.tr/-trip-request-dto/index.md)? = null |
