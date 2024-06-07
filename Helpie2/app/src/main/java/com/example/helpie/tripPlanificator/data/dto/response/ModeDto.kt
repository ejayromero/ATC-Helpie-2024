package com.example.helpie.tripPlanificator.data.dto.response

import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml


@Xml(name = "ojp:Mode")
data class ModeDto(
    @PropertyElement(name = "ojp:PtMode")
    val ptMode: String
)