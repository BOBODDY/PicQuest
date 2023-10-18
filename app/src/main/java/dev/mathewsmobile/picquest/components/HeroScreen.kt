package dev.mathewsmobile.picquest.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import kotlin.math.min


@Composable
fun CharacterScreen() {
    val scrollState = rememberScrollState()

    ConstraintLayout {
        AsyncImage(
            model = "https://thebanffblog.com/wp-content/uploads/2020/03/The-Banff-Blog-Featured-Image-Lake-Louise-1536x1024.jpg",
            contentDescription = null,
            modifier = Modifier
                .graphicsLayer {
                    alpha = min(1f, 1 - (scrollState.value / 600f))
                    translationY = -scrollState.value * 0.1f
                }
        )

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
        ) {
            Text("Pretend this is lots of text")
        }
    }
}

@Preview
@Composable
fun HeroPreview() {
    CharacterScreen()
}