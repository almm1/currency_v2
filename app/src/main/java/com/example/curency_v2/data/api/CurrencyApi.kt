package com.example.curency_v2.data.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface CurrencyApi {
    @GET("daily_json.js")
     fun getValute(): Call<ResponseApi>
}