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
    @Element(name = "TripId")
    val id: String? = null,

    @Element(name = "Duration")
    val duration: String? = null,

    @Element(name = "StartTime")
    val start: String? = null,

    @Element(name = "EndTime")
    val end: String? = null,

    @Element(name = "TripLeg")
    val step: List<TripLegDto>?,

)
