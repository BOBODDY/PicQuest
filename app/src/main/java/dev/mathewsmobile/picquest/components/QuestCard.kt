package dev.mathewsmobile.picquest.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.mathewsmobile.picquest.data.model.Quest

@Composable
fun QuestCard(quest: Quest) {
    Box(modifier = Modifier.padding(16.dp, 8.dp)) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            backgroundColor = MaterialTheme.colors.surface
        ) {
            Box(modifier = Modifier.padding(8.dp)) {
                Column {
                    Text(quest.name, style = MaterialTheme.typography.h3)
                    quest.description?.let {
                        Text(it, style = MaterialTheme.typography.body1)
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        WeatherList(weather = quest.desiredWeather)
                        TimeOfDayList(times = quest.desiredTime)
                    }
                }
            }
        }
    }
}
