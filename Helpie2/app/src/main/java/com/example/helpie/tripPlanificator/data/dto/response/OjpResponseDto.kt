package com.example.helpie.tripPlanificator.data.dto.response

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml


@Xml(name = "OJPResponse")
data class OjpResponseDto(
    @Element(name = "siri:ServiceDelivery")
    val serviceDelivery: ServiceDeliveryDto?
)
