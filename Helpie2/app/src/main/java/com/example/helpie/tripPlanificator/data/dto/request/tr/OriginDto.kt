package com.example.helpie.tripPlanificator.data.dto.request.tr

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml


@Xml(name = "Origin")
data class OriginDto(
    @Element(name = "PlaceRef")
    val placeRef: PlaceRefDto,

    @PropertyElement(name = "DepArrTime")
    val depArrTime: String
)
