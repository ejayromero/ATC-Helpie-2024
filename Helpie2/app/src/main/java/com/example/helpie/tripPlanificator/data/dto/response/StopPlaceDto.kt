package com.example.helpie.tripPlanificator.data.dto.response

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml


@Xml(name = "StopPlace")
data class StopPlaceDto(
    @PropertyElement(name = "StopPlaceRef")
    val stopPlaceRef: String,
    @Element(name = "StopPlaceName")
    val name: NameDto?,
    @PropertyElement(name = "TopographicPlaceRef")
    val topographicPlaceRef: String?,
)
