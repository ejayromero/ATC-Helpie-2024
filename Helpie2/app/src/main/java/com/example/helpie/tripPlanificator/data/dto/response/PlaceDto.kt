package com.example.helpie.tripPlanificator.data.dto.response

import com.example.helpie.tripPlanificator.data.dto.request.tr.GeoPositionDto
import ch.opentransportdata.ojp.data.dto.response.Mode
import ch.opentransportdata.ojp.data.dto.response.NameDto
import ch.opentransportdata.ojp.data.dto.response.StopPlaceDto
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

/**
 * Created by Michael Ruppen on 08.04.2024
 */
@Xml(name = "Place")
data class PlaceDto(
    @Element(name = "StopPlace")
    val stopPlace: StopPlaceDto?,
    @Element(name = "Name")
    val name: NameDto,
    @Element(name = "GeoPosition")
    val position: GeoPositionDto,
    @Element(name = "Mode")
    val mode: Mode,
)

