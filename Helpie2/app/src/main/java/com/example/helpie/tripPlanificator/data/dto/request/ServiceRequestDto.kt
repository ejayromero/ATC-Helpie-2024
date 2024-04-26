package com.example.helpie.tripPlanificator.data.dto.request

import com.example.helpie.tripPlanificator.data.dto.request.tr.TripRequestDto
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

/**
 * Created by Michael Ruppen on 08.04.2024
 */
@Xml(name = "ServiceRequest")
data class ServiceRequestDto(
    @PropertyElement(name = "siri:RequestTimestamp")
    val requestTimestamp: String?,

    @PropertyElement(name = "siri:RequestorRef")
    val requestorRef: String?,

    @Element(name = "OJPTripRequest")
    val tripRequest: TripRequestDto? = null
)