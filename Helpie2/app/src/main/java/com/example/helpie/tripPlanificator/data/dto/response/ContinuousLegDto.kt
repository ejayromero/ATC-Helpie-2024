package com.example.helpie.tripPlanificator.data.dto.response

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml


@Xml(name = "ojp:ContinuousLeg")
data class ContinuousLegDto(
    @Element(name = "ojp:LegStart")
    val start : LegStartDto,

    @Element(name = "ojp:LegEnd")
    val end : LegEndDto,

    @Element(name = "ojp:Service")
    val service : ServiceDto,

    @PropertyElement(name = "ojp:TimeWindowStart")
    val timeStart: String,

    @PropertyElement(name = "ojp:TimeWindowEnd")
    val timeEnd: String,

    @PropertyElement(name = "ojp:Duration")
    val duration: String,

    @PropertyElement(name = "ojp:Length")
    val length: Double,

    )