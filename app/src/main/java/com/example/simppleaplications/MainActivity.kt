package com.example.simppleaplications

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simppleaplications.ui.theme.SimppleAplicationsTheme
import java.lang.reflect.Modifier
import android.text.Layout.Alignment


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimppleAplicationsTheme {
                NavigationApp()
                }
            }
        }
    }
}

@Composable
fun NavigationApp(){
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = "login"
    ) {
        composable("login") {LoginPage(navController)}
    }
}

@Composable
fun LoginPage(navController: NavController){
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var senhaVisivel by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimppleAplicationsTheme {
        LoginPage(navController = rememberNavController())
    }
}