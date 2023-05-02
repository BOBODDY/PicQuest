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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(private val repository: QuestRepository) : ViewModel() {
    var mapUiState by mutableStateOf(MapUiState())

    fun fetchQuests() {
        viewModelScope.launch {
            try {
                mapUiState = MapUiState(status = UiStatus.Loading)
                val quests = repository.getQuests()
                mapUiState = MapUiState(status = UiStatus.Loaded, quests = quests)
            } catch (e: Exception) {
                mapUiState = MapUiState(status = UiStatus.Error)
            }
        }
    }

}

data class MapUiState(
    val status: UiStatus = UiStatus.Initial,
    val quests: List<Quest> = emptyList()
)