package com.example.helpie.tripPlanificator.data.repository

import ch.opentransportdata.ojp.data.dto.response.PlaceResultDto
import com.example.helpie.tripPlanificator.domain.model.Response
import com.example.helpie.tripPlanificator.data.remote.RemoteOjpDataSource
import com.example.helpie.tripPlanificator.domain.repository.OjpRepository
import timber.log.Timber

/**
 * Created by Michael Ruppen on 08.04.2024
 */
class OjpRepositoryImpl(
    private val remoteDataSource: RemoteOjpDataSource,
) : OjpRepository {

    override suspend fun tripResult(): Response<List<PlaceResultDto>> {
        return try {
            val response = remoteDataSource.tripRequest().ojpResponse
            val result = response?.serviceDelivery?.locationInformation?.placeResults ?: emptyList()
            Response.Success(result)
        } catch (e: Exception) {
            Timber.e(e, "Error creating request or receiving response")
            Response.Error(IllegalStateException("Request did not work", e))
        }
    }

}