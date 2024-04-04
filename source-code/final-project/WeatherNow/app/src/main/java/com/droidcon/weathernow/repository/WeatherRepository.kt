package com.droidcon.weathernow.repository

import com.droidcon.weathernow.model.City
import com.droidcon.weathernow.model.WeatherData
import com.droidcon.weathernow.model.WeatherForecast
import kotlin.random.Random

class WeatherRepository {

    // Function to create mock weather data
    private fun createMockWeatherData(cityName: String): WeatherData {
        val descriptions = listOf("Mostly Sunny", "Mostly Rainy", "Mostly Cloudy", "Unknown")
        val randomDescription = descriptions[Random.nextInt(descriptions.size)]

        val mockForecasts = (1..5).map {
            val forecastDescription = descriptions[Random.nextInt(descriptions.size)]
            WeatherForecast("Day $it", "${Random.nextInt(15, 30)}°C", forecastDescription)
        }

        return WeatherData(
            temperature = "${Random.nextInt(15, 30)}°C",
            description = randomDescription,
            forecasts = mockForecasts
        )
    }

    // Initializing mock cities with different weather data
    private val mockCities = listOf(
        City("New York", "USA", createMockWeatherData("New York")),
        City("London", "UK", createMockWeatherData("London")),
        City("Paris", "France", createMockWeatherData("Paris")),
        City("Tokyo", "Japan", createMockWeatherData("Tokyo")),
        City("Sydney", "Australia", createMockWeatherData("Sydney"))
    )

    // Function to fetch mock cities
    fun getCities(): List<City> {
        return mockCities
    }

    // Function to fetch weather for a specific city
    fun fetchWeatherForCity(cityName: String): WeatherData {
        // Find the city in the list, if not found create new mock data
        return mockCities.find { it.name == cityName }?.weather
            ?: createMockWeatherData(cityName)
    }
}
