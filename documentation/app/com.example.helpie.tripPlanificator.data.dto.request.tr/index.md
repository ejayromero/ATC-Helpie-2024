//[app](../../index.md)/[com.example.helpie.tripPlanificator.data.dto.request.tr](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [DestinationDto](-destination-dto/index.md) | [androidJvm]<br>@Xml(name = &quot;Destination&quot;)<br>data class [DestinationDto](-destination-dto/index.md)(@Element(name = &quot;PlaceRef&quot;)val placeRef: [PlaceRefDto](-place-ref-dto/index.md)) |
| [GeoPositionDto](-geo-position-dto/index.md) | [androidJvm]<br>@Xml(name = &quot;GeoPosition&quot;)<br>data class [GeoPositionDto](-geo-position-dto/index.md)(@PropertyElement(name = &quot;siri:Longitude&quot;)val longitude: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)?, @PropertyElement(name = &quot;siri:Latitude&quot;)val latitude: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)?) |
| [LocationNameDto](-location-name-dto/index.md) | [androidJvm]<br>@Xml(name = &quot;LocationName&quot;)<br>data class [LocationNameDto](-location-name-dto/index.md)(@PropertyElement(name = &quot;Text&quot;)val name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null) |
| [OriginDto](-origin-dto/index.md) | [androidJvm]<br>@Xml(name = &quot;Origin&quot;)<br>data class [OriginDto](-origin-dto/index.md)(@Element(name = &quot;PlaceRef&quot;)val placeRef: [PlaceRefDto](-place-ref-dto/index.md), @PropertyElement(name = &quot;DepArrTime&quot;)val depArrTime: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
| [ParamsDto](-params-dto/index.md) | [androidJvm]<br>@Xml(name = &quot;Params&quot;)<br>data class [ParamsDto](-params-dto/index.md)(@PropertyElement(name = &quot;IncludeTrackSections&quot;)val track: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, @PropertyElement(name = &quot;IncludeTurnDescription&quot;)val turn: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, @PropertyElement(name = &quot;IncludeIntermediateStops&quot;)val intermediate: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null) |
| [PlaceRefDto](-place-ref-dto/index.md) | [androidJvm]<br>@Xml(name = &quot;PlaceRef&quot;)<br>data class [PlaceRefDto](-place-ref-dto/index.md)(@Element(name = &quot;GeoPosition&quot;)val position: [GeoPositionDto](-geo-position-dto/index.md)? = null, @PropertyElement(name = &quot;StopPlaceRef&quot;)val stopPlaceRef: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, @Element(name = &quot;LocationName&quot;)val locationName: [LocationNameDto](-location-name-dto/index.md)) |
| [TripRequestDto](-trip-request-dto/index.md) | [androidJvm]<br>@Xml(name = &quot;OJPTripRequest&quot;)<br>data class [TripRequestDto](-trip-request-dto/index.md)(@PropertyElement(name = &quot;siri:RequestTimestamp&quot;)val requestTimestamp: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Element(name = &quot;Origin&quot;)val origin: [OriginDto](-origin-dto/index.md), @Element(name = &quot;Destination&quot;)val destination: [DestinationDto](-destination-dto/index.md), @Element(name = &quot;Params&quot;)val params: [ParamsDto](-params-dto/index.md)) |