package com.example.helpie.tripPlanificator.data.dto.response

import com.example.helpie.tripPlanificator.data.dto.response.PlaceDto
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

/**
 * Created by Michael Ruppen on 08.04.2024
 */
@Xml(name = "TripResult")
data class TripResultDto(
    @Element(name = "ResultId")
    val id: String? = null,

    @Element(name = "Trip")
    val trip: TripDto,

)
