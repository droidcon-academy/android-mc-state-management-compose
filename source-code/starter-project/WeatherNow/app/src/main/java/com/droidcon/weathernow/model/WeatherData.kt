package com.droidcon.weathernow.model

data class WeatherData(
    val temperature: String,
    val description: String,
    val forecast: List<WeatherForecast>
)
