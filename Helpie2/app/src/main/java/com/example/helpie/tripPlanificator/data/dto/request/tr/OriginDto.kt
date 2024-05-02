package com.example.helpie.tripPlanificator.data.dto.request.tr

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml


@Xml(name = "Origin")
data class OriginDto(
    @Element(name = "PlaceRef")
    val placeRef: PlaceGeoRefDto,

    @Element(name = "DepArrTime")
    val depArrTime: String
)
