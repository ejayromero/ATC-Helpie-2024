package com.example.helpie

import android.os.Build
import android.provider.Settings.Global.getString
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.ui.res.stringResource
import com.example.helpie.foregroundServices.ForegroundService
import com.example.helpie.tripPlanificator.OjpSdk
import com.example.helpie.tripPlanificator.data.dto.response.TripDto
import com.google.android.gms.maps.model.LatLng

/**
 * Data class representing the state of the UI.
 *
 * @property phoneNumber The phone number of the caregiver for help interface.
 * @property outlineNumber The CFF outline number (or other authority) to call.
 * @property usePhone Boolean indicating if the personal phone should be used.
 * @property langage The current language setting.
 * @property langageSwitch Boolean indicating if a change in language is asked.
 * @property skipper Skipper value for debugging. It is like time travelling, this value is added to the current time.
 * @property debugging Boolean indicating if debugging is enabled.
 * @property ticket Boolean indicating if a ticket is bought.
 * @property urlTicket The URL for the ticket page (for example, to the CFF page).
 * @property takeTicket The URL for taking a ticket (for example, to easyride).
 * @property easyRide Boolean indicating the ticket method (easyride, if false reminder).
 * @property isFinish Boolean indicating if the travel is finished (to handle last notification).
 * @property needClean Boolean indicating if cleaning is needed (same, will wait until return to the app to avoid crash and allow persistant notification).
 * @property type The type of notification to manage (travel, push ect..).
 * @property planner The trip planner instance.
 * @property trip The current trip data transfer object. (request response)
 * @property summary The summary of the trip.
 * @property tripIsGoing Boolean indicating if the trip is ongoing.
 * @property steps List of steps in the trip.
 * @property wait Boolean indicating if waiting is required.
 * @property currentStep The index of the current step.
 * @property remainingTime The remaining time for the step.
 * @property timeNeeded The time needed for the step.
 * @property showDialog Boolean indicating if the page to add a new destination is showed.
 * @property currentLocation The current location.
 * @property registeredLocation List of registered locations.
 * @property targetLocation The target location.
 */
data class UiState(

    //Help interface and number to call
    val phoneNumber:  String = "",
    val outlineNumber: String = "0800 007 102",
    val usePhone: Boolean = false,

    val langage : String = "fr",
    val langageSwitch : Boolean = true,

    //debugging
    val skipper: Int = 0,
    val debugging: Boolean = false,

    //ticket management
    val ticket: Boolean = false,
    val urlTicket: String = "https://app.sbbmobile.ch/ticketlist",
    val takeTicket: String = "https://app.sbbmobile.ch/easyride",

    val easyRide : Boolean = false,

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
            destinationName = "Bel-Air",
            destinationAddress = "Bel-Air, Lausanne",
            longitude = 6.629292449529679,
            latitude = 46.52220677770554,
            isValid = true
        ),
        // Destination 3
        Localisation(
            destinationName = "Biotech",
            destinationAddress = "Chem. des Mines 9, 1202 Genève",
            longitude = 6.1482750577995295,
            latitude = 46.22212491537171,
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

/**
 * Data class representing a location.
 *
 * @property destinationName The name of the destination.
 * @property destinationAddress The address of the destination.
 * @property longitude The longitude of the location.
 * @property latitude The latitude of the location.
 * @property isValid Boolean indicating if the location is valid. (according to google maps API)
 */
data class Localisation(
    val destinationName: String? = null,
    val destinationAddress: String? = null,
    val longitude: Double? = null,
    val latitude: Double? = null,
    val isValid: Boolean = false
)

/**
 * Data class representing a trip summary.
 *
 * @property duration The duration of the trip.
 * @property startTime The start time of the trip.
 * @property endTime The end time of the trip.
 * @property npSteps The number of steps in the trip.
 */
data class TripSummary(
    val duration: String? = null,
    val startTime: String = "",
    val endTime: String ="",
    val npSteps: Int = 0
)

/**
 * Open class representing a step in a trip.
 *
 * @property mode The mode of transportation.
 */
open class StepInfo(
    open val mode: String? = null,
) {

    /**
     * Function to return the start or end time based on the point parameter.
     *
     * @param point The point in time ("start" or "end").
     * @return The corresponding time as a string.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun giveTime(point: String): String {
        return when (this) {
            is walkInfo -> {
                when (point) {
                    "start" -> this.startTime!!
                    "end" -> this.endTime!!
                    else -> "0start"
                }
            }
            is transportInfo -> {
                when (point) {
                    "start" -> this.startTime!!
                    "end" -> this.endTime!!
                    else -> "0end"
                }
            }
            else -> "stepIssue"
        }
    }

    /**
     * Function to log the values of the step info.
     */
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

/**
 * Data class representing information for a transport step. Subclass of Stepinfo.
 *
 * @property mode The mode of transportation.
 * @property startName The name of the start point.
 * @property startTime The start time of the transport step.
 * @property startTimeEstimated The estimated start time of the transport step. (according to the data)
 * @property endName The name of the end point.
 * @property endTime The end time of the transport step.
 * @property endTimeEstimated The estimated end time of the transport step. (according to the data)
 * @property line The line of the transport. (if bus for ex)
 * @property startQuay The start quay of the transport. (if train for ex)
 * @property endQuay The end quay of the transport. (if train for ex)
 * @property way The way of the transport.
 */
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

/**
 * Data class representing information for a walk step. Subclass of Stepinfo.
 *
 * @property mode The mode of transportation.
 * @property startName Name of the start point.
 * @property startLongitude The start longitude.
 * @property startLatitude The start latitude.
 * @property endName Name of the end point.
 * @property endLongitude The end longitude.
 * @property endLatitude The end latitude.
 * @property startTime The start time of the walk.
 * @property endTime The end time of the walk.
 * @property duration The duration of the walk.
 * @property length The length of the walk in meters.
 * @property buffer The buffer time for the walk. (how many time do we have ? how many time does it take ?)
 */
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