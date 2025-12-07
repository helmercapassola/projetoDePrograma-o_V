package com.example.simppleaplications

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.simppleaplications.screens.*

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(onLoginSuccess = { navController.navigate("home") {
                popUpTo("login") { inclusive = true }
            } })
        }
        composable("home") {
            HomeScreen(
                onAdd = { navController.navigate("add") },
                onOpenDetail = { id -> navController.navigate("detail/$id") }
            )
        }
        composable("add") {
            AddItemScreen(onSaved = { navController.popBackStack() })
        }
        composable("detail/{id}", arguments = listOf(navArgument("id"){ type = NavType.LongType })) { backStackEntry ->
            val id = backStackEntry.arguments?.getLong("id") ?: 0L
            DetailScreen(itemId = id, onBack = { navController.popBackStack() })
        }
    }
}
