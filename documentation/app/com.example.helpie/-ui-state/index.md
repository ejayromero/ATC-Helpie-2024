//[app](../../../index.md)/[com.example.helpie](../index.md)/[UiState](index.md)

# UiState

[androidJvm]\
data class [UiState](index.md)(val phoneNumber: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;&quot;, val outlineNumber: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;0800 007 102&quot;, val usePhone: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, val langage: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;fr&quot;, val langageSwitch: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, val skipper: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, val debugging: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, val ticket: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, val urlTicket: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;https://app.sbbmobile.ch/ticketlist&quot;, val takeTicket: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;https://app.sbbmobile.ch/easyride&quot;, val easyRide: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, val isFinish: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, val needClean: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, val type: [ForegroundService.Actions](../../com.example.helpie.foregroundServices/-foreground-service/-actions/index.md) = ForegroundService.Actions.None, val planner: [OjpSdk](../../com.example.helpie.tripPlanificator/-ojp-sdk/index.md) = OjpSdk(
        baseUrl = &quot;https://api.opentransportdata.swiss/&quot;,
        endpoint = &quot;https://api.opentransportdata.swiss/ojp2020&quot;,
        requesterReference = &quot;Helpie&quot;,
        token = &quot;eyJvcmciOiI2NDA2NTFhNTIyZmEwNTAwMDEyOWJiZTEiLCJpZCI6IjAyZmIwZmM2OWQxMDRkNjY4NWNiZjQ0NWI1MjQyZjgxIiwiaCI6Im11cm11cjEyOCJ9&quot;
    ), val trip: [TripDto](../../com.example.helpie.tripPlanificator.data.dto.response/-trip-dto/index.md)? = null, val summary: [TripSummary](../-trip-summary/index.md)? = null, val tripIsGoing: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, val steps: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[StepInfo](../-step-info/index.md)&gt; = listOf(), val wait: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, val currentStep: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = -1, val remainingTime: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, val timeNeeded: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;start&quot;, val showDialog: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, val currentLocation: LatLng = LatLng(46.51912267765663,6.566314197944148), val registeredLocation: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Localisation](../-localisation/index.md)&gt; = listOf(
        Localisation(
            destinationName = &quot;Maison&quot;,
            destinationAddress = &quot;Rte de la Blécherette 1, 1052 Le Mont-sur-Lausanne&quot;,
            longitude =6.635555,
            latitude = 46.558945,
            isValid = true
        ),
        // Destination 2
        Localisation(
            destinationName = &quot;Bel-Air&quot;,
            destinationAddress = &quot;Bel-Air, Lausanne&quot;,
            longitude = 6.629292449529679,
            latitude = 46.52220677770554,
            isValid = true
        ),
        // Destination 3
        Localisation(
            destinationName = &quot;Biotech&quot;,
            destinationAddress = &quot;Chem. des Mines 9, 1202 Genève&quot;,
            longitude = 6.1482750577995295,
            latitude = 46.22212491537171,
            isValid = true
        ),
        // Destination 4
        Localisation(
            destinationName = &quot;EPFL&quot;,
            destinationAddress = &quot;Rte Cantonale, 1015 Lausanne&quot;,
            longitude =  6.566047222595748,
            latitude = 46.52219353016205,
            isValid = true

        ),
        // Destination input
        Localisation(
            destinationName = &quot;Ta destination&quot;,
            destinationAddress = &quot;Address&quot;,
            longitude = 6.564690632302699,
            latitude = 46.51727585320471,
            isValid = true
        ),
    ), val targetLocation: [Localisation](../-localisation/index.md) = Localisation(isValid = true))

Data class representing the state of the UI.

## Constructors

