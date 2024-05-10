package com.example.helpie.tripPlanificator.data.dto.response

import com.example.helpie.tripPlanificator.data.dto.request.tr.GeoPositionDto
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "ojp:LegEnd")
data class LegEndDto(
    @Element(name = "ojp:GeoPosition")
    val position: GeoPositionDto? = null,

    @Element(name = "ojp:LocationName")
    val name: LocationDto? = null,

    @PropertyElement(name = "siri:StopPointRef")
    val stopPlaceRef: String? = null,
)
