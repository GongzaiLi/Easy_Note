package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.event

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.event.use_case.UpdateEvent
import javax.inject.Inject

class EventViewModel @Inject constructor(
    private val eventUseCases: EventUseCases
) : ViewModel() {

    private val _state = mutableStateOf(EventState()) // todo 多种状态
    val state: State<EventState> = _state

    private var getEventJob: Job? = null

    init {
        getEvents()
    }

    fun onEvent(event: EventEvent) {
        when (event) {
            is EventEvent.DeleteEvent -> {
                viewModelScope.launch {
                    eventUseCases.deleteEvent(event.event)
                }
            }
            is EventEvent.AddEvent -> {
                viewModelScope.launch {
                    eventUseCases.addEvent(event.event)
                }
            }
            is EventEvent.UpdateEvent -> {
                viewModelScope.launch {
                    eventUseCases.updateEvent(event.event)
                }
            }
        }
    }


    private fun getEvents() {
        getEventJob?.cancel()
        getEventJob = eventUseCases.getEvents().onEach { events ->
            _state.value = state.value.copy(
                events = events
            )
        }.launchIn(viewModelScope)

    }
}