| | |
|---|---|
| [UiState](-ui-state.md) | [androidJvm]<br>constructor(phoneNumber: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;&quot;, outlineNumber: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;0800 007 102&quot;, usePhone: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, langage: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;fr&quot;, langageSwitch: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, skipper: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, debugging: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, ticket: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, urlTicket: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;https://app.sbbmobile.ch/ticketlist&quot;, takeTicket: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;https://app.sbbmobile.ch/easyride&quot;, easyRide: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, isFinish: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, needClean: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, type: [ForegroundService.Actions](../../com.example.helpie.foregroundServices/-foreground-service/-actions/index.md) = ForegroundService.Actions.None, planner: [OjpSdk](../../com.example.helpie.tripPlanificator/-ojp-sdk/index.md) = OjpSdk(         baseUrl = &quot;https://api.opentransportdata.swiss/&quot;,         endpoint = &quot;https://api.opentransportdata.swiss/ojp2020&quot;,         requesterReference = &quot;Helpie&quot;,         token = &quot;eyJvcmciOiI2NDA2NTFhNTIyZmEwNTAwMDEyOWJiZTEiLCJpZCI6IjAyZmIwZmM2OWQxMDRkNjY4NWNiZjQ0NWI1MjQyZjgxIiwiaCI6Im11cm11cjEyOCJ9&quot;     ), trip: [TripDto](../../com.example.helpie.tripPlanificator.data.dto.response/-trip-dto/index.md)? = null, summary: [TripSummary](../-trip-summary/index.md)? = null, tripIsGoing: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, steps: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[StepInfo](../-step-info/index.md)&gt; = listOf(), wait: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, currentStep: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = -1, remainingTime: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, timeNeeded: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;start&quot;, showDialog: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, currentLocation: LatLng = LatLng(46.51912267765663,6.566314197944148), registeredLocation: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Localisation](../-localisation/index.md)&gt; = listOf(         Localisation(             destinationName = &quot;Maison&quot;,             destinationAddress = &quot;Rte de la Blécherette 1, 1052 Le Mont-sur-Lausanne&quot;,             longitude =6.635555,             latitude = 46.558945,             isValid = true         ),         // Destination 2         Localisation(             destinationName = &quot;Bel-Air&quot;,             destinationAddress = &quot;Bel-Air, Lausanne&quot;,             longitude = 6.629292449529679,             latitude = 46.52220677770554,             isValid = true         ),         // Destination 3         Localisation(             destinationName = &quot;Biotech&quot;,             destinationAddress = &quot;Chem. des Mines 9, 1202 Genève&quot;,             longitude = 6.1482750577995295,             latitude = 46.22212491537171,             isValid = true         ),         // Destination 4         Localisation(             destinationName = &quot;EPFL&quot;,             destinationAddress = &quot;Rte Cantonale, 1015 Lausanne&quot;,             longitude =  6.566047222595748,             latitude = 46.52219353016205,             isValid = true<br>        ),         // Destination input         Localisation(             destinationName = &quot;Ta destination&quot;,             destinationAddress = &quot;Address&quot;,             longitude = 6.564690632302699,             latitude = 46.51727585320471,             isValid = true         ),     ), targetLocation: [Localisation](../-localisation/index.md) = Localisation(isValid = true)) |

## Properties

| Name | Summary |
|---|---|
| [currentLocation](current-location.md) | [androidJvm]<br>val [currentLocation](current-location.md): LatLng<br>The current location. |
| [currentStep](current-step.md) | [androidJvm]<br>val [currentStep](current-step.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The index of the current step. |
| [debugging](debugging.md) | [androidJvm]<br>val [debugging](debugging.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false<br>Boolean indicating if debugging is enabled. |
| [easyRide](easy-ride.md) | [androidJvm]<br>val [easyRide](easy-ride.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false<br>Boolean indicating the ticket method (easyride, if false reminder). |
| [isFinish](is-finish.md) | [androidJvm]<br>val [isFinish](is-finish.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false<br>Boolean indicating if the travel is finished (to handle last notification). |
| [langage](langage.md) | [androidJvm]<br>val [langage](langage.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The current language setting. |
| [langageSwitch](langage-switch.md) | [androidJvm]<br>val [langageSwitch](langage-switch.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true<br>Boolean indicating if a change in language is asked. |
| [needClean](need-clean.md) | [androidJvm]<br>val [needClean](need-clean.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false<br>Boolean indicating if cleaning is needed (same, will wait until return to the app to avoid crash and allow persistant notification). |
| [outlineNumber](outline-number.md) | [androidJvm]<br>val [outlineNumber](outline-number.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The CFF outline number (or other authority) to call. |
| [phoneNumber](phone-number.md) | [androidJvm]<br>val [phoneNumber](phone-number.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The phone number of the caregiver for help interface. |
| [planner](planner.md) | [androidJvm]<br>val [planner](planner.md): [OjpSdk](../../com.example.helpie.tripPlanificator/-ojp-sdk/index.md)<br>The trip planner instance. |
| [registeredLocation](registered-location.md) | [androidJvm]<br>val [registeredLocation](registered-location.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Localisation](../-localisation/index.md)&gt;<br>List of registered locations. |
| [remainingTime](remaining-time.md) | [androidJvm]<br>val [remainingTime](remaining-time.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0<br>The remaining time for the step. |
| [showDialog](show-dialog.md) | [androidJvm]<br>val [showDialog](show-dialog.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false<br>Boolean indicating if the page to add a new destination is showed. |
| [skipper](skipper.md) | [androidJvm]<br>val [skipper](skipper.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0<br>Skipper value for debugging. It is like time travelling, this value is added to the current time. |
| [steps](steps.md) | [androidJvm]<br>val [steps](steps.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[StepInfo](../-step-info/index.md)&gt;<br>List of steps in the trip. |
| [summary](summary.md) | [androidJvm]<br>val [summary](summary.md): [TripSummary](../-trip-summary/index.md)? = null<br>The summary of the trip. |
| [takeTicket](take-ticket.md) | [androidJvm]<br>val [takeTicket](take-ticket.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The URL for taking a ticket (for example, to easyride). |
| [targetLocation](target-location.md) | [androidJvm]<br>val [targetLocation](target-location.md): [Localisation](../-localisation/index.md)<br>The target location. |
| [ticket](ticket.md) | [androidJvm]<br>val [ticket](ticket.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false<br>Boolean indicating if a ticket is bought. |
| [timeNeeded](time-needed.md) | [androidJvm]<br>val [timeNeeded](time-needed.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The time needed for the step. |
| [trip](trip.md) | [androidJvm]<br>val [trip](trip.md): [TripDto](../../com.example.helpie.tripPlanificator.data.dto.response/-trip-dto/index.md)? = null<br>The current trip data transfer object. (request response) |
| [tripIsGoing](trip-is-going.md) | [androidJvm]<br>val [tripIsGoing](trip-is-going.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false<br>Boolean indicating if the trip is ongoing. |
| [type](type.md) | [androidJvm]<br>val [type](type.md): [ForegroundService.Actions](../../com.example.helpie.foregroundServices/-foreground-service/-actions/index.md)<br>The type of notification to manage (travel, push ect..). |
| [urlTicket](url-ticket.md) | [androidJvm]<br>val [urlTicket](url-ticket.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The URL for the ticket page (for example, to the CFF page). |
| [usePhone](use-phone.md) | [androidJvm]<br>val [usePhone](use-phone.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false<br>Boolean indicating if the personal phone should be used. |
| [wait](wait.md) | [androidJvm]<br>val [wait](wait.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false<br>Boolean indicating if waiting is required. |
