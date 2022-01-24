package com.example.curency_v2.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.curency_v2.App
import com.example.curency_v2.domain.models.Currency


class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels {
        MainViewModel.ViewModelFactory(
            (applicationContext as App).getCurrencyListUseCase,
            (applicationContext as App).convertCurrencyUseCase
        )
    }

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrencyApp(viewModel)
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun CurrencyApp(viewModel: MainViewModel) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.getData() }) {
                Icon(imageVector = Icons.Default.Refresh, contentDescription = "")
            }
        },
        content = { Content(viewModel) },
    )
}

@ExperimentalAnimationApi
@Composable
private fun Content(viewModel: MainViewModel) {
    val data = viewModel.currency.value
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TimeText(text = viewModel.time.value)

        LazyColumn {
            items(data.size) {
                ItemList(data[it], viewModel, it)
            }
        }
    }
}

@Composable
private fun TimeText(text: String) {
    Text(text = text, textAlign = TextAlign.Center, modifier = Modifier.padding(all = 12.dp))
}

@ExperimentalAnimationApi
@Composable
private fun ItemList(currency: Currency, viewModel: MainViewModel, index:Int) {
    var visible by rememberSaveable { mutableStateOf(false) }
    Column(
        Modifier
            .clickable {
                 visible = !visible
                viewModel.resultList[index] = "0"
            }
            .fillMaxWidth()
            .padding(20.dp))
    {
        Text(text = currency.name)
        Row {
            Text(text = currency.charCode)
            Text(text = "${currency.nominal} x ${currency.value}")
        }
        AnimatedVisibility(visible = visible) {
            Row {
                MyOutlinedTextField(viewModel, currency, index)
                Text("= ${viewModel.resultList[index]}")
            }
        }
    }
}


@Composable
private fun MyOutlinedTextField(viewModel: MainViewModel, currency: Currency, index:Int) {
    var text: String by rememberSaveable { mutableStateOf("") }
    OutlinedTextField(
        value = text,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Blue,
            focusedBorderColor = Color.Blue
        ),
        onValueChange = {
            text = it
            viewModel.convertCurrency(it, currency.value, currency.nominal, index)
        },
        placeholder = { Text(text = "RUB") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        keyboardActions = KeyboardActions(),
        maxLines = 1,
        singleLine = true,
    )
}