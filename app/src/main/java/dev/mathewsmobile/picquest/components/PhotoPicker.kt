package dev.mathewsmobile.picquest.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.mathewsmobile.picquest.ui.theme.PicQuestTheme

@Composable
fun PhotoPicker(
    photos: List<Uri>,
    maxPerLine: Int = 5,
    onAddPhoto: () -> Unit
) {
    val photos = photos.ifEmpty { listOf(null) }

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        // TODO Only center the icon, not the title
        Text(text = "Add Photos", modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp), style = MaterialTheme.typography.h3)
        photos.chunked(maxPerLine).map { photoRow ->
            Row(modifier = Modifier
                .height(64.dp)
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {

                repeat(maxPerLine) {
                    val photo = photoRow.getOrNull(it)
                    PhotoIcon(photoUri = photo)
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        PhotoButton {
            onAddPhoto()
        }
    }
}

@Composable
fun PhotoIcon(photoUri: Uri?) {
    Card(modifier = Modifier
        .size(64.dp)
        .background(Color.Gray)) {
        photoUri?.let {
            AsyncImage(model = it, contentDescription = "Your example image")
        }
    }
}

@Composable
fun PhotoButton(onClick: () -> Unit) {
    Card(modifier = Modifier
        .size(32.dp)
        .clickable { onClick() }) {
        Icon(Icons.Default.Add, contentDescription = "Add new photo")
    }
}

@Preview
@Composable
fun PhotoPickerPreview() {
    PicQuestTheme {
        Surface(color = MaterialTheme.colors.background) {
            PhotoPicker(listOf(), 5, {})
        }
    }
}