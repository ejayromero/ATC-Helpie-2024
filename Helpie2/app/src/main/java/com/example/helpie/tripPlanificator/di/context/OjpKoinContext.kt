package com.example.helpie.tripPlanificator.di.context

import com.example.helpie.tripPlanificator.di.dataSourceModule
import com.example.helpie.tripPlanificator.di.networkModule
import com.example.helpie.tripPlanificator.di.repositoryModule
import com.example.helpie.tripPlanificator.di.useCaseModule
import org.koin.dsl.koinApplication

/**
 * Created by Michael Ruppen on 08.04.2024
 */
object OjpKoinContext {
    val koinApp = koinApplication {
        modules(listOf(dataSourceModule, networkModule, repositoryModule, useCaseModule))
    }
}
