package com.example.helpie

import com.example.helpie.tripPlanificator.OjpSdk
import com.example.helpie.tripPlanificator.data.dto.response.TripDto


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

    val currentStep: Int = 0,

    val showDialog : Boolean = false,

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
    val duration: String?,
    val startTime: String,
    val endTime: String,
    val npSteps: Int
)

data class StepInfo(
    val mode: String? = null,

    val startName: String? = null,
    val startLongitude: Double? = 0.0,
    val startLatitude: Double? = 0.0,
    val endName: String? = null,
    val endLongitude: Double? = 0.0,
    val endLatitude: Double? = 0.0,

    )

