package nz.ac.uclive.gli65.seng440_assignment1_gli65

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import dagger.hilt.android.AndroidEntryPoint
import nz.ac.uclive.gli65.seng440_assignment1_gli65.ui.theme.Seng440_assignment1_gli65Theme
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.Navigation
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.screen.HomeScreen

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Seng440_assignment1_gli65Theme {
                Navigation()
            }
        }
    }
}
