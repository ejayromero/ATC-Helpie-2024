package com.example.helpie.tripPlanificator.data.dto.response
import com.example.helpie.tripPlanificator.data.dto.request.tr.PlaceRefDto
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

/**
 * Created by Michael Ruppen on 08.04.2024
 */
@Xml(name = "ojp:TripResponseContext")
data class TripContextDto(
    @Element(name = "ojp:Places")
    val place: PlaceDto,
)
