package com.example.helpie.tripPlanificator.data.dto.response

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

/**
 * Created by Michael Ruppen on 08.04.2024
 */
@Xml(name = "PlaceResult")
data class PlaceResultDto(

    @Element(name = "TripResult")
    val place: PlaceDto,

)
