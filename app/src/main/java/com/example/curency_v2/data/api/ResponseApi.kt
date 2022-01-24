package com.example.curency_v2.data.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseApi(
    @SerializedName("Date")
    @Expose
    val date: String,
    @SerializedName("PreviousDate")
    @Expose
    val previousDate: String,
    @SerializedName("PreviousURL")
    @Expose
    val previousURL: String,
    @SerializedName("Timestamp")
    @Expose
    val timestamp: String,
    @SerializedName("Valute")
    @Expose
    val valute: Map<String, Valute>
)

data class Valute(
    @SerializedName("ID")
    @Expose
    val id: String,
    @SerializedName("NumCode")
    @Expose
    val numCode: String,
    @SerializedName("CharCode")
    @Expose
    val charCode: String,
    @SerializedName("Nominal")
    @Expose
    val nominal: Int,
    @SerializedName("Name")
    @Expose
    val name: String,
    @SerializedName("Value")
    @Expose
    val value: Float,
    @SerializedName("Previous")
    @Expose
    val previous: Float
)