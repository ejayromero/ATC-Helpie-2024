package com.example.helpie.tripPlanificator.data.remote

import com.example.helpie.tripPlanificator.data.dto.OjpDto
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url

/**
 * Created by Michael Ruppen on 08.04.2024
 */
interface OjpService {

    @POST
    suspend fun tripRequest(
        @Url url: String,
        @Body ojpDto: OjpDto
    ): OjpDto
}