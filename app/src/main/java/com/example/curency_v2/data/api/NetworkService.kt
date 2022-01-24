package com.example.curency_v2.data.api

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit


object NetworkService {
    val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.cbr-xml-daily.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CurrencyApi::class.java)
    }
}
