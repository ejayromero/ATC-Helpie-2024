package com.example.helpie.tripPlanificator.domain.repository

import ch.opentransportdata.ojp.data.dto.response.PlaceResultDto
import com.example.helpie.tripPlanificator.domain.model.Response


interface OjpRepository {
    suspend fun tripResult(): Response<List<PlaceResultDto>>
}