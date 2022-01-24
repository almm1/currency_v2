package com.example.curency_v2.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
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
import com.example.curency_v2.presentation.theme.ui.*

@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels {
        MainViewModel.ViewModelFactory(
            (applicationContext as App).getCurrencyListUseCase,
            (applicationContext as App).convertCurrencyUseCase
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Curency_v2Theme { CurrencyApp(viewModel) }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun CurrencyApp(viewModel: MainViewModel) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.getData() }) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = ""
                )
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
        Modifier.background(BackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TimeText(text = viewModel.time.value)

        LazyColumn {
            items(data.size) {
                ItemList(data[it], viewModel, it)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
private fun TimeText(text: String) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(all = 12.dp),
        style = Typography.h1
    )
}

@ExperimentalAnimationApi
@Composable
private fun ItemList(currency: Currency, viewModel: MainViewModel, index: Int) {
    var visible by rememberSaveable { mutableStateOf(false) }
    Column(
        Modifier
            .clickable {
                visible = !visible
                viewModel.resultList[index] = "0"
            }
            .fillMaxWidth()
            .background(BackgroundItemColor)
            .padding(20.dp))
    {
        Text(text = currency.name, style = Typography.body2)
        Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
            Text(text = currency.charCode, style = Typography.h4)
            Text(text = "${currency.nominal} x ${currency.value}", style = Typography.h4)
        }
        AnimatedVisibility(visible = visible) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                MyOutlinedTextField(viewModel, currency, index)
                Text("  =   ${viewModel.resultList[index]}", style = Typography.h1)
            }
        }
    }
}


@Composable
private fun MyOutlinedTextField(viewModel: MainViewModel, currency: Currency, index: Int) {
    var text: String by rememberSaveable { mutableStateOf("") }
    OutlinedTextField(
        value = text,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Purrple,
            focusedBorderColor = Purrple,
            textColor = Color.White
        ),
        modifier = Modifier.padding(top = 5.dp).width(180.dp),
        onValueChange = {
            text = it
            viewModel.convertCurrency(it, currency.value, currency.nominal, index)
        },
        placeholder = { Text(text = "RUB", style = Typography.h3) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        keyboardActions = KeyboardActions(),
        maxLines = 1,
        singleLine = true,
        textStyle = Typography.h2
    )
}