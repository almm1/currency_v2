package com.example.curency_v2.domain.usecase

import com.example.curency_v2.domain.models.EntityData
import com.example.curency_v2.domain.repository.ValuteRepository

class GetCurrencyListUseCase(private val valuteRepository: ValuteRepository){
     suspend fun execute(): EntityData? {
        return valuteRepository.getValute()
    }
}