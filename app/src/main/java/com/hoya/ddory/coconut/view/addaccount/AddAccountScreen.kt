package com.hoya.ddory.coconut.view.addaccount

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hoya.ddory.coconut.R
import com.hoya.ddory.coconut.view.DropDownList

@ExperimentalMaterial3Api
@Composable
fun AddAccountScreen(
    modifier: Modifier = Modifier,
    addAccountViewModel: AddAccountViewModel = viewModel(),
    onBackClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add account") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            Deposit(
                addAccountViewModel.deposit,
                addAccountViewModel::changeDeposit
            )
            SelectCoin(addAccountViewModel.coinNameResourceIdList())
            Rule(
                addAccountViewModel.buyAbove,
                addAccountViewModel::changeBuyAbove,
                addAccountViewModel.buyBelow,
                addAccountViewModel::changeBuyBelow
            )
            Buttons(onBackClick, addAccountViewModel::createAccount)
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun Deposit(
    value: String,
    onValueChange: (String) -> Unit = {}
) {
    Row {
        Icon(painter = painterResource(id = R.drawable.ic_paid), contentDescription = null)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text("Deposit") }
        )
    }
}

@ExperimentalMaterial3Api
@Composable
fun SelectCoin(
    coinList: List<Int>
) {
    Row {
        Icon(
            painter = painterResource(id = R.drawable.ic_currency_bitcoin),
            contentDescription = null
        )
        val coinTextList = coinList.map {
            stringResource(id = it)
        }
        val text = remember { mutableStateOf(coinTextList[0]) } // initial value
        val isOpen = remember { mutableStateOf(false) } // initial value
        val openCloseOfDropDownList: (Boolean) -> Unit = {
            isOpen.value = it
        }
        val userSelectedString: (String) -> Unit = {
            text.value = it
        }
        Box {
            Column {
                OutlinedTextField(
                    value = text.value,
                    onValueChange = { text.value = it },
                    label = { Text(text = "Coin") },
                    modifier = Modifier.fillMaxWidth()
                )
                DropDownList(
                    requestToOpen = isOpen.value,
                    list = coinTextList,
                    openCloseOfDropDownList,
                    userSelectedString
                )
            }
            Spacer(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color.Transparent)
                    .padding(10.dp)
                    .clickable(
                        onClick = { isOpen.value = true }
                    )
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun Rule(
    buyAboveAverage: String,
    onBuyAboveAverageValueChange: (String) -> Unit = {},
    buyBelowAverage: String,
    onBuyBelowAverageValueChange: (String) -> Unit = {}
) {
    Row {
        Icon(painter = painterResource(id = R.drawable.ic_rule), contentDescription = null)
        Column {
            OutlinedTextField(
                value = buyAboveAverage,
                onValueChange = onBuyAboveAverageValueChange,
                label = { Text("Buy above average") }
            )
            OutlinedTextField(
                value = buyBelowAverage,
                onValueChange = onBuyBelowAverageValueChange,
                label = { Text("Buy below average") }
            )
        }
    }
}

@Composable
fun Buttons(
    onBackClick: () -> Unit,
    onCreateClick: (Context) -> Unit
) {
    val context = LocalContext.current
    Row {
        OutlinedButton(onClick = onBackClick) {
            Text(text = "Back")
        }
        OutlinedButton(onClick = {
            onCreateClick.invoke(context)
            onBackClick.invoke()
        }) {
            Text(text = "Create")
        }
    }
}
