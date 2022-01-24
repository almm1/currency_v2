package com.example.curency_v2.data.repository

import com.example.curency_v2.domain.models.EntityData
import com.example.curency_v2.domain.repository.ValuteRepository

class ValuteRepositoryImpl(private val dataSource: DataSource) : ValuteRepository {
    override suspend fun getValute(): EntityData? {
        return dataSource.getValutes()
    }
}