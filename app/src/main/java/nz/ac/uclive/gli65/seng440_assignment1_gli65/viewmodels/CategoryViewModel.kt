package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryUseCases: CategoryUseCases
) : ViewModel() {


    // todo init

    private val _state = mutableStateOf(CategoryState()) // todo 多种状态
    val state: State<CategoryState> = _state

    init {
        getCetCategories()
    }

    private var getCategoryJob: Job? = null

    fun onEvent(event: CategoryEvent) {
        when (event) {
            is CategoryEvent.DeleteCategory -> {
                viewModelScope.launch {
                    categoryUseCases.deleteUseCase(event.category)
                }
            }
            is CategoryEvent.AddCategory -> {
                viewModelScope.launch {
                    categoryUseCases.addUseCase(event.category)
                }
            }
//            is CategoryEvent.PickCategory -> {
//
//            }
//            is CategoryEvent.GetCategories -> {
//                useCases.getUseCase()
//                    .onEach { categories ->
//                        _state.value = state.value.copy(
//                            categories = categories,
//                        )
//                    }
//                    .launchIn(viewModelScope)
//            }
        }
    }

    private fun getCetCategories() {
        getCategoryJob?.cancel()
        getCategoryJob = categoryUseCases.getUseCase()
            .onEach { categories ->
                _state.value = state.value.copy(
                    categories = categories,
                )
            }
            .launchIn(viewModelScope)
    }




}