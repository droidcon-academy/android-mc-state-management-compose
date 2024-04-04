package com.droidcon.weathernow.model

data class WeatherData(
    val temperature: String,
    val description: String,
    val forecasts: List<WeatherForecast>
)
