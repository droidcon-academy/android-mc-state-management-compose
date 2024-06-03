package com.droidcon.weathernow.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.droidcon.weathernow.model.WeatherData
import com.droidcon.weathernow.model.WeatherForecast
import com.droidcon.weathernow.model.WeatherState
import com.droidcon.weathernow.ui.components.CitySearchBar
import com.droidcon.weathernow.ui.components.WeatherCard
import com.droidcon.weathernow.utils.getWeatherIcon
import com.droidcon.weathernow.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {
    val weatherState = viewModel.weatherData.collectAsState().value
    val cities by viewModel.cities.collectAsState()

    // LaunchedEffect to re-query the last city when the composable enters the Composition
    // This will restore the last known state without changing it
    LaunchedEffect(Unit) {
        viewModel.restoreWeatherForLastQueriedCity()
    }


    Column(modifier = Modifier.padding(16.dp)) {
        CitySearchBar(cities = cities.map { it.name }) { cityName ->
            Log.d("WeatherScreen", "Fetching weather for city: $cityName")
            viewModel.getWeatherForCity(cityName)
        }
        when (val state = weatherState) {
            is WeatherState.Loading -> {
                LoadingState()
                Log.d("WeatherScreen", "State: Loading")
            }
            is WeatherState.Success -> {
                Log.d("WeatherScreen", "State: Success")
                SuccessState(weatherData = state.data)
            }
            is WeatherState.Error -> {
                Log.d("WeatherScreen", "State: Error - ${state.message}")
                ErrorState(message = state.message)
            }
        }
    }
}

@Composable
fun LoadingState() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Text(text = "Loading weather data...")
    }
}

@Composable
fun SuccessState(weatherData: WeatherData) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.size(16.dp))
            WeatherIcon(description = weatherData.description)
            Text(text = "Current temperature: ${weatherData.temperature}")
            Text(text = "Current condition: ${weatherData.description}")
            Spacer(modifier = Modifier.height(8.dp))

            // Use Modifier.weight(1f) to allow the WeatherForecastColumn to take up all available space
            WeatherForecastColumn(
                forecastList = weatherData.forecasts,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun WeatherForecastColumn(forecastList: List<WeatherForecast>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        items(forecastList) { forecast ->
            WeatherCard(forecast)
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
fun ErrorState(message: String) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Text(text = "Error: $message")
    }
}

@Composable
fun WeatherIcon(description: String) {
    val iconRes = getWeatherIcon(description)
    Box(
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = description,
            modifier = Modifier.size(80.dp)
        )
    }
}





