package com.example.helpie.tripPlanificator

import android.util.Log
import com.example.helpie.StepInfo
import com.example.helpie.TripSummary
import com.example.helpie.tripPlanificator.data.dto.OjpDto
import com.example.helpie.tripPlanificator.data.dto.response.TripDto

fun extractTrip(response: OjpDto): TripDto {
    try {
        val tripList = response.ojpResponse?.serviceDelivery?.tripDelivery?.tripResults

        val selectedTrip = tripList?.get(0)?.trip

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

fun nextStep(trip: TripDto, stepID: Int) : StepInfo {
    try {
        Log.d("trip", stepID.toString())

        val steps = trip.step
        val step = steps?.get(stepID)

        if (step!=null) {
            if (step.cLeg != null) {
                Log.d("trip", "continuous leg")

                return StepInfo(
                    mode = step.cLeg.service.individualMode,
                    startName = step.cLeg.start.name?.name,
                    startLongitude = step.cLeg.start.position?.longitude,
                    startLatitude = step.cLeg.start.position?.latitude,
                    endName = step.cLeg.end.name?.name,
                    endLongitude = step.cLeg.end.position?.longitude,
                    endLatitude = step.cLeg.end.position?.longitude,
                )


            } else if (step.tLeg != null) {
                Log.d("trip","trip leg")

                return StepInfo(
                    mode = step.tLeg.service.mode?.ptMode,
                    startName = step.tLeg.board?.name?.name,
                    endName = step.tLeg.alight?.name?.name,
                )

            } else if (step.transLeg != null) {
                Log.d("trip","transfer leg")

                return StepInfo(
                    mode = step.transLeg.service,
                    startName = step.transLeg.start.name?.name,
                    startLongitude = step.transLeg.start.position?.longitude,
                    startLatitude= step.transLeg.start.position?.latitude,
                    endName = step.transLeg.end.name?.name,
                    endLongitude = step.transLeg.end.position?.longitude,
                    endLatitude=  step.transLeg.end.position?.longitude,
                )


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


