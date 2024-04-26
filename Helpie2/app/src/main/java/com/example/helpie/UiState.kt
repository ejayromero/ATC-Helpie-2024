package com.example.helpie

import com.example.helpie.tripPlanificator.OjpSdk

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

    //var planificator: OjpSdk? = null
)
