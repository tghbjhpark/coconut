package com.hoya.ddory.coconut.view.account

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hoya.ddory.coconut.database.entity.Account

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
            accounts = accountViewModel.accountList,
            playClick = { id ->
                accountViewModel.playPause(id, context)
            }
        )
    }
}

@ExperimentalMaterial3Api
@Composable
fun AccountList(
    modifier: Modifier,
    accounts: List<Account>,
    playClick: (Int) -> Unit
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
                account.quantity,
                account.state == "START",
                playClick
            )
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@ExperimentalMaterial3Api
@Composable
fun AccountItem(
    id: Int,
    initDeposit: String,
    orderCurrency: String,
    currentDeposit: String,
    coinQuantity: String,
    isPlaying: Boolean,
    playClick: (Int) -> Unit
) {
    val state: Boolean by mutableStateOf(isPlaying)
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = {
            Log.i("JONGHO", "[$id] item click")
        }) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(text = id.toString())
            Text(text = initDeposit)
            Text(text = orderCurrency)
            Text(text = currentDeposit)
            Text(text = coinQuantity)
            IconButton(onClick = { playClick(id) }) {
                Icon(
                    imageVector = if (state) Icons.Filled.PlayArrow else Icons.Filled.Close,
                    contentDescription = "Add"
                )
            }
        }
    }
}
