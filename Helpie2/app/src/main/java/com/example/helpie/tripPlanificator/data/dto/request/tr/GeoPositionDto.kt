package com.example.helpie.tripPlanificator.data.dto.request.tr

import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "GeoPosition")
data class GeoPositionDto(
    @PropertyElement(name = "siri:Longitude")
    val longitude: Double?,
    @PropertyElement(name = "siri:Latitude")
    val latitude: Double?
)