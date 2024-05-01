package com.example.helpie.tripPlanificator.di

import ch.opentransportdata.ojp.data.remote.OjpService
import com.example.helpie.tripPlanificator.di.interceptor.TokenInterceptor
import com.example.helpie.tripPlanificator.domain.usecase.Initializer
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.converter.htmlescape.HtmlEscapeStringConverter
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit


/**
 * Created by Michael Ruppen on 08.04.2024
 */
val networkModule = module {
    single { provideLoggingInterceptor() }
    single(named("ojpHttpClient")) { provideOkHttpClient(get(), get()) }
    single(named("ojpRetrofit")) { provideRetrofit(get(named("ojpHttpClient")), get(), get()) }
    single { provideTikXml() }
    single<TokenInterceptor> { TokenInterceptor(get()) }
    single { provideOjpService(get(named("ojpRetrofit"))) }
}

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor()
}

fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor, tokenInterceptor: TokenInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(tokenInterceptor)
        .readTimeout(15000, TimeUnit.MILLISECONDS)
        .build()
}

fun provideRetrofit(ojpHttpClient: OkHttpClient, tikXml: TikXml, initializer: Initializer): Retrofit {
    return Retrofit.Builder()
        .baseUrl(initializer.baseUrl)
        .client(ojpHttpClient)
        .addConverterFactory(TikXmlConverterFactory.create(tikXml))
        .build()
}

fun provideTikXml(): TikXml {
    return TikXml.Builder()
        .addTypeConverter(String::class.java, HtmlEscapeStringConverter())
        .exceptionOnUnreadXml(false)
        .build()
}

fun provideOjpService(retrofit: Retrofit): OjpService {
    return retrofit.create(OjpService::class.java)
}