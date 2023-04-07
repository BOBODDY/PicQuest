package dev.mathewsmobile.picquest.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

enum class TimeOfDay : Option {
    SUNRISE, SUNSET, AFTERNOON, NIGHT, GOLDEN_HOUR, BLUE_HOUR;

    override fun getIcon(): ImageVector {
        return when (this) {
            SUNSET -> Icons.Filled.WbSunny
            SUNRISE -> Icons.Filled.WbSunny
            AFTERNOON -> Icons.Filled.Grass
            NIGHT -> Icons.Filled.NightShelter
            GOLDEN_HOUR -> Icons.Filled.GridGoldenratio
            BLUE_HOUR -> Icons.Filled.Bluetooth
        }

    }

    override fun getName(): String {
        return when(this) {
            SUNRISE -> "Sunrise"
            SUNSET -> "Sunset"
            AFTERNOON -> "Afternoon"
            NIGHT -> "Night"
            GOLDEN_HOUR -> "Golden hour"
            BLUE_HOUR -> "Blue hour"
        }
    }
}