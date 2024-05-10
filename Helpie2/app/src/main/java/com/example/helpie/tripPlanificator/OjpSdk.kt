package com.example.helpie.tripPlanificator

import android.util.Log
import com.example.helpie.Localisation
import com.example.helpie.tripPlanificator.data.dto.OjpDto
import com.example.helpie.tripPlanificator.data.dto.request.OjpRequestDto
import com.example.helpie.tripPlanificator.data.dto.request.ServiceRequestDto
import com.example.helpie.tripPlanificator.data.dto.request.tr.DestinationDto
import com.example.helpie.tripPlanificator.data.dto.request.tr.GeoPositionDto
import com.example.helpie.tripPlanificator.data.dto.request.tr.LocationNameDto
import com.example.helpie.tripPlanificator.data.dto.request.tr.OriginDto
import com.example.helpie.tripPlanificator.data.dto.request.tr.ParamsDto
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
import okio.BufferedSource
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

    private val client = OkHttpClient()
    private val tikXml: TikXml = TikXml.Builder()
        .addTypeConverter(String::class.java, HtmlEscapeStringConverter())
        .exceptionOnUnreadXml(false)
        .build()

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

    suspend fun tripRequest(target: Localisation): OjpDto  {
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
                            placeRef = PlaceRefDto(
                                position = GeoPositionDto(6.537042242329045,46.51600017680952),
                                locationName = LocationNameDto("Manira Wokshop - Denges")
                            ),
                            depArrTime = requestTime.toInstantString() //"2024-05-02T10:43:02"
                        ),
                        destination = DestinationDto(
                            placeRef = PlaceRefDto(
                                position = GeoPositionDto(target.longitude,target.latitude),
                                locationName = LocationNameDto(target.destinationName)
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

        val result = interpretResponse(response)
        Log.d("service","Response interpreted: $result")

        return result
    }

    private suspend fun sendRequest(
        url: String,
        token: String,
        request: OjpDto
    ): String {
        // Create a buffer to capture the XML output
        val buffer = Buffer()
        // Serialize the request object to XML
        tikXml.write(buffer, request)
        // Convert the buffer to a string and print it
        val xmlString = buffer.readUtf8()
        Log.d("service","XML String: $xmlString")

        // Convert the XML string to a RequestBody
        val requestBody = xmlString.toRequestBody("application/xml".toMediaType())


        val toSend = Request.Builder()
            .url(url)
            .header("Authorization", token)
            .post(requestBody)
            .build()

        return withContext(Dispatchers.IO) {

            val response = client.newCall(toSend).execute()
            val responseBody = response.body?.string() ?: ""

            responseBody
        }
    }

    private fun interpretResponse(
        response: String
    ): OjpDto {
        val source: BufferedSource = Buffer().writeUtf8(response)
        // Deserialize the response XML string back into an object

        return tikXml.read(source, OjpDto::class.java)
    }

}




