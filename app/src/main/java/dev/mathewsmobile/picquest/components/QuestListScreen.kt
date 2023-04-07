package dev.mathewsmobile.picquest.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.mathewsmobile.picquest.viewmodel.QuestsViewModel
import dev.mathewsmobile.picquest.viewmodel.UiStatus

object QuestListScreen {
    const val navRoute = "QuestListScreen"
}

@Composable
fun QuestListScreen(viewModel: QuestsViewModel, handleNewQuest: () -> Unit) {
    val state = viewModel.questsUiState
    when (state.status) {
        UiStatus.Initial -> {
            viewModel.fetchQuests()
        }
        UiStatus.Loading -> {
            LoadingComponent()
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
                        items(state.quests) { quest ->
                            QuestCard(quest)
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
