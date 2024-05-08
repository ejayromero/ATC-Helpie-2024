package com.example.helpie.tripPlanificator

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

import com.example.helpie.tripPlanificator.data.dto.OjpDto
import com.google.gson.GsonBuilder

//const val url = "https://api.opentransportdata.swiss/ojp2020"
//const val token = "eyJvcmciOiI2NDA2NTFhNTIyZmEwNTAwMDEyOWJiZTEiLCJpZCI6IjAyZmIwZmM2OWQxMDRkNjY4NWNiZjQ0NWI1MjQyZjgxIiwiaCI6Im11cm11cjEyOCJ9"

class TheServices {
    suspend fun sendRequest(
        url: String,
        token: String,
        request: OjpDto
    ): OjpDto {
        Log.d("service","go")

        val client = OkHttpClient()

        val gson = GsonBuilder().create()

        val xmlString = gson.toJson(request.ojpRequest)

        val requestBody = xmlString.toRequestBody("application/xml".toMediaType())

        val tosend = Request.Builder()
            .url(url)
            .header("Authorization", token)
            .post(requestBody)
            .build()

        val response = client.newCall(tosend).execute()
        response.body?.string() ?: ""

        Log.d("service","Request object: $response")

        return withContext(Dispatchers.IO) {
            request
        }
    }
}