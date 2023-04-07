package dev.mathewsmobile.picquest.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.EnergySavingsLeaf
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import dev.mathewsmobile.picquest.viewmodel.QuestsViewModel
import dev.mathewsmobile.picquest.viewmodel.UiStatus

object QuestListScreen {
    const val navRoute = "QuestListScreen"
}

@Composable
fun QuestListScreen(viewModel: QuestsViewModel, handleNewQuest: () -> Unit) {
    val state by viewModel.uiState.collectAsState()
    when (state.status) {
        UiStatus.Loading -> {
            viewModel.fetchQuests()
            Text("Loading...")
        }
        UiStatus.Loaded -> {
            Scaffold(floatingActionButton = {
                FloatingActionButton(onClick = { handleNewQuest() }) {
                    Icon(Icons.Filled.Add, "Add new quest")
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
                        items(state.quests.size) {
                            state.quests.map {
                                QuestCard(it)
                            }
                        }
                    }
                }
            }
        }
        UiStatus.Error -> Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Error!")
            Button(onClick = ({ viewModel.fetchQuests() })) {
                Text("Retry")
            }
        }
    }
}
