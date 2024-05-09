package com.example.helpie.tripPlanificator.data.dto.request.tr

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "OJPTripRequest")
data class TripRequestDto(
    @PropertyElement(name = "siri:RequestTimestamp")
    val requestTimestamp: String,

    @Element(name = "Origin")
    val origin: OriginDto,

    @Element(name = "Destination")
    val destination: DestinationDto,

    @Element(name = "Params")
    val params: ParamsDto
)
