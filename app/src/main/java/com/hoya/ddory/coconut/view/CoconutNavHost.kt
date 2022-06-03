package com.hoya.ddory.coconut.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hoya.ddory.coconut.view.account.AccountScreen
import com.hoya.ddory.coconut.view.addaccount.AddAccountScreen

@Composable
fun CoconutNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = CoconutScreen.Account.name,
        modifier = modifier
    ) {
        composable(CoconutScreen.Account.name) {
            AccountScreen(
                onAddAccount = { navController.navigate(CoconutScreen.AddAccount.name) }
            )
        }
        composable(CoconutScreen.AddAccount.name) {
            AddAccountScreen()
        }
    }
}