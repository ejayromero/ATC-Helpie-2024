package com.example.helpie

data class UiState(
    //The Edit mode enable the helper to configure the app
    val editMode : Boolean  = false,

    //Help interface and number to call
    val phoneNumber :  String = "",  //"+33658814083"
    val outlineNumber:  String = "0800 007 102",  //"+33658814083"
    val usePhone : Boolean = false,  //"+33658814083"

//ticket management
    val ticket : Boolean = false,
    val urlTicket : String = "https://app.sbbmobile.ch/ticketlist",
    val takeTicket : String = "https://app.sbbmobile.ch/easyride",

    val request : String = "",

//Bus journey
    val takeBus : Boolean = false,
    val busLine : String = "701",
    val transportMode : String = "Bus",
    val busStart : String = "St-Sulpice VD, Parc Scientifique",

    //location
    val targetLocation: Localisation = Localisation(
        destinationName = "EPFL plasma center",
        destinationAddress = "Address",
        longitude = 6.564690632302699,
        latitude = 46.51727585320471
    ),
)

class Localisation(
    val destinationName: String? = null,
    val destinationAddress: String? = null,
    val longitude: Double? = null,
    val latitude: Double? = null
)