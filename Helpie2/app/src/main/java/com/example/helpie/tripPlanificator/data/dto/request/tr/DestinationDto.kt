package com.example.helpie.tripPlanificator.data.dto.request.tr

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "Destination")
data class DestinationDto(
    @Element(name = "PlaceRef")
    val placeRef: PlaceRefDto

    // could be asked, but not useful in our setting
    //@Element(name = "DepArrTime")
    //val depArrTime: String
)
