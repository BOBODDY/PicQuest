package dev.mathewsmobile.picquest.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.mathewsmobile.picquest.data.model.Weather
import dev.mathewsmobile.picquest.utils.IconUtils.getIcon

@Composable
fun WeatherList(weather: List<Weather>) {
    Row {
        weather.map { WeatherIcon(weather = it) }
    }
}

@Composable
fun WeatherIcon(weather: Weather) {
    Box(modifier = Modifier.padding(8.dp)) {
        Icon(weather.getIcon(), "Weather")
    }
}