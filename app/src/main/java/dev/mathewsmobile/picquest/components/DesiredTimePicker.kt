package dev.mathewsmobile.picquest.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dev.mathewsmobile.picquest.data.model.TimeOfDay
import dev.mathewsmobile.picquest.data.model.Weather
import dev.mathewsmobile.picquest.ui.theme.PicQuestTheme

@Preview
@Composable
fun TimePickerPreview() {
    PicQuestTheme {
        Surface(color = MaterialTheme.colors.background) {
            DesiredTimePicker(
                selectedOptions = listOf(TimeOfDay.SUNSET),
                onTimeSelected = {}
            )
        }
    }
}

@Composable
fun DesiredTimePicker(
    modifier: Modifier = Modifier,
    selectedOptions: List<TimeOfDay>,
    onTimeSelected: (TimeOfDay) -> Unit
) {
    Column(modifier = modifier) {
        Text(
            "Time of Day",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        MultiChoicePicker(
            options = TimeOfDay.values().toList(),
            selectedOptions = selectedOptions,
            onOptionSelected = onTimeSelected
        )
    }
}