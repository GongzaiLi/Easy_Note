package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.category

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryUseCases: CategoryUseCases
) : ViewModel() {

    private val _state = mutableStateOf(CategoryState())  // keep data
    val state: State<CategoryState> = _state

    init {
        getCategories()
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
                    _state.value = state.value.copy(
                        selectedIcon = _state.value.categoryIcons.get(0),
                        categoryTitle = ""
                    )
                }
            }
            is CategoryEvent.PickCategory -> {
                _state.value = state.value.copy(
                    pickCategory = event.category,
                    screenName = event.category.title
                )
            }
            is CategoryEvent.PickIcon -> {
                _state.value = state.value.copy(
                    selectedIcon = event.iconName
                )
            }
            is CategoryEvent.UpdateCategoryTitle -> {
                _state.value = state.value.copy(
                    categoryTitle = event.title
                )
            }
            is CategoryEvent.UpdateEventCount -> {
                //getCategories()
                updateCategoryNum()
            }
        }
    }

    private fun getCategories() {
        getCategoryJob?.cancel()

        // https://www.youtube.com/watch?v=6dRwaXH2cYA and io
        viewModelScope.launch {
            categoryUseCases.getUseCase().collectLatest { categories ->
                withContext(Dispatchers.IO) {
                    val newCategories = categories.map { category ->
                        val job = async {
                            categoryUseCases.getEventCount(category)
                        }
                        category.eventNumber = job.await().first()
                        category // return value
                    }

                    _state.value = state.value.copy(
                        categories = newCategories,
                        pickCategory = newCategories[0],
                        screenName = newCategories[0].title
                    )
                }
            }

        }
    }

    private fun updateCategoryNum() {

        viewModelScope.launch {
            val newCategories = _state.value.categories.map { category ->
                val job = async {
                    categoryUseCases.getEventCount(category)
                }
                category.eventNumber = job.await().first()
                category // return value
            }

            _state.value = state.value.copy(
                categories = newCategories,
            )


        }

    }
}


