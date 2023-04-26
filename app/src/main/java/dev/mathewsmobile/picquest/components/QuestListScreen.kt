package dev.mathewsmobile.picquest.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Map
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import dev.mathewsmobile.picquest.viewmodel.QuestsViewModel
import dev.mathewsmobile.picquest.data.ui.UiStatus

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
                    FloatingActionButton(onClick = { /* TODO */ }) {
                        Icon(Icons.Filled.Map, "View Map")
                    }

                    ExtendedFloatingActionButton(
                        icon = { Icon(Icons.Filled.Add, "Add new quest") },
                        text = { Text("Add") },
                        onClick = { navController.navigate(NewQuestScreen.navRoute) })
                }
            }) { padding ->
                Column(modifier = Modifier.padding(padding)) {
                    Text(text = "Quest Board",
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.h1
                    )
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colors.primary)
                    ) {
                        items(state.quests) { quest ->
                            QuestCard(quest)
                        }
                    }
                }
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
