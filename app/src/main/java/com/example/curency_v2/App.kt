package com.example.curency_v2

import android.app.Application
import com.example.curency_v2.data.di.DependencyInjection
import com.example.curency_v2.data.repository.ValuteRepositoryImpl
import com.example.curency_v2.domain.usecase.ConvertCurrencyUseCase
import com.example.curency_v2.domain.usecase.GetCurrencyListUseCase

class App:Application() {
    private val repository: ValuteRepositoryImpl
        get() = DependencyInjection.provideRepository()

    val getCurrencyListUseCase: GetCurrencyListUseCase
        get() = GetCurrencyListUseCase(repository)

    val convertCurrencyUseCase: ConvertCurrencyUseCase
        get() = ConvertCurrencyUseCase()
}