package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.use_case.UseCases
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val useCase: UseCases
) : ViewModel() {

    private val _state = mutableStateOf<EventState>(EventState())
    val state: State<EventState> = _state



    fun onEvent(toggleEvent: ToggleEvent) {
        when (toggleEvent) {
            is ToggleEvent.DeleteEvent -> {
                viewModelScope.launch {
                    useCase.deleteEventUseCase(toggleEvent.event)
                }
            }
            is ToggleEvent.DeleteCategory -> {
                viewModelScope.launch {
                    useCase.deleteCategoryUseCase(toggleEvent.category)
                }
            }
            is ToggleEvent.AddEvent -> {
                viewModelScope.launch {
                    useCase.addEventUseCase(toggleEvent.event) // todo
                }
            }
            is ToggleEvent.AddCategory -> {
                viewModelScope.launch {
                    useCase.addCategoryUseCase(toggleEvent.category)
                }
            }
            else -> {}
        }
    }
}