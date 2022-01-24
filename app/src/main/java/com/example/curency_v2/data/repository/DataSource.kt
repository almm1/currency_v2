package com.example.curency_v2.data.repository

import com.example.curency_v2.data.api.CurrencyApi
import com.example.curency_v2.data.mappers.ApiResponseMapper
import com.example.curency_v2.domain.models.EntityData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataSource(private val service: CurrencyApi, private val mapper:ApiResponseMapper) {

    suspend fun getValutes(): EntityData? =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getValute().execute()
                if (response.isSuccessful) {
                    return@withContext mapper.toEntityData(response.body()!!)
                } else {
                    return@withContext null
                }
            } catch (e: Exception) {
                return@withContext null
            }
        }
}