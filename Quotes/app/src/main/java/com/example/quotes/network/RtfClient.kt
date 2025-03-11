package com.example.quotes.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RtfClient {
    private const val BASE_URL = "https://zenquotes.io/api/"

    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}