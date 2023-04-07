package dev.mathewsmobile.picquest.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.mathewsmobile.picquest.data.model.Option
import dev.mathewsmobile.picquest.data.model.Options
import dev.mathewsmobile.picquest.data.model.Weather
import dev.mathewsmobile.picquest.ui.theme.PicQuestTheme

@Preview
@Composable
fun MultiPickerPreview() {
    PicQuestTheme {
        Surface(color = MaterialTheme.colors.background) {
            MultiChoicePicker(
                options = Options.values().toList(),
                selectedOptions = emptyList(),
                onOptionSelected = {}
            )
        }
    }
}

@Composable
fun <T : Option> MultiChoicePicker(
    modifier: Modifier = Modifier,
    options: List<T>,
    selectedOptions: List<T>,
    onOptionSelected: (T) -> Unit
) {
    Column() {
        options.chunked(5).map { optionLine ->
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                optionLine.map {
                    val background = if (selectedOptions.contains(it)) {
                        Color.Gray.copy(alpha = 0.66f)
                    } else {
                        Color.Transparent
                    }
                    Column(
                        modifier = Modifier
                            .clickable { onOptionSelected(it) }
                            .background(background),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            it.getIcon(),
                            contentDescription = it.getName(),
                            tint = MaterialTheme.colors.secondary,
                            modifier = modifier.padding(
                                start = 16.dp,
                                top = 16.dp,
                                end = 16.dp,
                                bottom = 4.dp
                            )
                        )
                        Text(text = it.getName())
                    }
                }
            }
        }

    }
}
