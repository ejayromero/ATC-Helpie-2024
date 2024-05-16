package com.example.helpie

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.helpie.tripPlanificator.OjpSdk
import com.example.helpie.tripPlanificator.data.dto.response.TripDto
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.Duration


data class UiState(
    //The Edit mode enable the helper to configure the app
    val editMode: Boolean  = false,

    //Help interface and number to call
    val phoneNumber:  String = "",  //"+33658814083"
    val outlineNumber:  String = "0800 007 102",  //"+33658814083"
    val usePhone: Boolean = false,  //"+33658814083"

//ticket management
    val ticket: Boolean = false,
    val urlTicket: String = "https://app.sbbmobile.ch/ticketlist",
    val takeTicket: String = "https://app.sbbmobile.ch/easyride",

    //trip management

    val request: String = "",

    val planner: OjpSdk = OjpSdk(
        baseUrl = "https://api.opentransportdata.swiss/",
        endpoint = "https://api.opentransportdata.swiss/ojp2020",
        requesterReference = "Helpie",
        token = "eyJvcmciOiI2NDA2NTFhNTIyZmEwNTAwMDEyOWJiZTEiLCJpZCI6IjAyZmIwZmM2OWQxMDRkNjY4NWNiZjQ0NWI1MjQyZjgxIiwiaCI6Im11cm11cjEyOCJ9"
    ),

    val trip: TripDto? = null,

    val summary: TripSummary? = null,

    val steps : List<StepInfo> = listOf(),

    val wait :Boolean = false,

    val currentStep: Int = 0,

    val remainingTime: Int = 0,

    val showDialog: Boolean = false,

    val registeredLocation: List<Localisation> = listOf(
        Localisation(
            destinationName = "Maison",
            destinationAddress = "Rte de la Blécherette 1, 1052 Le Mont-sur-Lausanne",
            longitude =6.635555,
            latitude = 46.558945
        ),
        // Destination 2
        Localisation(
            destinationName = "Sport",
            destinationAddress = "Centre sportif universitaire de Dorigny",
            longitude = 6.580914360470724,
            latitude = 46.5191049
        ),
        // Destination 3
        Localisation(
            destinationName = "EPFL plasma center",
            destinationAddress = "Address",
            longitude = 6.564690632302699,
            latitude = 46.51727585320471
        ),
        // Destination 4
        Localisation(
            destinationName = "EPFL plasma center",
            destinationAddress = "Address",
            longitude = 6.564690632302699,
            latitude = 46.51727585320471
        ),
    ),

    val targetLocation: Localisation = Localisation(
        destinationName = "EPFL plasma center",
        destinationAddress = "Address",
        longitude = 6.564690632302699,
        latitude = 46.51727585320471
    ),
)

data class Localisation(
    val destinationName: String? = null,
    val destinationAddress: String? = null,
    val longitude: Double? = null,
    val latitude: Double? = null
)

data class TripSummary(
    val duration: String? = null,
    val startTime: String = "",
    val endTime: String ="",
    val npSteps: Int = 0
)

open class StepInfo(
    open val mode: String? = null,
) {

    private fun extractMinutesFromWalkDuration(durationString: String): String? {
        val pattern = Regex("""PT(\d+)M""")
        val matchResult = pattern.find(durationString)
        return matchResult?.groupValues?.get(1)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun calculateDuration(): String {
        return when (this) {
            is walkInfo -> {
                val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
                val start = LocalDateTime.parse(this.startTime, formatter)
                val end = LocalDateTime.parse(this.endTime, formatter)
                val duration = Duration.between(start, end)

                // Format the duration as an ISO 8601 string
                val formattedDuration = formatDurationToMin(duration)
                formattedDuration
            }
            is transportInfo -> {
                val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
                val start = LocalDateTime.parse(this.startTime, formatter)
                val end = LocalDateTime.parse(this.endTime, formatter)
                val duration = Duration.between(start, end)

                val formattedDuration = formatDurationToMin(duration)
                formattedDuration
            }
            else -> {
                "0"  // Default duration (fallback value)
            }
        }
    }

    // Helper function to format Duration to ISO 8601 string
    @RequiresApi(Build.VERSION_CODES.O)
    private fun formatDurationToMin(duration: Duration): String {
        val seconds = duration.seconds
        return (seconds.toInt()/60).toString()
    }
    fun logValues() {
        Log.d("trip", "Mode: $mode")

        // Log common properties
        if (this is transportInfo) {
            Log.d("trip", "Start Name: $startName")
            Log.d("trip", "Start Time: $startTime")
            Log.d("trip", "Start Time Estimated: $startTimeEstimated")
            Log.d("trip", "End Name: $endName")
            Log.d("trip", "End Time: $endTime")
            Log.d("trip", "End Time Estimated: $endTimeEstimated")
            Log.d("trip", "Line: $line")
            Log.d("trip", "Start Quay: $startQuay")
            Log.d("trip", "End Quay: $endQuay")
        } else if (this is walkInfo) {
            Log.d("trip", "Start Name: $startName")
            Log.d("trip", "Start Longitude: $startLongitude")
            Log.d("trip", "Start Latitude: $startLatitude")
            Log.d("trip", "End Name: $endName")
            Log.d("trip", "End Longitude: $endLongitude")
            Log.d("trip", "End Latitude: $endLatitude")
            Log.d("trip", "Start Time: $startTime")
            Log.d("trip", "End Time: $endTime")
            Log.d("trip", "Duration: $duration")
            Log.d("trip", "Length: $length")
            Log.d("trip", "Buffer: $buffer")
        }
    }
}

data class transportInfo(
    override val mode: String? = null,

    val startName: String? = null,
    val startTime: String? = null,
    val startTimeEstimated: String? = null,

    val endName: String? = null,
    val endTime: String? = null,
    val endTimeEstimated: String? = null,

    //transport
    val line: String? = null,
    val startQuay: String? = null,
    val endQuay: String? = null,

): StepInfo(mode
)

data class walkInfo(
    override val mode: String? = null,

    val startName: String? = null,
    val startLongitude: Double? = 0.0,
    val startLatitude: Double? = 0.0,

    val endName: String? = null,
    val endLongitude: Double? = 0.0,
    val endLatitude: Double? = 0.0,

    val startTime: String? = null,
    val endTime: String? = null,
    val duration: String? = null,

    val length: Double? = 0.0, //metre
    val buffer: String? = null,

    ): StepInfo(mode
)