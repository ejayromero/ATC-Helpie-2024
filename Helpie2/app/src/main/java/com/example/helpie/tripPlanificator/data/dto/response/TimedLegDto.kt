package com.example.helpie.tripPlanificator.data.dto.response

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml


@Xml(name = "ojp:TimedLeg")
data class TimedLegDto(
    @Element(name = "ojp:LegBoard")
    val board : LegBoardDto? = null,

    @Element(name = "ojp:LegAlight")
    val alight : LegAlightDto? = null,

    //about the transport
    @Element(name = "ojp:Service")
    val service : ServiceDto,


    )