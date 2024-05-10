package com.example.helpie.tripPlanificator.data.dto.response

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

/**
 * Created by Michael Ruppen on 08.04.2024
 */
@Xml(name = "ojp:ServiceDeparture")
data class DepartureDto(
    @PropertyElement(name = "ojp:TimetabledTime")
    val time: String,

    @PropertyElement(name = "ojp:EstimatedTime")
    val timeEstimate: String? = null,
)
