package dev.mathewsmobile.picquest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mathewsmobile.picquest.data.QuestRepository
import dev.mathewsmobile.picquest.data.model.Location
import dev.mathewsmobile.picquest.data.model.Quest
import dev.mathewsmobile.picquest.data.model.TimeOfDay
import dev.mathewsmobile.picquest.data.model.Weather
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NewQuestViewModel @Inject constructor(private val repository: QuestRepository) : ViewModel() {

    private val _nameState = MutableStateFlow("")
    val name = _nameState.asStateFlow()
    fun setName(name: String) {
        viewModelScope.launch {
            _nameState.emit(name)
        }
    }

    private val _descriptionState = MutableStateFlow("")
    val description = _descriptionState.asStateFlow()
    fun setDescription(description: String) {
        viewModelScope.launch {
            _descriptionState.emit(description)
        }

    }

    fun addQuest() {
        val id = UUID.randomUUID()
        val quest = Quest(
            id = id,
            name = _nameState.value,
            description = _descriptionState.value,
            location = Location(id, 34.123, 105.382),
            desiredWeather = listOf(Weather.SUNNY),
            desiredTime = listOf(TimeOfDay.SUNRISE, TimeOfDay.SUNSET)
        )
        viewModelScope.launch {
            repository.addQuest(quest)
        }
    }

    fun openPhotoPicker() {
        
    }

}
