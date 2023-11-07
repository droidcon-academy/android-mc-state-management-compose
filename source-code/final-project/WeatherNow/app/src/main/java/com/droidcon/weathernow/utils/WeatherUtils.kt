package com.droidcon.weathernow.utils

import androidx.annotation.DrawableRes
import com.droidcon.weathernow.R

@DrawableRes
fun getWeatherIcon(description: String): Int {
    return when {
        description.contains("sunny", ignoreCase = true) -> R.drawable.ic_sunny
        description.contains("rainy", ignoreCase = true) -> R.drawable.ic_rainy
        description.contains("cloudy", ignoreCase = true) -> R.drawable.ic_cloudy
        else -> R.drawable.ic_unknown // Default icon for unknown conditions
    }
}