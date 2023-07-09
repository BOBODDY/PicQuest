package dev.mathewsmobile.picquest.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.mathewsmobile.picquest.data.model.TimeOfDay
import dev.mathewsmobile.picquest.data.model.Weather
import dev.mathewsmobile.picquest.ui.theme.PicQuestTheme

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
    onSaveClicked: () -> Unit,
    onAddPhotoClicked: () -> Unit,
    onCloseClicked: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(Icons.Default.Close, contentDescription = "Close the screen", modifier = Modifier.padding(16.dp).clickable { onCloseClicked() })

        Text(text = "New Quest", style = MaterialTheme.typography.h2)

        PhotoPicker(onAddPhotoClicked)

        Spacer(modifier = Modifier.height(16.dp))

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
            modifier = Modifier.fillMaxWidth(0.9f),
            selectedOptions = desiredWeather.toList(),
            onWeatherSelected = onWeatherChanged
        )

        Spacer(modifier = Modifier.height(16.dp))

        DesiredTimePicker(
            modifier = Modifier.fillMaxWidth(0.9f),
            selectedOptions = desiredTime.toList(),
            onTimeSelected = onTimeChanged
        )

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
                {}, {}, {}, {}, {}, {}, {}
            )
        }
    }
}