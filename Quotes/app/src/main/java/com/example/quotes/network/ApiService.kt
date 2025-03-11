package com.example.quotes.network

import retrofit2.Call
import retrofit2.http.GET
import com.example.quotes.models.QuoteRandom  // Import your User data model

interface ApiService {
    @GET("random")
    fun getQR(): Call<List<QuoteRandom>>
}