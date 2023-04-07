package dev.mathewsmobile.picquest.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Snowshoeing
import androidx.compose.material.icons.filled.Thunderstorm
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.ui.graphics.vector.ImageVector

enum class Weather : Option {
    SUNNY, CLOUDY, RAINY, FOGGY, SNOWY;

    override fun getIcon(): ImageVector {
        return when (this) {
            SUNNY -> Icons.Filled.WbSunny
            CLOUDY -> Icons.Filled.Cloud
            RAINY -> Icons.Filled.Thunderstorm
            FOGGY -> Icons.Filled.Cloud
            SNOWY -> Icons.Filled.Snowshoeing
        }

    }

    override fun getName(): String {
        return when (this) {
            SUNNY -> "Sunny"
            CLOUDY -> "Cloudy"
            RAINY -> "Rainy"
            FOGGY -> "Foggy"
            SNOWY -> "Snowy"
        }
    }
}
