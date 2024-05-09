package com.example.helpie.tripPlanificator.data.dto.request.tr

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "PlaceRef")
data class PlaceGeoRefDto(
    @Element(name = "GeoPosition")
    val position: GeoPositionDto,

    @Element(name = "LocationName")
    val locationName: LocationNameDto,
)
