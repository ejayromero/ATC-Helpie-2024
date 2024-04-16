package com.example.helpie.network
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody


const val url = "https://api.opentransportdata.swiss/ojp2020"
const val token = "eyJvcmciOiI2NDA2NTFhNTIyZmEwNTAwMDEyOWJiZTEiLCJpZCI6IjAyZmIwZmM2OWQxMDRkNjY4NWNiZjQ0NWI1MjQyZjgxIiwiaCI6Im11cm11cjEyOCJ9"
var responseData = "Default Value"


suspend fun fetchData(): String {
    val xml = """
        <?xml version="1.0" encoding="utf-8"?>
        <OJP xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="http://www.siri.org.uk/siri" version="1.0" xmlns:ojp="http://www.vdv.de/ojp" xsi:schemaLocation="http://www.siri.org.uk/siri ../ojp-xsd-v1.0/OJP.xsd">
            <OJPRequest>
                <ServiceRequest>
                    <RequestTimestamp>2024-04-15T14:02:53.036Z</RequestTimestamp>
                    <RequestorRef>API-Explorer</RequestorRef>
                    <ojp:OJPTripRequest>
                        <RequestTimestamp>2024-04-15T14:02:53.036Z</RequestTimestamp>
                        <ojp:Origin>
                            <ojp:PlaceRef>
                                <ojp:StopPlaceRef>8507000</ojp:StopPlaceRef>
                                <ojp:LocationName>
                                    <ojp:Text>Bern</ojp:Text>
                                </ojp:LocationName>
                            </ojp:PlaceRef>
                            <ojp:DepArrTime>2024-04-15T16:02:53</ojp:DepArrTime>
                        </ojp:Origin>
                        <ojp:Destination>
                            <ojp:PlaceRef>
                                <ojp:StopPlaceRef>8503000</ojp:StopPlaceRef>
                                <ojp:LocationName>
                                    <ojp:Text>Zürich</ojp:Text>
                                </ojp:LocationName>
                            </ojp:PlaceRef>
                        </ojp:Destination>
                        <ojp:Params>
                            <ojp:IncludeTrackSections></ojp:IncludeTrackSections>
                            <ojp:IncludeTurnDescription></ojp:IncludeTurnDescription>
                            <ojp:IncludeIntermediateStops></ojp:IncludeIntermediateStops>
                        </ojp:Params>
                    </ojp:OJPTripRequest>
                </ServiceRequest>
            </OJPRequest>
        </OJP>
    """.trimIndent()

    val client = OkHttpClient()

    val requestBody = xml.toRequestBody("application/xml".toMediaType())

    val request = Request.Builder()
        .url(url)
        .header("Authorization", token)
        .post(requestBody)
        .build()

    return withContext(Dispatchers.IO) {
        val response = client.newCall(request).execute()
        response.body?.string() ?: ""
    }
}