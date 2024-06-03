package com.droidcon.weathernow.ui.screens

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

    LaunchedEffect(Unit) {
        viewModel.restoreWeatherForLastQueriedCity()
    }

    Column(modifier = Modifier.padding(16.dp)) {
        CitySearchBar(cities = cities.map { it.name}) {cityName ->
            viewModel.getWeatherForCity(cityName)
        }

        //Paste this when you're in the coding challenge
        when (val state = weatherState) {
            is WeatherState.Loading -> LoadingState()
            is WeatherState.Success -> SuccessState(weatherData = state.data)
            is WeatherState.Error -> ErrorState(message = state.message)
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










































