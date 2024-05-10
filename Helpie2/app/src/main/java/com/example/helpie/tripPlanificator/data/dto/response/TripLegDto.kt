package com.example.helpie.tripPlanificator.data.dto.response

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

/**
 * Created by Michael Ruppen on 08.04.2024
 */
@Xml(name = "ojp:TripLeg")
data class TripLegDto(
    @PropertyElement(name = "ojp:LegId")
    val id: Int? = null,
    //will have only 1 type of leg
    @Element(name = "ojp:ContinuousLeg")
    val cLeg : ContinuousLegDto? = null,

    @Element(name = "ojp:TimedLeg")
    val tLeg : TimedLegDto? = null,

    @Element(name = "ojp:TransferLeg")
    val transLeg : TransLegDto? = null,
)
