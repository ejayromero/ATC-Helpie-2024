package com.example.helpie.tripPlanificator.data.dto.request.tr

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "PlaceRef")
data class PlaceRefDto(
    @Element(name = "StopPlaceRef")
    val stopPlaceRef: String,

    @Element(name = "LocationName")
    val locationName: LocationNameDto,
)
