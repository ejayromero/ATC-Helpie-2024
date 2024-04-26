package com.example.helpie.tripPlanificator.di

import com.example.helpie.tripPlanificator.data.repository.OjpRepositoryImpl
import com.example.helpie.tripPlanificator.domain.repository.OjpRepository
import org.koin.dsl.module

/**
 * Created by Michael Ruppen on 08.04.2024
 */
val repositoryModule = module {
    single<OjpRepository> { OjpRepositoryImpl(get()) }
}