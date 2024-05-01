package com.example.helpie.tripPlanificator

import android.util.Log
import ch.opentransportdata.ojp.data.dto.response.PlaceResultDto
import com.example.helpie.tripPlanificator.di.context.OjpKoinContext
import com.example.helpie.tripPlanificator.domain.model.Response
import com.example.helpie.tripPlanificator.domain.usecase.Initializer
import com.example.helpie.tripPlanificator.domain.usecase.TripRequest

class OjpSdk(
    baseUrl: String,
    endpoint: String,
    requesterReference: String,
    httpHeaders: HashMap<String, String> = hashMapOf()
) {
    init {
        Log.d("OJL","Initialize SDK")
        OjpKoinContext.koinApp.koin.get<Initializer>().init(baseUrl, endpoint, requesterReference, httpHeaders)
    }

    suspend fun TripRequest(
    ): Response<List<PlaceResultDto>> {
        Log.d("helpie","triprequest")
        return OjpKoinContext.koinApp.koin.get<TripRequest>().invoke()
    }

}