package dev.mathewsmobile.picquest.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.mathewsmobile.picquest.data.model.TimeOfDay
import dev.mathewsmobile.picquest.utils.IconUtils.getIcon

@Composable
fun TimeOfDayList(times: List<TimeOfDay>) {
    Row {
        times.map { TimeOfDayIcon(timeOfDay = it) }
    }
}

@Composable
fun TimeOfDayIcon(timeOfDay: TimeOfDay) {
    Box(modifier = Modifier.padding(8.dp)) {
        Icon(timeOfDay.getIcon(), "Time of day")
    }
}