package com.example.helpie.tripPlanificator

import android.util.Log
import com.example.helpie.tripPlanificator.data.dto.OjpDto
import com.example.helpie.tripPlanificator.data.dto.request.OjpRequestDto
import com.example.helpie.tripPlanificator.data.dto.request.ServiceRequestDto
import com.example.helpie.tripPlanificator.data.dto.request.tr.DestinationDto
import com.example.helpie.tripPlanificator.data.dto.request.tr.GeoPositionDto
import com.example.helpie.tripPlanificator.data.dto.request.tr.LocationNameDto
import com.example.helpie.tripPlanificator.data.dto.request.tr.OriginDto
import com.example.helpie.tripPlanificator.data.dto.request.tr.ParamsDto
import com.example.helpie.tripPlanificator.data.dto.request.tr.PlaceGeoRefDto
import com.example.helpie.tripPlanificator.data.dto.request.tr.PlaceRefDto
import com.example.helpie.tripPlanificator.data.dto.request.tr.TripRequestDto
import com.example.helpie.tripPlanificator.utils.toInstantString
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.joda.time.LocalDateTime

class OjpSdk(
    baseUrl: String,
    endpoint: String,
    requesterReference: String,
    token: String
) {
    private var baseUrl: String
    private var endpoint: String
    private var requesterReference: String
    private var token: String
    init {
        Log.d("ojk","initiating ")
        this.baseUrl = baseUrl
        this.endpoint = endpoint
        this.requesterReference = requesterReference + "_" + ANDROID_SDK
        this.token = token
    }

    companion object {
        private const val ANDROID_SDK = "ANDROID_SDK"
    }

    suspend fun tripRequest(): String  {
        Log.d("remote",endpoint)
        val requestTime = LocalDateTime.now()
        Log.d("remote","create request")
        val request = OjpDto(
            ojpRequest = OjpRequestDto(
                serviceRequest = ServiceRequestDto(
                    requestTimestamp = requestTime.toInstantString(),
                    requestorRef = requesterReference,
                    tripRequest = TripRequestDto(
                        requestTimestamp = requestTime.toInstantString(),
                        origin = OriginDto(
                            placeRef = PlaceGeoRefDto(
                                position = GeoPositionDto(7.446683,46.928306),
                                locationName = LocationNameDto("Wabern bei Bern")
                            ),
                            depArrTime = "2024-05-02T10:43:02"
                        ),
                        destination = DestinationDto(
                            placeRef = PlaceRefDto(
                                stopPlaceRef = "8503000",
                                locationName = LocationNameDto("Zürich")
                            )
                        ),
                        params = ParamsDto()
                    )
                )
            )
        )
        Log.d("request","Request object: $request")

        val response = sendRequest(endpoint, token, request)
        Log.d("service","Response object: $response")
        return response
    }

    private suspend fun sendRequest(
        url: String,
        token: String,
        request: OjpDto
    ): String {
        Log.d("service","go")

        val xml = """
        <?xml version="1.0" encoding="utf-8"?>
<OJP xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="http://www.siri.org.uk/siri" version="1.0" xmlns:ojp="http://www.vdv.de/ojp" xsi:schemaLocation="http://www.siri.org.uk/siri ../ojp-xsd-v1.0/OJP.xsd">
    <OJPRequest>
        <ServiceRequest>
            <RequestTimestamp>2024-05-02T08:43:07.587Z</RequestTimestamp>
            <RequestorRef>Helpie</RequestorRef>
            <ojp:OJPTripRequest>
                <RequestTimestamp>2024-05-02T08:43:07.587Z</RequestTimestamp>
                <ojp:Origin>
                    <ojp:PlaceRef>
                        <ojp:GeoPosition>
                            <Longitude>7.446683</Longitude>
                            <Latitude>46.928306</Latitude>
                        </ojp:GeoPosition>
                        <ojp:LocationName>
                            <ojp:Text>Wabern bei Bern</ojp:Text>
                        </ojp:LocationName>
                    </ojp:PlaceRef>
                    <ojp:DepArrTime>2024-05-02T10:43:02</ojp:DepArrTime>
                </ojp:Origin>
                <ojp:Destination>
                    <ojp:PlaceRef>
                        <ojp:StopPlaceRef>8503000</ojp:StopPlaceRef>
                        <ojp:LocationName>
                            <ojp:Text>Zürich</ojp:Text>
                        </ojp:LocationName>
                    </ojp:PlaceRef>
                </ojp:Destination>
            </ojp:OJPTripRequest>
        </ServiceRequest>
    </OJPRequest>
</OJP>
    """.trimIndent()

        val client = OkHttpClient()

        val gson = GsonBuilder().create()

        val xmlString = gson.toJson(request.ojpRequest)

        //val requestBody = xmlString.toRequestBody("application/xml".toMediaType())
        val requestBody = xml.toRequestBody("application/xml".toMediaType())

        val tosend = Request.Builder()
            .url(url)
            .header("Authorization", token)
            .post(requestBody)
            .build()



        return withContext(Dispatchers.IO) {
            val response = client.newCall(tosend).execute()
            response.body?.string() ?: ""
        }
    }

}