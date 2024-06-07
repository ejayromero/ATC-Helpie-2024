package com.example.helpie.tripPlanificator.data.dto.response

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml


@Xml(name = "ServiceDelivery")
data class ServiceDeliveryDto(
    @PropertyElement(name = "siri:ResponseTimestamp")
    val responseTimestamp: String? = null,

    @PropertyElement(name = "siri:ProducerRef")
    val producerRef: String? = null,

    @PropertyElement(name = "siri:ResponseMessageIdentifier")
    val respMessageRef: String? = null,

    @PropertyElement(name = "siri:Status")
    val status: Boolean? = null,

    @Element(name = "ojp:OJPTripDelivery")
    val tripDelivery: TripRequestResponseDto? = null,
)
