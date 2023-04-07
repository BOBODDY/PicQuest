package dev.mathewsmobile.picquest.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mathewsmobile.picquest.data.QuestRepository
import dev.mathewsmobile.picquest.data.model.Quest
import dev.mathewsmobile.picquest.viewmodel.UiStatus.Error
import dev.mathewsmobile.picquest.viewmodel.UiStatus.Loaded
import dev.mathewsmobile.picquest.viewmodel.UiStatus.Loading
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class UiStatus {
    Initial, Loading, Error, Loaded
}

data class QuestsUiState(
    val status: UiStatus = UiStatus.Initial,
    val quests: List<Quest> = emptyList()
)

@HiltViewModel
class QuestsViewModel @Inject constructor(
    private val repository: QuestRepository
) : ViewModel() {

   var questsUiState by mutableStateOf(QuestsUiState())

//    init {
//        fetchQuests()
//    }

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