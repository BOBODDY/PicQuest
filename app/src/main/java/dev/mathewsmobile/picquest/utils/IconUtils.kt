package dev.mathewsmobile.picquest.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import dev.mathewsmobile.picquest.data.model.TimeOfDay
import dev.mathewsmobile.picquest.data.model.TimeOfDay.*
import dev.mathewsmobile.picquest.data.model.Weather
import dev.mathewsmobile.picquest.data.model.Weather.*

object IconUtils {

    fun Weather.getIcon(): ImageVector {
        return when (this) {
            SUNNY -> Icons.Filled.WbSunny
            CLOUDY -> Icons.Filled.Cloud
            RAINY -> Icons.Filled.Thunderstorm
            FOGGY -> Icons.Filled.Cloud
            SNOWY -> Icons.Filled.Snowshoeing
        }
    }

    fun TimeOfDay.getIcon(): ImageVector {
        return when (this) {
            SUNSET -> Icons.Filled.WbSunny
            SUNRISE -> Icons.Filled.WbSunny
            AFTERNOON -> Icons.Filled.Grass
            NIGHT -> Icons.Filled.NightShelter
            GOLDEN_HOUR -> Icons.Filled.GridGoldenratio
            BLUE_HOUR -> Icons.Filled.Bluetooth
        }
    }
}