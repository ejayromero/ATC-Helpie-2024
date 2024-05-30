package com.example.helpie

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.helpie.foregroundServices.ForegroundService
import com.example.helpie.tripPlanificator.OjpSdk
import com.example.helpie.tripPlanificator.data.dto.response.TripDto
import com.google.android.gms.maps.model.LatLng


data class UiState(

    //Help interface and number to call
    val phoneNumber:  String = "",
    val outlineNumber: Boolean = "0800 007 102",
    val usePhone: Boolean = false,

    //debugging
    val skipper: Boolean = 0,
    val debugging: Localisation = false,

    //ticket management
    val ticket: Boolean = false,
    val urlTicket: String = "https://app.sbbmobile.ch/ticketlist",
    val takeTicket: String = "https://app.sbbmobile.ch/easyride",
    val easyRide: Boolean = true,

    // end of travel management
    val isFinish: Boolean = false,
    val needClean: Boolean = false,

    // notification management
    val type: ForegroundService.Actions = ForegroundService.Actions.None,

    //trip management
    val planner: OjpSdk = OjpSdk(
        baseUrl = "https://api.opentransportdata.swiss/",
        endpoint = "https://api.opentransportdata.swiss/ojp2020",
        requesterReference = "Helpie",
        token = "eyJvcmciOiI2NDA2NTFhNTIyZmEwNTAwMDEyOWJiZTEiLCJpZCI6IjAyZmIwZmM2OWQxMDRkNjY4NWNiZjQ0NWI1MjQyZjgxIiwiaCI6Im11cm11cjEyOCJ9"
    ),

    val trip: TripDto? = null,

    val summary: TripSummary? = null,

    val tripIsGoing: Boolean = false,

    val steps: List<StepInfo> = listOf(),

    val wait:Boolean = false,

    val currentStep: Int = -1,

    val remainingTime: Int = 0,

    val timeNeeded: String = "start",

    val showDialog: Boolean = false,

    val currentLocation: LatLng = LatLng(46.51912267765663,6.566314197944148),

    val registeredLocation: List<Localisation> = listOf(
        Localisation(
            destinationName = "Maison",
            destinationAddress = "Rte de la Blécherette 1, 1052 Le Mont-sur-Lausanne",
            longitude =6.635555,
            latitude = 46.558945,
            isValid = true
        ),
        // Destination 2
        Localisation(
            destinationName = "Sport",
            destinationAddress = "Centre sportif universitaire de Dorigny",
            longitude = 6.580914360470724,
            latitude = 46.5191049,
            isValid = true
        ),
        // Destination 3
        Localisation(
            destinationName = "La grange de Dorigny",
            destinationAddress = "Grange Dorigny, Quartier, 1015 Lausanne",
            longitude = 6.581891310828132,
            latitude = 46.52425828710366,
            isValid = true
        ),
        // Destination 4
        Localisation(
            destinationName = "EPFL",
            destinationAddress = "Rte Cantonale, 1015 Lausanne",
            longitude =  6.566047222595748,
            latitude = 46.52219353016205,
            isValid = true

        ),
        // Destination input
        Localisation(
            destinationName = "Ta destination",
            destinationAddress = "Address",
            longitude = 6.564690632302699,
            latitude = 46.51727585320471,
            isValid = true
        ),
    ),

    val targetLocation: Localisation = Localisation(isValid = true),
)

data class Localisation(
    val destinationName: String? = null,
    val destinationAddress: String? = null,
    val longitude: Double? = null,
    val latitude: Double? = null,
    val isValid: Boolean? = false
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
    @RequiresApi(Build.VERSION_CODES.O)
    fun giveTime(point: String): String {
        return when (this) {
            is walkInfo -> {
                when (point) {
                    "start" -> {
                                           this.startTime!!
//                        "2024-05-22T00:46:00Z"
                    }

                    "end" -> {
                                            this.endTime!!
//                        "2024-05-22T00:49:00Z"
                    }

                    else -> {
                        "0start"
                    }
                }
            }
            is transportInfo -> {
                when (point) {
                    "start" -> {
                               this.startTime!!
//                        "2024-05-22T00:46:00Z"
                    }
                    "end" -> {
                                this.endTime!!
//                        "2024-05-22T00:49:00Z"
                    }
                    else -> {
                        "0end"
                    }
                }
            }
            else -> {
                "stepIssue"  // Default duration (fallback value)
            }
        }
    }

    // Helper function to format Duration to ISO 8601 string
    @RequiresApi(Build.VERSION_CODES.O)
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
            Log.d("trip", "Way: $way")
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
    val way: String? = null,

    ): StepInfo(mode
)

data class walkInfo(
    override val mode: String? = null,

    val startName: String? = null,
    val startLongitude: Double? = 0.0,
    val startLatitude: Double? = 0.0,

    val endName: String? = null,
    var endLongitude: Double? = 0.0,
    var endLatitude: Double? = 0.0,

    val startTime: String? = null,
    val endTime: String? = null,
    val duration: String? = null,

    val length: Double? = 0.0, //metre
    val buffer: String? = null,

    ): StepInfo(mode
)