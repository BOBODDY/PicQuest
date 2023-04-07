package dev.mathewsmobile.picquest.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.mathewsmobile.picquest.data.model.TimeOfDay
import dev.mathewsmobile.picquest.data.model.Weather
import dev.mathewsmobile.picquest.ui.theme.PicQuestTheme
import dev.mathewsmobile.picquest.viewmodel.NewQuestViewModel

object NewQuestScreen {
    const val navRoute = "NewQuestScreen"
}

@Composable
fun NewQuestScreen(viewModel: NewQuestViewModel, onQuestSaved: () -> Unit) {

    val name by viewModel.name.collectAsState()
    val description by viewModel.description.collectAsState()
    var desiredWeather by remember { mutableStateOf(setOf<Weather>()) }
    var desiredTime by remember { mutableStateOf(setOf<TimeOfDay>()) }

    NewQuestComponent(
        name = name,
        description = description,
        desiredWeather = desiredWeather,
        desiredTime = desiredTime,
        onNameChanged = { viewModel.setName(it) },
        onDescriptionChanged = { viewModel.setDescription(it) },
        onWeatherChanged = {
            desiredWeather = if (desiredWeather.contains(it)) {
                desiredWeather - it
            } else {
                desiredWeather + it
            }
        },
        onTimeChanged = {
            desiredTime = if (desiredTime.contains(it)) {
                desiredTime - it
            } else {
                desiredTime + it
            }
        },
        onSaveClicked = {
            viewModel.addQuest()
            onQuestSaved()
        }
    )
}

@Composable
fun NewQuestComponent(
    name: String,
    description: String,
    desiredWeather: Set<Weather>,
    desiredTime: Set<TimeOfDay>,
    onNameChanged: (String) -> Unit,
    onDescriptionChanged: (String) -> Unit,
    onWeatherChanged: (Weather) -> Unit,
    onTimeChanged: (TimeOfDay) -> Unit,
    onSaveClicked: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "New Quest", style = MaterialTheme.typography.h2)

        TextField(
            modifier = Modifier.fillMaxWidth(0.9f),
            value = name,
            onValueChange = onNameChanged,
            placeholder = { Text("Name (required)") })

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(0.9f),
            value = description,
            onValueChange = onDescriptionChanged,
            placeholder = { Text("Description") })

        Spacer(modifier = Modifier.height(16.dp))

        DesiredWeatherPicker(
            selectedOptions = desiredWeather.toList(),
            onWeatherSelected = onWeatherChanged
        )

        Spacer(modifier = Modifier.height(16.dp))

        DesiredTimePicker(selectedOptions = desiredTime.toList(), onTimeSelected = onTimeChanged)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onSaveClicked) {
            Text("Save")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun NewQuestPreview() {
    PicQuestTheme {
        Surface(color = MaterialTheme.colors.background) {
            NewQuestComponent(
                name = "Pike's Peak",
                description = "Tall mountain",
                setOf(Weather.SUNNY),
                setOf(TimeOfDay.SUNSET),
                {}, {}, {}, {}, {}
            )
        }
    }
}
