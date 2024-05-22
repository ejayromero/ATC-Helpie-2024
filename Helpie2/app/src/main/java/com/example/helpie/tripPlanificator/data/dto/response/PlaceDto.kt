package com.example.helpie.tripPlanificator.data.dto.response
import com.example.helpie.tripPlanificator.data.dto.request.tr.PlaceRefDto
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

/**
 * Created by Michael Ruppen on 08.04.2024
 */
@Xml(name = "ojp:Places")
data class PlaceDto(
    @Element(name = "ojp:Location")
    val places: List<PlaceInfoDto>?,
)
