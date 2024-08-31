package com.sid.motivateme.quotenetwork

import retrofit2.http.GET
import retrofit2.http.Path

interface QuoteApiService {
    @GET("quotes/random")
    suspend fun getRandomQuote(): List<QuoteNetworkEntity>
}