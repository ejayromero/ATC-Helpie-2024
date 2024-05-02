package com.example.helpie.tripPlanificator.domain.usecase

import android.util.Log
import com.example.helpie.tripPlanificator.data.dto.response.PlaceResultDto
import com.example.helpie.tripPlanificator.data.dto.response.TripResultDto
import com.example.helpie.tripPlanificator.domain.model.Response
import com.example.helpie.tripPlanificator.domain.repository.OjpRepository


/**
 * Created by Michael Ruppen on 08.04.2024
 */
class TripRequest(
    private val ojpRepository: OjpRepository
) {
    suspend operator fun invoke(
    ): Response<List<TripResultDto>> {
        Log.d("triprequest","invoke")
        return when (val response =
            ojpRepository.tripResult()) {
            is Response.Success -> response
            is Response.Error -> response //if needed, map the error to a predefined List of Errors
        }
    }
}
