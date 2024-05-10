package com.example.helpie.tripPlanificator

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

fun nextStep() {

}

