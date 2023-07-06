package dev.mathewsmobile.picquest.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.mathewsmobile.picquest.ui.theme.Parchment
import dev.mathewsmobile.picquest.ui.theme.PicQuestTheme

@Composable
fun ParchmentBackground(content: @Composable ColumnScope.() -> Unit) {
    Column(modifier = Modifier
        .background(Parchment)
        .padding(all = 8.dp)
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun ParchmentPreview() {
    PicQuestTheme {
        ParchmentBackground {
            DesiredWeatherPicker(selectedOptions = emptyList(), onWeatherSelected = {})
        }
    }
}