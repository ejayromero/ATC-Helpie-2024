package com.example.helpie.tripPlanificator.data.dto.response

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml


@Xml(name = "TripResult")
data class TripResultDto(
    @PropertyElement(name = "ojp:ResultId")
    val id: String? = null,

    @Element(name = "ojp:Trip")
    val trip: TripDto,

)
