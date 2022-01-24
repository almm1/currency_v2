package com.example.curency_v2.data.di

import com.example.curency_v2.data.api.NetworkService
import com.example.curency_v2.data.mappers.ApiResponseMapper
import com.example.curency_v2.data.repository.DataSource
import com.example.curency_v2.data.repository.ValuteRepositoryImpl

object DependencyInjection {
    private val networkModule by lazy {
        NetworkService
    }
    @Volatile
    var repository: ValuteRepositoryImpl? = null

    fun provideRepository(): ValuteRepositoryImpl {
        synchronized(this) {
            return repository ?: createBooksRepository()
        }
    }
    private fun createBooksRepository(): ValuteRepositoryImpl {
        val newRepo =
            ValuteRepositoryImpl(
            DataSource( networkModule.retrofit, ApiResponseMapper())
            )
        repository = newRepo
        return newRepo
    }
}