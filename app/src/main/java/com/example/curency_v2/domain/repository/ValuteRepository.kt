package com.example.curency_v2.domain.repository

import com.example.curency_v2.domain.models.EntityData

interface ValuteRepository {
     suspend fun getValute(): EntityData?
}