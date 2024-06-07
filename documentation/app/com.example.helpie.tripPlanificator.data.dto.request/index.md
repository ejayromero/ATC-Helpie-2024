//[app](../../index.md)/[com.example.helpie.tripPlanificator.data.dto.request](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [OjpRequestDto](-ojp-request-dto/index.md) | [androidJvm]<br>@Xml(name = &quot;OJPRequest&quot;)<br>data class [OjpRequestDto](-ojp-request-dto/index.md)(@Element(name = &quot;siri:ServiceRequest&quot;)val serviceRequest: [ServiceRequestDto](-service-request-dto/index.md)?) |
| [ServiceRequestDto](-service-request-dto/index.md) | [androidJvm]<br>@Xml(name = &quot;ServiceRequest&quot;)<br>data class [ServiceRequestDto](-service-request-dto/index.md)(@PropertyElement(name = &quot;siri:RequestTimestamp&quot;)val requestTimestamp: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, @PropertyElement(name = &quot;siri:RequestorRef&quot;)val requestorRef: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, @Element(name = &quot;OJPTripRequest&quot;)val tripRequest: [TripRequestDto](../com.example.helpie.tripPlanificator.data.dto.request.tr/-trip-request-dto/index.md)? = null) |
