package com.example.helpie.tripPlanificator.data.dto.request

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml


@Xml(name = "OJPRequest")
data class OjpRequestDto(
    @Element(name = "siri:ServiceRequest")
    val serviceRequest: ServiceRequestDto?
)