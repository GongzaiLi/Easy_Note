package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.event

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Event
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val eventUseCases: EventUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    // todo ?????
    private val _state = mutableStateOf(EventState()) // todo 多种状态
    val state: State<EventState> = _state


    private val _eventTitle = mutableStateOf(
        EventTextFieldState(
            hint = "Enter title..."
        )
    )
    val eventTitle: State<EventTextFieldState> = _eventTitle

    private val _eventDescription = mutableStateOf(
        EventTextFieldState(
            hint = "Enter some description ..."
        )
    )
    val eventDescription: State<EventTextFieldState> = _eventDescription


    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveEvent : UiEvent()

    }

    private var selectedEventId: Long? = null

    init {
        savedStateHandle.get<Long>("eventId")?.let { id ->
            if (id != -1L) {
                viewModelScope.launch {
                    eventUseCases.getEvent(id)?.also {
                        selectedEventId = it.id
                        _eventTitle.value = eventTitle.value.copy(
                            text = it.title,
                            isHintVisible = false
                        )
                        _eventDescription.value = eventDescription.value.copy(
                            text = it.description ?: "",
                            hint = if (it.description == null || it.description.isBlank()) "Enter some description ..." else "",
                            isHintVisible = it.description == null || it.description.isBlank()
                        )
                        _state.value = state.value.copy(
                            selectedColor = it.color
                        )
                    }
                }
            }
        }
    }
    // todo Up ============================

    private var getEventJob: Job? = null


    fun onEvent(event: EventEvent) {
        when (event) {
            is EventEvent.DeleteEvent -> {
                viewModelScope.launch {
                    eventUseCases.deleteEvent(event.event)
                }
            }
            is EventEvent.AddEvent -> {
                viewModelScope.launch {

                    eventUseCases.addEvent(
                        Event(
                            id = selectedEventId,
                            title = eventTitle.value.text,
                            description = eventDescription.value.text,
                            timestamp = event.time,
                            color = state.value.selectedColor,
                            categoryId = event.categoryId
                        )

                    )
                    _eventFlow.emit(UiEvent.SaveEvent)


                }
            }
            is EventEvent.GetEvents -> {
                getEvents(event.id)
            }
            is EventEvent.EnteredTitle -> {
                _eventTitle.value = eventTitle.value.copy(
                    text = event.text
                )
            }
            is EventEvent.ChangeTitleFocus -> {
                _eventTitle.value = eventTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused && eventTitle.value.text.isBlank()
                )
            }
            is EventEvent.EnteredDescription -> {
                _eventDescription.value = eventDescription.value.copy(
                    text = event.text
                )
            }
            is EventEvent.ChangeDescriptionFocus -> {
                _eventDescription.value = eventDescription.value.copy(
                    isHintVisible = !event.focusState.isFocused && eventDescription.value.text.isBlank()
                )
            }
            is EventEvent.ChangeColor -> {
                _state.value = state.value.copy(
                    selectedColor = event.color
                )
            }
        }
    }


    // todo many need fix
    private fun getEvents(categoryId: Long?) {
        var id = 1L // all type
        if (categoryId != null) {
            id = categoryId
        }
        getEventJob?.cancel()
        getEventJob = eventUseCases.getEvents(id).onEach { events ->
            _state.value = state.value.copy(
                events = events
            )
        }.launchIn(viewModelScope)

    }
}