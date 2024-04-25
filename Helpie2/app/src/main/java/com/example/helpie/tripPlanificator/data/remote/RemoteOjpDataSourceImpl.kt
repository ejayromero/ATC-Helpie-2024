package ch.opentransportdata.ojp.data.remote

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.helpie.tripPlanificator.data.dto.OjpDto
import ch.opentransportdata.ojp.data.dto.request.OjpRequestDto
import ch.opentransportdata.ojp.data.dto.request.ServiceRequestDto
import ch.opentransportdata.ojp.data.dto.request.lir.GeoRestrictionDto
import ch.opentransportdata.ojp.data.dto.request.lir.InitialInputDto
import ch.opentransportdata.ojp.data.dto.request.lir.LocationInformationRequestDto
import ch.opentransportdata.ojp.data.dto.request.lir.RestrictionsDto
import ch.opentransportdata.ojp.domain.usecase.Initializer
import ch.opentransportdata.ojp.utils.GeoLocationUtil.initWithGeoLocationAndBoxSize
import ch.opentransportdata.ojp.utils.toInstantString
import com.example.helpie.tripPlanificator.data.dto.request.tr.DestinationDto
import com.example.helpie.tripPlanificator.data.dto.request.tr.LocationNameDto
import com.example.helpie.tripPlanificator.data.dto.request.tr.OriginDto
import com.example.helpie.tripPlanificator.data.dto.request.tr.ParamsDto
import com.example.helpie.tripPlanificator.data.dto.request.tr.PlaceRefDto
import com.example.helpie.tripPlanificator.data.dto.request.tr.TripRequestDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.joda.time.LocalDateTime
import timber.log.Timber
import java.time.LocalDateTime

/**
 * Created by Michael Ruppen on 08.04.2024
 */
class RemoteOjpDataSourceImpl(
    private val ojpService: OjpService,
    private val initializer: Initializer
) : RemoteOjpDataSource {

    private val numberOfResults = 1
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
        return@withContext ojpService.locationInformationRequest(url, request)
    }
}