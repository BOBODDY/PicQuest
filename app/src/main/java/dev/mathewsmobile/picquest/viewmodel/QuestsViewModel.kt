package dev.mathewsmobile.picquest.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mathewsmobile.picquest.data.QuestRepository
import dev.mathewsmobile.picquest.data.model.Location
import dev.mathewsmobile.picquest.data.model.Quest
import dev.mathewsmobile.picquest.data.model.TimeOfDay
import dev.mathewsmobile.picquest.data.model.TimeOfDay.SUNRISE
import dev.mathewsmobile.picquest.data.model.TimeOfDay.SUNSET
import dev.mathewsmobile.picquest.data.model.Weather
import dev.mathewsmobile.picquest.data.model.Weather.SUNNY
import dev.mathewsmobile.picquest.viewmodel.UiStatus.Loaded
import dev.mathewsmobile.picquest.viewmodel.UiStatus.Loading
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

enum class UiStatus {
    Loading, Error, Loaded
}

data class QuestsUiState(
    val status: UiStatus = Loaded,
    val quests: List<Quest> = emptyList()
)

@HiltViewModel
class QuestsViewModel @Inject constructor(
    private val repository: QuestRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(QuestsUiState())
    val uiState: StateFlow<QuestsUiState> = _uiState.asStateFlow()

    fun fetchQuests() {
        viewModelScope.launch {
            _uiState.emit(QuestsUiState(Loading, emptyList()))
            try {
                val quests = repository.getQuests()
                _uiState.emit(QuestsUiState(Loaded, quests))
            } catch (e: Exception) {
                Log.e("QuestsViewModel", "Error loading quests", e)
                _uiState.emit(QuestsUiState(UiStatus.Error, emptyList()))
            }
        }
    }
}