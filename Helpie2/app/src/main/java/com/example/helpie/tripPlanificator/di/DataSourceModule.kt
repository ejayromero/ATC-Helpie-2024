package com.example.helpie.tripPlanificator.di

import com.example.helpie.tripPlanificator.data.remote.RemoteOjpDataSource
import com.example.helpie.tripPlanificator.data.remote.RemoteOjpDataSourceImpl
import org.koin.dsl.module

/**
 * Created by Michael Ruppen on 08.04.2024
 */
val dataSourceModule = module {
    single<RemoteOjpDataSource> { RemoteOjpDataSourceImpl(get(), get()) }
}