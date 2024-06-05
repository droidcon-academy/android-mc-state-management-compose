package com.droidcon.weathernow.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.droidcon.weathernow.model.WeatherData
import com.droidcon.weathernow.model.WeatherForecast
import com.droidcon.weathernow.ui.components.WeatherCard


@Composable
fun WeatherApp() {

    var weatherData by remember {
        mutableStateOf(WeatherData("20°C", "Sunny", listOf(
            WeatherForecast("Monday", "22°C", "Partly Cloudy"),
            WeatherForecast("Tuesday", "18°C", "Rainy"),
        )))
    }

    Column {
        Text(text = "Temperature:${weatherData.temperature}")
        Text(text = "Description:${weatherData.description}")
        Button(onClick = {
            weatherData = WeatherData("15°C", "Cloudy", emptyList())
        }) {
            Text(text = "Update Weather")
        }
        
        weatherData.forecasts.forEach {
            forecast -> WeatherCard(forecast = forecast)
        }
    }
}

@Composable
fun StatelessWeatherCard(forecast: WeatherForecast) {
    Card {
        Text(text = forecast.day)
        Text(text = forecast.temperature)
        Text(text = forecast.description)
    }
}

@Composable
fun StatefulWeatherCard(forecast: WeatherForecast) {
    var showDetails by remember { mutableStateOf(false) }

    Card {
        Column {
            Text(forecast.day)
            Text(forecast.temperature)
            if (showDetails) {
                Text(forecast.description)
            }
            Button(onClick = { showDetails = !showDetails }) {
                Text(if (showDetails) "Hide Details" else "Show Details")
            }
        }
    }
}