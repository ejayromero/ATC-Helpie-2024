package com.example.helpie.tripPlanificator.data.dto.response

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "ojp:Service")
data class ServiceDto(
    @PropertyElement(name = "ojp:IndividualMode")
    val individualMode: String? = null,

    @Element(name = "ojp:Mode")
    val mode: ModeDto? = null,

    @Element(name = "ojp:PublishedLineName")
    val line: LocationDto? = null,

    @Element(name = "ojp:DestinationText")
    val way: LocationDto? = null
)