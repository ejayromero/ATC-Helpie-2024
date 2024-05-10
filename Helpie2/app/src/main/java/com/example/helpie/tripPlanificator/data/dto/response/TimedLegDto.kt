package com.example.helpie.tripPlanificator.data.dto.response

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

/**
 * Created by Michael Ruppen on 08.04.2024
 */
@Xml(name = "ojp:TimedLeg")
data class TimedLegDto(
    @Element(name = "ojp:LegBoard")
    val board : LegBoardDto? = null,

    @Element(name = "ojp:LegLegAlight")
    val alight : LegAlightDto? = null,

    //about the transport
    @Element(name = "ojp:Service")
    val service : ServiceDto,


)
