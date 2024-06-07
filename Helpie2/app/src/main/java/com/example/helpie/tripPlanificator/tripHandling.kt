package com.example.helpie.tripPlanificator

import android.util.Log
import com.example.helpie.StepInfo
import com.example.helpie.TripSummary
import com.example.helpie.transportInfo
import com.example.helpie.tripPlanificator.data.dto.OjpDto
import com.example.helpie.tripPlanificator.data.dto.response.PlaceInfoDto
import com.example.helpie.tripPlanificator.data.dto.response.TripDto
import com.example.helpie.walkInfo
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.time.Duration.Companion.hours

/**
 * Extracts trip information from the provided OJP response.
 *
 * @param response The OJP response containing trip information.
 * @return The extracted [TripDto].
 * @throws IllegalStateException if no trip is found in the response.
 */
fun extractTrip(response: OjpDto): TripDto {
    try {
        val tripList = response.ojpResponse?.serviceDelivery?.tripDelivery?.tripResults

        val startTime = Clock.System.now()

        var i = 0
        if (tripList != null) {
            while(((tripList[i].trip.start?.let { Instant.parse(it).epochSeconds }
                    ?.minus(startTime.epochSeconds))?.div(60))!! < 0) {
                i += 1
            }
        }
        val selectedTrip = tripList?.get(i)?.trip

        if (selectedTrip != null) {
            Log.d("the time", selectedTrip.start.toString())
        }

        if (selectedTrip != null) {
            return selectedTrip
        } else {
            throw IllegalStateException("No trip found in the response")
        }
    } catch (e: Exception) {
        // Handle the exception or log the error
        throw IllegalStateException("Failed to extract trip: ${e.message}")
    }
}

/**
 * Extracts location information from the provided OJP response.
 *
 * @param response The OJP response containing location information.
 * @return The list of extracted [PlaceInfoDto] objects.
 * @throws IllegalStateException if no places are found in the response.
 */
fun extractLoca(response: OjpDto): List<PlaceInfoDto> {
    try {
        val places = response.ojpResponse?.serviceDelivery?.tripDelivery?.context?.place?.places
        Log.d("tripHand location", "done extract loca")
        if (places != null) {
            return places
        } else {
            throw IllegalStateException("No places found in the response")
        }
    } catch (e: Exception) {
        // Handle the exception or log the error
        throw IllegalStateException("Failed to extract trip: ${e.message}")
    }
}

/**
 * Generates a summary of the provided trip.
 *
 * @param trip The trip for which to generate the summary.
 * @return The generated [TripSummary].
 * @throws IllegalStateException if no steps are found in the trip.
 */
fun tripSummary(trip: TripDto): TripSummary {
    try {
        val steps = trip.step
        if (trip.step != null) {
            val npSteps = steps?.size ?: 0
            val duration = trip.duration ?: ""
            val startTime = trip.start ?: ""
            val endTime = trip.end ?: ""

            return TripSummary(duration, startTime, endTime, npSteps)
        }
        else {
            throw IllegalStateException("No steps found in the response")
        }
    } catch (e: Exception) {
        // Handle the exception or log the error
        throw IllegalStateException("Failed to extract steps: ${e.message}")
    }

}

/**
 * Retrieves information about the next step in the trip.
 *
 * @param trip The trip for which to retrieve the next step.
 * @param stepID The ID of the current step.
 * @param contextLoca The list of location information used for context.
 * @return The [StepInfo] object representing the next step.
 * @throws IllegalStateException if no steps are found in the trip.
 */
fun nextStep(trip: TripDto, stepID: Int, contextLoca : List<PlaceInfoDto>) : StepInfo {
    try {
        Log.d("trip", stepID.toString())

        val steps = trip.step
        val step = steps?.get(stepID)

        if (step!=null) {

            if (step.cLeg != null) {
                Log.d("trip", "continuous leg")

                val walk = walkInfo(
                    mode = step.cLeg.service.individualMode,

                    startName = step.cLeg.start.name?.name,
                    startLongitude = step.cLeg.start.position?.longitude,
                    startLatitude = step.cLeg.start.position?.latitude,

                    endName = step.cLeg.end.name?.name,
                    endLongitude = step.cLeg.end.position?.longitude,
                    endLatitude = step.cLeg.end.position?.latitude,

                    startTime = step.cLeg.timeStart,
                    endTime = step.cLeg.timeEnd,
                    duration = step.cLeg.duration,
                    length = step.cLeg.length
                )
                Log.d("is 0 ?","hello")
                if ((walk.endLongitude == null) or (walk.endLatitude == null)) {
                    Log.d("is 0","snif")
                    var find = false
                    var i = 0
                    while (!find) {
                        if (contextLoca[i].locationName?.name == walk.endName) {
                            walk.endLongitude = contextLoca[i].position?.longitude
                            walk.endLatitude = contextLoca[i].position?.latitude
                            find = true
                        }
                        i += 1
                    }
                }
                return walk


            } else if (step.tLeg != null) {
                Log.d("trip","trip leg")

                return transportInfo(
                    mode = step.tLeg.service.mode?.ptMode,

                    startName = step.tLeg.board?.name?.name,
                    startTime = step.tLeg.board?.time?.time,
                    startTimeEstimated = step.tLeg.board?.time?.timeEstimate,

                    endName = step.tLeg.alight?.name?.name,
                    endTime = step.tLeg.alight?.time?.time,
                    endTimeEstimated = step.tLeg.alight?.time?.timeEstimate,

                    line = step.tLeg.service.line?.name,
                    startQuay = step.tLeg.board?.quay?.name,
                    endQuay = step.tLeg.alight?.quay?.name,
                    way = step.tLeg.service.way?.name
                )

            } else if (step.transLeg != null) {
                Log.d("trip","transfer leg")

                var walk = walkInfo(
                    mode = step.transLeg.service,

                    startName = step.transLeg.start.name?.name,
                    startLongitude = step.transLeg.start.position?.longitude,
                    startLatitude = step.transLeg.start.position?.latitude,

                    endName = step.transLeg.end.name?.name,
                    endLongitude = step.transLeg.end.position?.longitude,
                    endLatitude =  step.transLeg.end.position?.latitude,

                    startTime = step.transLeg.timeStart,
                    endTime = step.transLeg.timeEnd,
                    duration = step.transLeg.walk,
                    buffer = step.transLeg.duration
                )

                if ((walk.endLongitude == null) or (walk.endLatitude == null)) {
                    var find = false
                    var i = 0
                    while (!find) {
                        if (contextLoca[i].locationName?.name == walk.endName) {
                            walk.endLongitude = contextLoca[i].position?.longitude
                            walk.endLatitude = contextLoca[i].position?.latitude
                            find = true
                        }
                        i += 1
                    }
                }
                return  walk

            } else {
                Log.d("trip","leg ?")
            }
            return StepInfo()
        } else {
            throw IllegalStateException("No steps found in the response")
        }
    } catch (e: Exception) {
        // Handle the exception or log the error
        throw IllegalStateException("Failed to extract steps: ${e.message}")
    }
}