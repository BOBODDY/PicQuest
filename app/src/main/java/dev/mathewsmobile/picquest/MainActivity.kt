package dev.mathewsmobile.picquest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.mathewsmobile.picquest.components.NewQuestScreen
import dev.mathewsmobile.picquest.components.QuestListScreen
import dev.mathewsmobile.picquest.ui.theme.PicQuestTheme
import dev.mathewsmobile.picquest.viewmodel.NewQuestViewModel
import dev.mathewsmobile.picquest.viewmodel.QuestsViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                            QuestListScreen(viewModel) {
                                navController.navigate(NewQuestScreen.navRoute)
                            }
                        }
                        composable(NewQuestScreen.navRoute) {
                            val viewModel by viewModels<NewQuestViewModel>()
                            NewQuestScreen(viewModel) { navController.popBackStack()}
                        }
                    }
                }
            }
        }
    }
}