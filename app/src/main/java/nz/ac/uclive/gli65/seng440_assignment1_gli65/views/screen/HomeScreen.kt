package nz.ac.uclive.gli65.seng440_assignment1_gli65.views.screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.CategoryEvent
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.ViewModel


@Composable
fun HomeScreen(
    //navController: NavController,
    viewModel: ViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    println("VM 1111111  =  ${state.categories.size}")
    viewModel.onEvent(CategoryEvent.GetCategories)
    println("VM 1111111  =  ${state.categories.size}")
}