package com.example.helpie.tripPlanificator.data.dto.request.tr

import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "LocationName")
data class LocationNameDto(
    @PropertyElement(name = "Text")
    val name: String? = null,
)
