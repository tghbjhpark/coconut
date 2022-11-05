package com.hoya.ddory.coconut.view.account

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hoya.ddory.coconut.database.entity.Account

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun AccountScreen(
    accountViewModel: AccountViewModel = viewModel(),
    onAddAccount: () -> Unit
) {
    val context = LocalContext.current
    accountViewModel.getTaskList(context)
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Accounts")
                },
                actions = {
                    IconButton(onClick = onAddAccount) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Add"
                        )
                    }
                })
        }
    ) {
        AccountList(
            modifier = Modifier.padding(it),
            accounts = accountViewModel.accountList
        )
    }
}

@Composable
fun AccountList(
    modifier: Modifier,
    accounts: List<Account>
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = accounts,
            key = {
                it.id
            }
        ) { account ->
            AccountItem(
                account.id,
                account.initDeposit,
                account.orderCurrency,
                account.available,
                account.quantity
            )
        }
    }
}

@Composable
fun AccountItem(
    id: Int,
    initDeposit: String,
    orderCurrency: String,
    currentDeposit: String,
    coinQuantity: String
) {
    Row {
        Text(text = id.toString())
        Text(text = initDeposit)
        Text(text = orderCurrency)
        Text(text = currentDeposit)
        Text(text = coinQuantity)
    }
}
