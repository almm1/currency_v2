package com.example.curency_v2.domain.models


data class EntityData(
    val time: String,
    val currencies: List<Currency>
)

data class Currency(
    val charCode: String,
    val nominal: Int,
    val name: String,
    val value: Float
)
