package com.example.laboratory2

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface ApiService {
    @GET("new_text.json")
    suspend fun getCardsInfo(): Response<List<CardInfo>>

    companion object {
        private const val API_URL = "https://develtop.ru/study/"

        fun instance(): ApiService = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}