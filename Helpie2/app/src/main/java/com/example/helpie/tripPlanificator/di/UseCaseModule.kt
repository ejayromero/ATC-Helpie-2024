package com.example.helpie.tripPlanificator.di

import com.example.helpie.tripPlanificator.domain.usecase.TripRequest
import com.example.helpie.tripPlanificator.domain.usecase.Initializer
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

/**
 * Created by Michael Ruppen on 08.04.2024
 */
val useCaseModule = module {
    singleOf(::Initializer)
    factoryOf(::TripRequest)
}