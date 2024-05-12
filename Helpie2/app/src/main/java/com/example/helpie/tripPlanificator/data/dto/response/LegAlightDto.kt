package com.example.helpie.tripPlanificator.data.dto.response

import com.example.helpie.tripPlanificator.data.dto.request.tr.GeoPositionDto
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "ojp:LegAlight")
data class LegAlightDto(
    @PropertyElement(name = "siri:StopPointRef")
    val stopPlaceRef: String? = null,

    @Element(name = "ojp:StopPointName")
    val name: LocationDto? = null,

    @Element(name = "ojp:PlannedQuay")
    val quay : LocationDto? = null,

    @Element(name = "ojp:ServiceArrival")
    val time : DepartureDto? = null,

    )