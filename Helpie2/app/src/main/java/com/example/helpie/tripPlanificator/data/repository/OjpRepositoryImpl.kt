package com.example.helpie.tripPlanificator.data.repository

import android.util.Log
import ch.opentransportdata.ojp.data.dto.response.PlaceResultDto
import com.example.helpie.tripPlanificator.domain.model.Response
import com.example.helpie.tripPlanificator.data.remote.RemoteOjpDataSource
import com.example.helpie.tripPlanificator.domain.repository.OjpRepository

/**
 * Created by Michael Ruppen on 08.04.2024
 */
class OjpRepositoryImpl(
    private val remoteDataSource: RemoteOjpDataSource,
) : OjpRepository {

    override suspend fun tripResult(): Response<List<PlaceResultDto>> {
        return try {
            Log.d("repo","resp")
            val response = remoteDataSource.tripRequest().ojpResponse
            Log.d("repo","result")
            val result = response?.serviceDelivery?.locationInformation?.placeResults ?: emptyList()
            Response.Success(result)
        } catch (e: Exception) {
            Response.Error(IllegalStateException("Request did not work", e))
        }
    }

}