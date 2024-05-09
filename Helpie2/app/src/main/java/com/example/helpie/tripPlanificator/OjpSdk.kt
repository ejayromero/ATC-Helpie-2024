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
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.converter.htmlescape.HtmlEscapeStringConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okio.Buffer
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


        val client = OkHttpClient()

        val tikXml = TikXml.Builder()
            .addTypeConverter(String::class.java, HtmlEscapeStringConverter())
            .exceptionOnUnreadXml(false)
            .build()

        Log.d("service","buffer")
        // Create a buffer to capture the XML output
        val buffer = Buffer()
        Log.d("service","write")
        // Serialize the request object to XML
        tikXml.write(buffer, request)
        Log.d("service","to string")
        // Convert the buffer to a string and print it
        val xmlString = buffer.readUtf8()
        Log.d("service","XML String: $xmlString")

        println(xmlString)

        // Convert the XML string to a RequestBody
        val requestBody = xmlString.toRequestBody("application/xml".toMediaType())


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




