package com.recipess.app.core.network

import android.content.Context
import com.recipess.app.core.preferences.Preferences
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


//---- API
const val API_AUTH = "private"
const val API_WITHOUT_AUTH = "public"

//---- URL
const val BASE_URL    = "https://tasty.p.rapidapi.com/"

//---- Headers
const val HEADER_RAPID_KEY   = "X-RapidAPI-Key"
const val HEADER_RAPID_HOST  = "X-RapidAPI-Host"
const val HEADER_PLATFORM    = "Platform"
const val PLATFORM  = "android"

val networkModule: Module = module {

    single { providerHttpLoggingInterceptor() }
    single { getRetrofitBase() }

    //----WithoutAuth Api
    single (named(API_WITHOUT_AUTH)) {
        providerPublicRetrofit(
            retrofitBase = get(),
            context = get(),
            httpLoggingInterceptor = get()
        )
    }

    //----Auth Api
    single (named(API_AUTH)) {
        providerPrivateRetrofit(
            retrofitBase = get(),
            context = get(),
            httpLoggingInterceptor = get(),
            preferences = get()
        )
    }
}

private fun getRetrofitBase(): Retrofit.Builder {
    return Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BASE_URL)
}

private fun getBasicHeaders(context: Context, chain: Interceptor.Chain): Request.Builder {
    val builder = chain.request().newBuilder()
    builder.addHeader(HEADER_PLATFORM, PLATFORM)
    return builder
}

fun providerPublicRetrofit(retrofitBase: Retrofit.Builder, context: Context, httpLoggingInterceptor: HttpLoggingInterceptor): Retrofit {
    return retrofitBase
        .client(providerPublicHttpClient(httpLoggingInterceptor, context))
        .build()
}

fun providerPrivateRetrofit(retrofitBase: Retrofit.Builder, context: Context, preferences: Preferences, httpLoggingInterceptor:HttpLoggingInterceptor): Retrofit {
    return retrofitBase
        .client(providerPrivateHttpClient(httpLoggingInterceptor, context, preferences))
        .build()
}

private fun providerPrivateHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, context: Context, preferences: Preferences): OkHttpClient {
    val httpClient = OkHttpClient.Builder()
    httpClient.addInterceptor { chain ->
        val builder = getBasicHeaders(context, chain)
        builder.addHeader(HEADER_RAPID_KEY, preferences.rapidKey)
        builder.addHeader(HEADER_RAPID_HOST, preferences.rapidHost)
        val response = chain.proceed(builder.build())
        response
    }
    httpClient.addInterceptor(httpLoggingInterceptor)
    return httpClient.build()
}

private fun providerPublicHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, context: Context): OkHttpClient {
    val httpClient = OkHttpClient.Builder()
    httpClient.addInterceptor { chain ->
        val builder = getBasicHeaders(context, chain)
        chain.proceed(builder.build())
    }
    httpClient.addInterceptor(httpLoggingInterceptor)
    return httpClient.build()
}

fun providerHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    return logging
}



