//[app](../../../index.md)/[com.example.helpie](../index.md)/[UiState](index.md)/[UiState](-ui-state.md)

# UiState

[androidJvm]\
constructor(phoneNumber: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;&quot;, outlineNumber: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;0800 007 102&quot;, usePhone: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, langage: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;fr&quot;, langageSwitch: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, skipper: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, debugging: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, ticket: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, urlTicket: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;https://app.sbbmobile.ch/ticketlist&quot;, takeTicket: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;https://app.sbbmobile.ch/easyride&quot;, easyRide: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, isFinish: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, needClean: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, type: [ForegroundService.Actions](../../com.example.helpie.foregroundServices/-foreground-service/-actions/index.md) = ForegroundService.Actions.None, planner: [OjpSdk](../../com.example.helpie.tripPlanificator/-ojp-sdk/index.md) = OjpSdk(
        baseUrl = &quot;https://api.opentransportdata.swiss/&quot;,
        endpoint = &quot;https://api.opentransportdata.swiss/ojp2020&quot;,
        requesterReference = &quot;Helpie&quot;,
        token = &quot;eyJvcmciOiI2NDA2NTFhNTIyZmEwNTAwMDEyOWJiZTEiLCJpZCI6IjAyZmIwZmM2OWQxMDRkNjY4NWNiZjQ0NWI1MjQyZjgxIiwiaCI6Im11cm11cjEyOCJ9&quot;
    ), trip: [TripDto](../../com.example.helpie.tripPlanificator.data.dto.response/-trip-dto/index.md)? = null, summary: [TripSummary](../-trip-summary/index.md)? = null, tripIsGoing: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, steps: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[StepInfo](../-step-info/index.md)&gt; = listOf(), wait: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, currentStep: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = -1, remainingTime: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, timeNeeded: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;start&quot;, showDialog: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, currentLocation: LatLng = LatLng(46.51912267765663,6.566314197944148), registeredLocation: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Localisation](../-localisation/index.md)&gt; = listOf(
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
    ), targetLocation: [Localisation](../-localisation/index.md) = Localisation(isValid = true))
