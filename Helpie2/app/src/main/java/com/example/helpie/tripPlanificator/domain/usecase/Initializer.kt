package com.example.helpie.tripPlanificator.domain.usecase


/**
 * Created by Michael Ruppen on 08.04.2024
 */
class Initializer {

    lateinit var baseUrl: String
    lateinit var endpoint: String
    lateinit var requesterReference: String
    lateinit var httpHeaders: HashMap<String, String>

    fun init(
        baseUrl: String,
        endpoint: String,
        requesterReference: String,
        httpHeaders: HashMap<String, String>,
    ) {
        this.baseUrl = baseUrl
        this.endpoint = endpoint
        this.requesterReference = requesterReference + "_" + ANDROID_SDK
        this.httpHeaders = httpHeaders
    }

    companion object {
        private const val ANDROID_SDK = "ANDROID_SDK"
    }
}