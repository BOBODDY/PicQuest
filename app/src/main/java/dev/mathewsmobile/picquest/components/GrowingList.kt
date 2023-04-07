package dev.mathewsmobile.picquest.components

import androidx.annotation.FloatRange
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.material.math.MathUtils.lerp
import kotlin.math.abs

fun getScaleFactor(
    itemIndex: Int,
    currentIndex: Float,
    minScale: Float,
    maxScale: Float,
): Float {
    val scaleFactor = 1 - abs(itemIndex - currentIndex)
    return lerp(minScale, maxScale, scaleFactor)
}

@Composable
fun AnimatedCard(scale: Float, content: @Composable () -> Unit) {
    val animatedScale = animateFloatAsState(targetValue = scale).value

    Card(modifier = Modifier.scale(animatedScale).sizeIn(maxWidth = 300.dp, maxHeight = 300.dp)) {
        content()
    }
}

@Composable
fun AnimatedCardList() {
    val items = (1..20).toList()
    val listState = rememberLazyListState()
    val currentIndex by remember {
        derivedStateOf {
            (listState.firstVisibleItemIndex.toFloat() +
                    (listState.firstVisibleItemScrollOffset / 100f))
        }
    }

    LazyColumn(state = listState) {
        itemsIndexed(items) { index, item ->
            val scale = getScaleFactor(index, currentIndex, 1f, 1.4f)
            AnimatedCard(scale = scale) {
                // Add your card content here, e.g. Text or Image
                Text(text = "Card $item", fontSize = 20.sp, modifier = Modifier.padding(16.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimatedListPreview() {
    AnimatedCardList()
}
