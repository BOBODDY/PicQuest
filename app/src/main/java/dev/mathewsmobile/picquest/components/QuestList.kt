package dev.mathewsmobile.picquest.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.mathewsmobile.picquest.viewmodel.QuestsUiState

@Composable
fun QuestList(
    padding: PaddingValues,
    state: QuestsUiState
) {
    Column(modifier = Modifier.padding(padding)) {
        Text(
            text = "Quest Board",
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
