package com.example.helpie.tripPlanificator

import ch.opentransportdata.ojp.data.dto.response.PlaceResultDto
import com.example.helpie.tripPlanificator.di.context.OjpKoinContext
import com.example.helpie.tripPlanificator.domain.model.Response
import com.example.helpie.tripPlanificator.domain.usecase.Initializer
import com.example.helpie.tripPlanificator.domain.usecase.TripRequest
import timber.log.Timber

class OjpSdk(
    url: String,
    requesterReference: String,
    httpHeaders: HashMap<String, String> = hashMapOf()
) {
    init {
        Timber.i("Initialize SDK")
        OjpKoinContext.koinApp.koin.get<Initializer>().init(url, requesterReference, httpHeaders)
    }

    suspend fun TripRequest(
    ): Response<List<PlaceResultDto>> {
        return OjpKoinContext.koinApp.koin.get<TripRequest>().invoke()
    }

}