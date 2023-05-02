package dev.mathewsmobile.picquest.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Map
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.mathewsmobile.picquest.components.LoadingComponent
import dev.mathewsmobile.picquest.components.QuestList
import dev.mathewsmobile.picquest.data.ui.UiStatus
import dev.mathewsmobile.picquest.viewmodel.QuestsViewModel

object QuestListScreen {
    const val navRoute = "QuestListScreen"
}

@Composable
fun QuestListScreen(
    viewModel: QuestsViewModel,
    navController: NavController
) {
    val state = viewModel.questsUiState
    when (state.status) {
        UiStatus.Loaded -> {
            Scaffold(floatingActionButton = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    FloatingActionButton(onClick = { navController.navigate(MapScreen.navRoute) }) {
                        Icon(Icons.Filled.Map, "View Map")
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    ExtendedFloatingActionButton(
                        icon = { Icon(Icons.Filled.Add, "Add new quest") },
                        text = { Text("Add") },
                        onClick = { navController.navigate(NewQuestScreen.navRoute) })
                }
            }) { padding ->
                QuestList(padding, state)
            }
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
