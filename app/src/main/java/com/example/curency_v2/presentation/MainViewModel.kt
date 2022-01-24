package com.example.curency_v2.presentation

import androidx.compose.runtime.*
import androidx.lifecycle.*
import com.example.curency_v2.domain.models.Currency
import com.example.curency_v2.domain.usecase.ConvertCurrencyUseCase
import com.example.curency_v2.domain.usecase.GetCurrencyListUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel(
    private val getCurrencyListUseCase: GetCurrencyListUseCase,
    private val convertCurrencyUseCase: ConvertCurrencyUseCase
) : ViewModel() {

    var time: MutableState<String> = mutableStateOf("")
    var currency: MutableState<List<Currency>> = mutableStateOf(ArrayList())

    var resultList = mutableStateListOf("")

    init {
        tickerFlow().onEach { getData() }
            .launchIn(viewModelScope)
    }

    private fun tickerFlow(delay: Long = 300000) = flow {
        while (true) {
            emit(Unit)
            delay(delay)
        }
    }

    fun getData() {
        viewModelScope.launch {
            val result = getCurrencyListUseCase.execute()
            if (result != null) {
                currency.value = result.currencies
                time.value = result.time
                resultList.clear()
                for (i in 0..currency.value.size) {
                    resultList.add("0")
                }
            }
        }
    }

    fun convertCurrency(rub: String, value: Float, nominal: Int, index: Int) {
        resultList[index] =
            convertCurrencyUseCase.execute(rub.toInt(), value, nominal).toString()
    }

    class ViewModelFactory(
        private val getCurrencyListUseCase: GetCurrencyListUseCase,
        private val convertCurrencyUseCase: ConvertCurrencyUseCase
    ) :
        ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(
                getCurrencyListUseCase,
                convertCurrencyUseCase,
            ) as T
        }
    }
}


