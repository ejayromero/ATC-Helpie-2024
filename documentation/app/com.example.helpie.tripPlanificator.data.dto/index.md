//[app](../../index.md)/[com.example.helpie.tripPlanificator.data.dto](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [OjpDto](-ojp-dto/index.md) | [androidJvm]<br>@Xml(name = &quot;OJP&quot;, writeNamespaces = [&quot;ojp=http://www.vdv.de/ojp&quot;, &quot;siri=http://www.siri.org.uk/siri&quot;, &quot;xsi=http://www.w3.org/2001/XMLSchema-instance&quot;, &quot;xsd=http://www.w3.org/2001/XMLSchema&quot;])<br>data class [OjpDto](-ojp-dto/index.md)(@Element(name = &quot;OJPRequest&quot;)val ojpRequest: [OjpRequestDto](../com.example.helpie.tripPlanificator.data.dto.request/-ojp-request-dto/index.md)? = null, @Element(name = &quot;siri:OJPResponse&quot;)val ojpResponse: [OjpResponseDto](../com.example.helpie.tripPlanificator.data.dto.response/-ojp-response-dto/index.md)? = null, @Attribute(name = &quot;version&quot;)val version: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;1.0&quot;)<br>Main XML management. |
