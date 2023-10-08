package com.test.testfordrumncode.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.test.common.api.AuthInterceptor
import com.test.data.source.shared.remote.ApiService
import com.test.testfordrumncode.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {

    factory { provideConverterFactory() }

    factory { provideAuthInterceptor(BuildConfig.API_KEY) }
    factory { provideLogInterceptor() }

    factory { provideOkHttpClient(get(), get()) }
    factory { provideApi(get()) }

    single { provideRetrofit(BuildConfig.BASE_URL, get(), get()) }

}

fun provideConverterFactory(): MoshiConverterFactory {
    val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
    return MoshiConverterFactory.create(moshi)
}

fun provideLogInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

fun provideAuthInterceptor(apiKey: String): AuthInterceptor = AuthInterceptor(apiKey)

fun provideOkHttpClient(
    authInterceptor: AuthInterceptor,
    loggingInterceptor: HttpLoggingInterceptor,
): OkHttpClient {
    return OkHttpClient().newBuilder()
        .addInterceptor(authInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()
}


fun provideRetrofit(
    url: String,
    okHttpClient: OkHttpClient,
    factory: MoshiConverterFactory,
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(factory)
        .build()
}


fun provideApi(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)


