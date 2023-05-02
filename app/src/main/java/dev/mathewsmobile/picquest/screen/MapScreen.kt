package dev.mathewsmobile.picquest.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import dev.mathewsmobile.picquest.components.LoadingComponent
import dev.mathewsmobile.picquest.components.MapComponent
import dev.mathewsmobile.picquest.data.ui.UiStatus
import dev.mathewsmobile.picquest.viewmodel.MapViewModel

object MapScreen {
    const val navRoute = "MapScreen"
}

@Composable
fun MapScreen(
    viewModel: MapViewModel,
    navController: NavController
) {
    val state = viewModel.mapUiState
    when (state.status) {
        UiStatus.Loaded -> {
            MapComponent(state.quests)
        }
        UiStatus.Initial -> {
            viewModel.fetchQuests()
        }
        UiStatus.Loading -> {
            LoadingComponent()
        }
        UiStatus.Error -> {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Error!")
                Button(onClick = ({ viewModel.fetchQuests() })) {
                    Text("Retry")
                }
            }
        }
    }
}