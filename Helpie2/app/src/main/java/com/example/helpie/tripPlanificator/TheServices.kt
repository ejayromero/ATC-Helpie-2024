package com.example.helpie.tripPlanificator

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

import com.example.helpie.tripPlanificator.data.dto.OjpDto

const val url = "https://api.opentransportdata.swiss/ojp2020"
const val token = "eyJvcmciOiI2NDA2NTFhNTIyZmEwNTAwMDEyOWJiZTEiLCJpZCI6IjAyZmIwZmM2OWQxMDRkNjY4NWNiZjQ0NWI1MjQyZjgxIiwiaCI6Im11cm11cjEyOCJ9"

class TheServices {

    suspend fun tripRequest(): String {

        val client = OkHttpClient()


        val request = Request.Builder()
            .url(url)
            .header("Authorization", token)
            .post(requestBody)
            .build()


        return withContext(Dispatchers.IO) {
            val response = client.newCall(request).execute()
            response.body?.string() ?: ""
        }
    }
}