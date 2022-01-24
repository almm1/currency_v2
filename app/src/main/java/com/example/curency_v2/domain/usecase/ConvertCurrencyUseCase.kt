package com.example.curency_v2.domain.usecase


class ConvertCurrencyUseCase {
    fun execute(rub: Int, value: Float, nominal: Int): Float {
        return (rub/value)*nominal
    }
}