//[app](../../../index.md)/[com.example.helpie.tripPlanificator.data.dto](../index.md)/[OjpDto](index.md)

# OjpDto

[androidJvm]\
@Xml(name = &quot;OJP&quot;, writeNamespaces = [&quot;ojp=http://www.vdv.de/ojp&quot;, &quot;siri=http://www.siri.org.uk/siri&quot;, &quot;xsi=http://www.w3.org/2001/XMLSchema-instance&quot;, &quot;xsd=http://www.w3.org/2001/XMLSchema&quot;])

data class [OjpDto](index.md)(@Element(name = &quot;OJPRequest&quot;)val ojpRequest: [OjpRequestDto](../../com.example.helpie.tripPlanificator.data.dto.request/-ojp-request-dto/index.md)? = null, @Element(name = &quot;siri:OJPResponse&quot;)val ojpResponse: [OjpResponseDto](../../com.example.helpie.tripPlanificator.data.dto.response/-ojp-response-dto/index.md)? = null, @Attribute(name = &quot;version&quot;)val version: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;1.0&quot;)

Main XML management.

This management method have been inspirated by the one created by Michael Ruppen on 08.04.2024 for the OJP SDK for android

## Constructors

| | |
|---|---|
| [OjpDto](-ojp-dto.md) | [androidJvm]<br>constructor(@Element(name = &quot;OJPRequest&quot;)ojpRequest: [OjpRequestDto](../../com.example.helpie.tripPlanificator.data.dto.request/-ojp-request-dto/index.md)? = null, @Element(name = &quot;siri:OJPResponse&quot;)ojpResponse: [OjpResponseDto](../../com.example.helpie.tripPlanificator.data.dto.response/-ojp-response-dto/index.md)? = null, @Attribute(name = &quot;version&quot;)version: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;1.0&quot;) |

## Properties

| Name | Summary |
|---|---|
| [ojpRequest](ojp-request.md) | [androidJvm]<br>val [ojpRequest](ojp-request.md): [OjpRequestDto](../../com.example.helpie.tripPlanificator.data.dto.request/-ojp-request-dto/index.md)? = null |
| [ojpResponse](ojp-response.md) | [androidJvm]<br>val [ojpResponse](ojp-response.md): [OjpResponseDto](../../com.example.helpie.tripPlanificator.data.dto.response/-ojp-response-dto/index.md)? = null |
| [version](version.md) | [androidJvm]<br>val [version](version.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
