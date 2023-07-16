package dev.mathewsmobile.picquest.screen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.ImageOnly
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import dev.mathewsmobile.picquest.components.NewQuestComponent
import dev.mathewsmobile.picquest.data.model.TimeOfDay
import dev.mathewsmobile.picquest.data.model.Weather
import dev.mathewsmobile.picquest.viewmodel.NewQuestViewModel

object NewQuestScreen {
    const val navRoute = "NewQuestScreen"
}

@Composable
fun NewQuestScreen(viewModel: NewQuestViewModel, navController: NavController) {

    val name by viewModel.name.collectAsState()
    val description by viewModel.description.collectAsState()
    var desiredWeather by remember { mutableStateOf(setOf<Weather>()) }
    var desiredTime by remember { mutableStateOf(setOf<TimeOfDay>()) }
    val examplePhotos by viewModel.photos.collectAsState()

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickMultipleVisualMedia()) { uris ->
        uris.forEach { viewModel.addPhoto(it) }
    }

    NewQuestComponent(
        name = name,
        description = description,
        desiredWeather = desiredWeather,
        desiredTime = desiredTime,
        examplePhotos = examplePhotos,
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
            navController.popBackStack()
        },
        onAddPhotoClicked = {
            launcher.launch(PickVisualMediaRequest(mediaType = ImageOnly))
        },
        onCloseClicked = {
            navController.popBackStack()
        }
    )
}
