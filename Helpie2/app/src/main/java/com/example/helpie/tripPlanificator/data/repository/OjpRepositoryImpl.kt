package com.example.helpie.tripPlanificator.data.repository

import android.util.Log
import com.example.helpie.tripPlanificator.data.dto.response.PlaceResultDto
import com.example.helpie.tripPlanificator.data.dto.response.TripResultDto
import com.example.helpie.tripPlanificator.domain.model.Response
import com.example.helpie.tripPlanificator.data.remote.RemoteOjpDataSource
import com.example.helpie.tripPlanificator.domain.repository.OjpRepository

/**
 * Created by Michael Ruppen on 08.04.2024
 */
class OjpRepositoryImpl(
    private val remoteDataSource: RemoteOjpDataSource,
) : OjpRepository {

    override suspend fun tripResult(): Response<List<TripResultDto>> {
        return try {
            Log.d("repo","resp")
            val response = remoteDataSource.tripRequest()
            Log.d("repo", "ok request")
            val ojpResponse = response.ojpResponse
            Log.d("repo", "Request object: $ojpResponse")
            val result = ojpResponse?.serviceDelivery?.tripDelivery?.tripResults ?: emptyList()
            Response.Success(result)
        } catch (e: Exception) {
            Response.Error(IllegalStateException("Request did not work", e))
        }
    }

}