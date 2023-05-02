package dev.mathewsmobile.picquest

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.mathewsmobile.picquest.components.NewQuestScreen
import dev.mathewsmobile.picquest.components.QuestListScreen
import dev.mathewsmobile.picquest.screen.MapScreen
import dev.mathewsmobile.picquest.ui.theme.PicQuestTheme
import dev.mathewsmobile.picquest.viewmodel.MapViewModel
import dev.mathewsmobile.picquest.viewmodel.NewQuestViewModel
import dev.mathewsmobile.picquest.viewmodel.QuestsViewModel
import org.osmdroid.views.MapView

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    companion object {
        const val REQUEST_PERMISSIONS_REQUEST_CODE = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestPermissionsIfNecessary(listOf()) // TODO

        setContent {
            val navController = rememberNavController()
            PicQuestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = QuestListScreen.navRoute) {
                        composable(QuestListScreen.navRoute) {
                            val viewModel by viewModels<QuestsViewModel>()
                            QuestListScreen(viewModel, navController)
                        }
                        composable(NewQuestScreen.navRoute) {
                            val viewModel by viewModels<NewQuestViewModel>()
                            NewQuestScreen(viewModel, navController)
                        }
                        composable(MapScreen.navRoute) {
                            val viewModel by viewModels<MapViewModel>()
                            MapScreen(viewModel, navController)
                        }
                    }
                }
            }
        }
    }

    private fun requestPermissionsIfNecessary(permissions: List<String>) {
        val permissionsToRequest = ArrayList<String>()
        permissions.forEach { permission ->
            if (ContextCompat.checkSelfPermission(this, permission)
                != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                permissionsToRequest.add(permission)
            }
        }
        if (permissionsToRequest.size > 0) {
            ActivityCompat.requestPermissions(
                this,
                permissionsToRequest.toTypedArray(),
                REQUEST_PERMISSIONS_REQUEST_CODE)
        }
    }
}