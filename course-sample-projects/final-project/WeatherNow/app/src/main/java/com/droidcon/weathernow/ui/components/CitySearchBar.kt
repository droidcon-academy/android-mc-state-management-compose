package com.droidcon.weathernow.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitySearchBar(
    cities: List<String>, // Assuming you have a list of city names as strings
    onCitySelected: (String) -> Unit
) {
    var text by rememberSaveable { mutableStateOf("") }

    Column {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter city name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                if (text in cities) {
                    onCitySelected(text)
                } else {
                    // Handle the case where the city is not in the list
                }
            },
            modifier = Modifier.align(Alignment.End),
            enabled = text in cities // Only enable the button if the city is in the list
        ) {
            Text("Get Weather")
        }
    }
}

