package com.droidcon.weathernow.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.droidcon.weathernow.ui.screens.LoadingScreen
import com.droidcon.weathernow.ui.screens.WeatherScreen
import com.droidcon.weathernow.viewmodel.WeatherViewModel
import kotlinx.coroutines.delay

@Composable
fun WeatherApp() {
    
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = "launch") {
        composable("launch") {
            LoadingScreen()
            LaunchedEffect(key1 = Unit) {
                delay(2000)
                navController.navigate("home") {
                    popUpTo("launch") {inclusive = true}
                }
            }
        }
        composable("home") {
            val weatherViewModel: WeatherViewModel = viewModel()
            WeatherScreen(viewModel = weatherViewModel)
        }
    }
}