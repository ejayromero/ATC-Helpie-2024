package com.example.helpie.tripPlanificator.data.dto.response

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

/**
 * Created by Michael Ruppen on 08.04.2024
 */
@Xml(name = "ojp:Service")
data class ServiceDto(
    @PropertyElement(name = "ojp:IndividualMode")
    val individualMode: String? = null,

    @Element(name = "ojp:Mode")
    val mode: ModeDto? = null
)