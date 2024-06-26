package com.example.helpie.tripPlanificator.data.dto

import com.example.helpie.tripPlanificator.data.dto.request.OjpRequestDto
import com.example.helpie.tripPlanificator.data.dto.response.OjpResponseDto
import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

/**
 * Main XML management.
 *
 * This management method have been inspirated by the one created by Michael Ruppen on 08.04.2024
 * for the OJP SDK for android
 */
@Xml(
    name = "OJP",
    writeNamespaces = [
        "ojp=http://www.vdv.de/ojp",
        "siri=http://www.siri.org.uk/siri",
        "xsi=http://www.w3.org/2001/XMLSchema-instance",
        "xsd=http://www.w3.org/2001/XMLSchema"
    ]
)
data class OjpDto(
    @Element(name = "OJPRequest")
    val ojpRequest: OjpRequestDto? = null,

    @Element(name = "siri:OJPResponse")
    val ojpResponse: OjpResponseDto? = null,

    @Attribute(name = "version")
    val version: String = "1.0",
)


