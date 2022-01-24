package com.example.curency_v2.data.mappers

import com.example.curency_v2.data.api.ResponseApi
import com.example.curency_v2.domain.models.Currency
import com.example.curency_v2.domain.models.EntityData

class ApiResponseMapper {
    fun toEntityData(response: ResponseApi): EntityData {
        return EntityData(
            response.date,
            response.valute.values.map { Currency(it.charCode, it.nominal, it.name, it.value) })
    }
}