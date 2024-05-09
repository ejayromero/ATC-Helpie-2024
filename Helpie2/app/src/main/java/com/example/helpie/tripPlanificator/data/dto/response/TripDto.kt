package com.example.helpie.tripPlanificator.data.dto.response

import com.example.helpie.tripPlanificator.data.dto.response.PlaceDto
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

/**
 * Created by Michael Ruppen on 08.04.2024
 */
@Xml(name = "Trip")
data class TripDto(
    @PropertyElement(name = "TripId")
    val id: String? = null,

    @PropertyElement(name = "Duration")
    val duration: String? = null,

    @PropertyElement(name = "StartTime")
    val start: String? = null,

    @PropertyElement(name = "EndTime")
    val end: String? = null,

    @Element(name = "TripLeg")
    val step: List<TripLegDto>?,

)
