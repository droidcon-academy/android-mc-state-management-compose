package com.droidcon.weathernow.viewmodel

import androidx.lifecycle.ViewModel
import com.droidcon.weathernow.model.WeatherData
import com.droidcon.weathernow.model.WeatherForecast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WeatherViewModel : ViewModel() {
    private val _weatherData = MutableStateFlow(
        WeatherData(
            "20°C", "Sunny",
            listOf(
                WeatherForecast("Monday", "22°C", "Partly Cloudy"),
                WeatherForecast("Tuesday", "18°C", "Rainy"),
                )
        ))
    
    val weatherData: StateFlow<WeatherData> = _weatherData
    
    private val _showAdditionalDetails = MutableStateFlow(false)
    val showAdditionalDetails : StateFlow<Boolean> = _showAdditionalDetails
    
    fun updateWeather(newWeatherData: WeatherData) {
        _weatherData.value = newWeatherData
    }
    
    fun toggleDetails() {
        _showAdditionalDetails.value = !_showAdditionalDetails.value
    }
}