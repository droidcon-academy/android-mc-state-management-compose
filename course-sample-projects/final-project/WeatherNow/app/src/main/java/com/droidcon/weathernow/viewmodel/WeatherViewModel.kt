package com.droidcon.weathernow.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidcon.weathernow.model.City
import com.droidcon.weathernow.model.WeatherState
import com.droidcon.weathernow.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: WeatherRepository = WeatherRepository()) : ViewModel() {

    private val _weatherData = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val weatherData: StateFlow<WeatherState> = _weatherData

    private val _cities = MutableStateFlow<List<City>>(emptyList())
    val cities: StateFlow<List<City>> = _cities

    // Remember the last city that was queried to re-query it during a configuration change.
    private var lastQueriedCity: String? = null

    init {
        loadCities()
    }

    private fun loadCities() {
        viewModelScope.launch {
            _cities.value = repository.getCities()
        }
    }

    fun getWeatherForCity(cityName: String) {
        lastQueriedCity = cityName
        viewModelScope.launch {
            _weatherData.value = WeatherState.Loading
            try {
                val weather = repository.fetchWeatherForCity(cityName)
                _weatherData.value = WeatherState.Success(weather)
            } catch (e: Exception) {
                _weatherData.value = WeatherState.Error(e.message ?: "Unknown error")
            }
        }
    }

    // This method would be called from the UI component like an Activity or Fragment
    // when it is being created or recreated after a configuration change.
    fun restoreWeatherForLastQueriedCity() {
        lastQueriedCity?.let {
            getWeatherForCity(it)
        }
    }
}
