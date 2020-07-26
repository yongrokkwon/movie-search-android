package com.ksoft.ms.di.module

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.ksoft.ms.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://openapi.naver.com/")
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor { chain ->
            chain.proceed(
                chain.request().newBuilder().apply {
                    addHeader("X-Naver-Client-Id", "ILemxdi5dXZ8xA9xQUf3")
                    addHeader("X-Naver-Client-Secret", "XJJSuL_2Mo")
                }.build()
            )
        }
        if (BuildConfig.DEBUG) {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

}