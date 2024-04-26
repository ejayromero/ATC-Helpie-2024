package com.example.helpie.tripPlanificator.data.remote

import com.example.helpie.tripPlanificator.data.dto.OjpDto

/**
 * Created by Michael Ruppen on 08.04.2024
 */
interface RemoteOjpDataSource {

    suspend fun tripRequest(): OjpDto
}