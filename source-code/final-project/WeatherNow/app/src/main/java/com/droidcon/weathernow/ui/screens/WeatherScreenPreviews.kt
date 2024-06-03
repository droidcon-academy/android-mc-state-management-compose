package com.droidcon.weathernow.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.droidcon.weathernow.model.WeatherData
import com.droidcon.weathernow.model.WeatherForecast

@Composable
fun LoadingPreview() {
    LoadingState()
}

@Preview(showBackground = true)
@Composable
fun PreviewLoadingState() {
    LoadingPreview()
}

@Composable
fun SuccessPreview() {
    val sampleWeatherData = WeatherData(
        temperature = "25°C",
        description = "Sunny",
        forecasts = listOf(
            WeatherForecast("Monday", "Sunny", "25°C"),
            WeatherForecast("Tuesday", "Cloudy", "22°C"),
            WeatherForecast("Wednesday", "Rainy", "20°C")
        )
    )
    SuccessState(weatherData = sampleWeatherData)
}

@Preview(showBackground = true)
@Composable
fun PreviewSuccessState() {
    SuccessPreview()
}

@Composable
fun ErrorPreview() {
    ErrorState(message = "Unable to fetch weather data")
}

@Preview(showBackground = true)
@Composable
fun PreviewErrorState() {
    ErrorPreview()
}
