package com.example.helpie.tripPlanificator.data.dto.response
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml


@Xml(name = "OJPTripDelivery")
data class TripRequestResponseDto(
    @Element(name = "ojp:TripResponseContext")
    val context: TripContextDto,
    @Element(name = "ojp:TripResult")
    val tripResults: List<TripResultDto>?, // ideally we want only 1
)
