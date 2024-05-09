package com.example.helpie.tripPlanificator.data.dto.response

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

/**
 * Created by Michael Ruppen on 08.04.2024
 */
@Xml(name = "Trip")
data class TripDto(
    @PropertyElement(name = "ojp:TripId")
    val id: String? = null,

    @PropertyElement(name = "ojp:Duration")
    val duration: String? = null,

    @PropertyElement(name = "ojp:StartTime")
    val start: String? = null,

    @PropertyElement(name = "ojp:EndTime")
    val end: String? = null,

    @Element(name = "ojp:TripLeg")
    val step: List<TripLegDto>? = null,

)
