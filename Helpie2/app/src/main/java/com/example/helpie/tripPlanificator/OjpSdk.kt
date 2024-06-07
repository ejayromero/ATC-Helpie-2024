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
import com.google.android.gms.maps.model.LatLng
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
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration.Companion.hours


/**
 * SDK for making trip requests using the Open Journey Planner (OJP) service.
 *
 * @property baseUrl The base URL of the OJP service.
 * @property endpoint The specific endpoint for trip requests.
 * @property requesterReference The reference string used for identifying the requester.
 * @property token The authentication token for accessing the OJP service.
 */
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

    /**
     * Asynchronously requests trip information from the OJP service.
     *
     * @param here The current location represented by latitude and longitude coordinates.
     * @param target The target location for the trip.
     * @return The OJP response containing trip information.
     */
    suspend fun tripRequest(here : LatLng, target: Localisation): OjpDto  {
        val requestTime = Clock.System.now()
        Log.d("LE TEMPS", requestTime.toString())
        Log.d("remote","create request")
        Log.d("remote", here.longitude.toString())
        Log.d("remote", here.latitude.toString())
        val request = OjpDto(
            ojpRequest = OjpRequestDto(
                serviceRequest = ServiceRequestDto(
                    requestTimestamp = requestTime.toString(),
                    requestorRef = requesterReference,
                    tripRequest = TripRequestDto(
                        requestTimestamp = requestTime.toString(),
                        origin = OriginDto(
                            placeRef = PlaceRefDto(
                                //position = GeoPositionDto(here.longitude,here.latitude),
                                position = GeoPositionDto(6.537035659108079,46.515441573339686),
                                locationName = LocationNameDto("You")
                            ),
                            depArrTime = requestTime.toString() //"2024-05-02T10:43:02"
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

    /**
     * Sends an HTTP request to the OJP service and returns the response.
     *
     * @param url The URL to which the request is sent.
     * @param token The authentication token for accessing the service.
     * @param request The request object to be sent.
     * @return The response received from the service.
     */
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

    /**
     * Interprets the response received from the OJP service. (XML to kotlin class)
     *
     * @param response The response string received from the service.
     * @return The interpreted OJP response object.
     */
    private fun interpretResponse(
        response: String
    ): OjpDto {
        val source: BufferedSource = Buffer().writeUtf8(response)
        // Deserialize the response XML string back into an object

        return tikXml.read(source, OjpDto::class.java)
    }

}




