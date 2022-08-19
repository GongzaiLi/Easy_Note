package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _state = mutableStateOf(CategoryState())
    val state: State<CategoryState> = _state

    fun onEvent(event: CategoryEvent) {
        when (event) {
            is CategoryEvent.DeleteCategory -> {
                viewModelScope.launch {
                    useCases.deleteUseCase(event.category)
                }
            }
            is CategoryEvent.AddCategory -> {
                viewModelScope.launch {
                    useCases.addUseCase(event.category)
                }
            }
            is CategoryEvent.GetCategories -> {
                useCases.getUseCase()
                    .onEach { categories ->
                        _state.value = state.value.copy(
                            categories = categories,
                        )
                    }
                    .launchIn(viewModelScope)
            }
        }
    }


}