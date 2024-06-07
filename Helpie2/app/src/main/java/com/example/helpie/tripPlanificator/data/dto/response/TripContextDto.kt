package com.example.helpie.tripPlanificator.data.dto.response
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml


@Xml(name = "ojp:TripResponseContext")
data class TripContextDto(
    @Element(name = "ojp:Places")
    val place: PlaceDto? = null,
)
