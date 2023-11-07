package com.droidcon.weathernow.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.droidcon.weathernow.model.WeatherForecast
import com.droidcon.weathernow.utils.getWeatherIcon

@Composable
fun WeatherCard(forecast: WeatherForecast) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp)) // Rounded corners
            .background(Color.LightGray), // Background color
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val iconRes = getWeatherIcon(forecast.description)
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = forecast.description,
            modifier = Modifier
                .size(48.dp)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = forecast.day)
        Text(text = "${forecast.temperature} | ${forecast.description}")
    }
}










