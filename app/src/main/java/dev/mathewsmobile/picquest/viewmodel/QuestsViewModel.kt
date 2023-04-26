package dev.mathewsmobile.picquest.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mathewsmobile.picquest.data.QuestRepository
import dev.mathewsmobile.picquest.data.model.Quest
import dev.mathewsmobile.picquest.data.ui.UiStatus
import dev.mathewsmobile.picquest.data.ui.UiStatus.Error
import dev.mathewsmobile.picquest.data.ui.UiStatus.Loaded
import dev.mathewsmobile.picquest.data.ui.UiStatus.Loading
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestsViewModel @Inject constructor(
    private val repository: QuestRepository
) : ViewModel() {
   var questsUiState by mutableStateOf(QuestsUiState())

    fun fetchQuests() {
        viewModelScope.launch {
            try {
                questsUiState = QuestsUiState(status = Loading)
                val quests = repository.getQuests()
                questsUiState = QuestsUiState(status = Loaded, quests = quests)
            } catch (e: Exception) {
                questsUiState = QuestsUiState(status = Error)
            }
        }
    }
}

data class QuestsUiState(
    val status: UiStatus = UiStatus.Initial,
    val quests: List<Quest> = emptyList()
)
