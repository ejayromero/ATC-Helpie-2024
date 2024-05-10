package com.example.helpie.tripPlanificator.data.dto.response

import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "ojp:LocationName")
data class LocationDto(
    @PropertyElement(name = "ojp:Text")
    val name: String? = null,
)
