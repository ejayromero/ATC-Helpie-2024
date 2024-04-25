package com.example.helpie.tripPlanificator.data.dto.request.tr

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "LocationName")
data class LocationNameDto(
    @Element(name = "Text")
    val name: String? = null,
)
