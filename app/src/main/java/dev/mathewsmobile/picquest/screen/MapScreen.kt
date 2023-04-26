package dev.mathewsmobile.picquest.screen

import androidx.compose.runtime.Composable
import dev.mathewsmobile.picquest.components.MapComponent

object MapScreen {
    const val navRoute = "MapScreen"
}

@Composable
fun MapScreen() {
    MapComponent()
}