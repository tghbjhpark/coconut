package com.hoya.ddory.coconut.view.addaccount

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.hoya.ddory.coconut.R

@ExperimentalMaterial3Api
@Composable
fun AddAccountScreen(
    onNavigationClick: () -> Unit
) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Add account") },
                navigationIcon = {
                    IconButton(onClick = onNavigationClick) {
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
            Deposit()
            SelectCoin()
            Rule()
            Buttons()
        }
    }
}

@Composable
fun Deposit() {
    Row {
        Icon(painter = painterResource(id = R.drawable.ic_paid), contentDescription = null)
    }
}

@Composable
fun SelectCoin() {
    Row {
        Icon(painter = painterResource(id = R.drawable.ic_currency_bitcoin), contentDescription = null)
    }
}

@Composable
fun Rule() {
    Row {
        Icon(painter = painterResource(id = R.drawable.ic_rule), contentDescription = null)
    }
}

@Composable
fun Buttons() {
    Row {
        OutlinedButton(onClick = { /*TODO*/ }) {
            Text(text = "Back")
        }
        OutlinedButton(onClick = { /*TODO*/ }) {
            Text(text = "Create")
        }
    }
}
