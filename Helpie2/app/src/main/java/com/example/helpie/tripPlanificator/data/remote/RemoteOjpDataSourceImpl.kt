package com.example.helpie.tripPlanificator.data.remote

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.helpie.tripPlanificator.data.dto.request.OjpRequestDto
import com.example.helpie.tripPlanificator.data.dto.request.ServiceRequestDto
import com.example.helpie.tripPlanificator.utils.toInstantString
import com.example.helpie.tripPlanificator.data.dto.OjpDto
import com.example.helpie.tripPlanificator.data.dto.request.tr.DestinationDto
import com.example.helpie.tripPlanificator.data.dto.request.tr.GeoPositionDto
import com.example.helpie.tripPlanificator.data.dto.request.tr.LocationNameDto
import com.example.helpie.tripPlanificator.data.dto.request.tr.OriginDto
import com.example.helpie.tripPlanificator.data.dto.request.tr.ParamsDto
import com.example.helpie.tripPlanificator.data.dto.request.tr.PlaceGeoRefDto
import com.example.helpie.tripPlanificator.data.dto.request.tr.PlaceRefDto
import com.example.helpie.tripPlanificator.data.dto.request.tr.TripRequestDto
import com.example.helpie.tripPlanificator.domain.usecase.Initializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.joda.time.LocalDateTime



/**
 * Created by Michael Ruppen on 08.04.2024
 */
class RemoteOjpDataSourceImpl(
    private val ojpService: OjpService,
    private val initializer: Initializer
) : RemoteOjpDataSource {

    private val url: String
        get() = initializer.endpoint //+ initializer.baseURL


    //trajet fixe pour l'instant

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun tripRequest(): OjpDto = withContext(Dispatchers.IO)  {
        Log.d("remote",url)
        val requestTime = LocalDateTime.now()
        Log.d("remote","create request")
        val request = OjpDto(
            ojpRequest = OjpRequestDto(
                serviceRequest = ServiceRequestDto(
                    requestTimestamp = requestTime.toInstantString(),
                    requestorRef = initializer.requesterReference,
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


        return@withContext ojpService.sendRequest(url, request)
    }
}