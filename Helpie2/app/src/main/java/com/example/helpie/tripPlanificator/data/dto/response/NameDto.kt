package com.example.helpie.tripPlanificator.data.dto.response

import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml


@Xml(name = "Name")
data class NameDto(
    @PropertyElement(name = "Text")
    val stationName: String
)