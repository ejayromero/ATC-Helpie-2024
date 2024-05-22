package com.example.helpie.tripPlanificator.data.dto.response

import com.example.helpie.tripPlanificator.data.dto.request.tr.GeoPositionDto
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "ojp:Location")
data class PlaceInfoDto(
    @Element(name = "ojp:LocationName")
    val locationName: LocationDto? = null,

    @Element(name = "ojp:GeoPosition")
    val position: GeoPositionDto? = null,
)
