package com.example.helpie.tripPlanificator.data.remote

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.helpie.tripPlanificator.data.dto.request.OjpRequestDto
import com.example.helpie.tripPlanificator.data.dto.request.ServiceRequestDto
import ch.opentransportdata.ojp.data.remote.OjpService
import ch.opentransportdata.ojp.utils.toInstantString
import com.example.helpie.tripPlanificator.data.dto.OjpDto
import com.example.helpie.tripPlanificator.data.dto.request.tr.DestinationDto
import com.example.helpie.tripPlanificator.data.dto.request.tr.LocationNameDto
import com.example.helpie.tripPlanificator.data.dto.request.tr.OriginDto
import com.example.helpie.tripPlanificator.data.dto.request.tr.ParamsDto
import com.example.helpie.tripPlanificator.data.dto.request.tr.PlaceRefDto
import com.example.helpie.tripPlanificator.data.dto.request.tr.TripRequestDto
import com.example.helpie.tripPlanificator.domain.usecase.Initializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.joda.time.LocalDateTime
import timber.log.Timber



/**
 * Created by Michael Ruppen on 08.04.2024
 */
class RemoteOjpDataSourceImpl(
    private val ojpService: OjpService,
    private val initializer: Initializer
) : RemoteOjpDataSource {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun tripRequest(): OjpDto = withContext(Dispatchers.IO) {
        val requestTime = LocalDateTime.now()

        val request = OjpDto(
            ojpRequest = OjpRequestDto(
                serviceRequest = ServiceRequestDto(
                    requestTimestamp = requestTime.toInstantString(),
                    requestorRef = initializer.requesterReference,
                    tripRequest = TripRequestDto(
                        requestTimestamp = requestTime.toInstantString(),
                        origin = OriginDto(
                            placeRef = PlaceRefDto(
                                stopPlaceRef = "",
                                locationName = LocationNameDto()
                            ),
                            depArrTime = ""
                        ),
                        destination = DestinationDto(
                            placeRef = PlaceRefDto(
                                stopPlaceRef = "",
                                locationName = LocationNameDto()
                            )
                        ),
                        params = ParamsDto()
                    )
                )
            )
        )

        Timber.d("Request object: $request")
        return@withContext ojpService.tripRequest(initializer.url, request)
    }
}