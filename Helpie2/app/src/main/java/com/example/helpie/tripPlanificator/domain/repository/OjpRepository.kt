package com.example.helpie.tripPlanificator.domain.repository

import com.example.helpie.tripPlanificator.data.dto.response.PlaceResultDto
import com.example.helpie.tripPlanificator.data.dto.response.TripResultDto
import com.example.helpie.tripPlanificator.domain.model.Response


interface OjpRepository {
    suspend fun tripResult(): Response<List<TripResultDto>>
}