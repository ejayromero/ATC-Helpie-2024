//[app](../../../index.md)/[com.example.helpie.tripPlanificator.data.dto.response](../index.md)/[ServiceDeliveryDto](index.md)

# ServiceDeliveryDto

[androidJvm]\
@Xml(name = &quot;ServiceDelivery&quot;)

data class [ServiceDeliveryDto](index.md)(@PropertyElement(name = &quot;siri:ResponseTimestamp&quot;)val responseTimestamp: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, @PropertyElement(name = &quot;siri:ProducerRef&quot;)val producerRef: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, @PropertyElement(name = &quot;siri:ResponseMessageIdentifier&quot;)val respMessageRef: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, @PropertyElement(name = &quot;siri:Status&quot;)val status: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, @Element(name = &quot;ojp:OJPTripDelivery&quot;)val tripDelivery: [TripRequestResponseDto](../-trip-request-response-dto/index.md)? = null)

## Constructors

| | |
|---|---|
| [ServiceDeliveryDto](-service-delivery-dto.md) | [androidJvm]<br>constructor(@PropertyElement(name = &quot;siri:ResponseTimestamp&quot;)responseTimestamp: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, @PropertyElement(name = &quot;siri:ProducerRef&quot;)producerRef: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, @PropertyElement(name = &quot;siri:ResponseMessageIdentifier&quot;)respMessageRef: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, @PropertyElement(name = &quot;siri:Status&quot;)status: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, @Element(name = &quot;ojp:OJPTripDelivery&quot;)tripDelivery: [TripRequestResponseDto](../-trip-request-response-dto/index.md)? = null) |

## Properties

| Name | Summary |
|---|---|
| [producerRef](producer-ref.md) | [androidJvm]<br>val [producerRef](producer-ref.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
| [respMessageRef](resp-message-ref.md) | [androidJvm]<br>val [respMessageRef](resp-message-ref.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
| [responseTimestamp](response-timestamp.md) | [androidJvm]<br>val [responseTimestamp](response-timestamp.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
| [status](status.md) | [androidJvm]<br>val [status](status.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null |
| [tripDelivery](trip-delivery.md) | [androidJvm]<br>val [tripDelivery](trip-delivery.md): [TripRequestResponseDto](../-trip-request-response-dto/index.md)? = null |
