package com.example.helpie.tripPlanificator.domain.usecase


/**
 * Created by Michael Ruppen on 08.04.2024
 */
class Initializer {

    lateinit var url: String
    lateinit var requesterReference: String
    lateinit var httpHeaders: HashMap<String, String>

    fun init(
        url: String,
        requesterReference: String,
        httpHeaders: HashMap<String, String>,
    ) {
        this.url = url
        this.requesterReference = requesterReference + "_" + ANDROID_SDK
        this.httpHeaders = httpHeaders
    }

    companion object {
        private const val ANDROID_SDK = "ANDROID_SDK"
    }
}