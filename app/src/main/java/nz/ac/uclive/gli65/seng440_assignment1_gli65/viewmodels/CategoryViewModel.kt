package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Category
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryUseCases: CategoryUseCases
) : ViewModel() {


    // todo init

    private val _state = mutableStateOf(CategoryState()) // todo 多种状态
    val state: State<CategoryState> = _state

    init {
        getCategories()
        //test()
    }

    private var getCategoryJob: Job? = null
    private var getCategoryCountJob: Job? = null

    fun onEvent(event: CategoryEvent) {
        when (event) {
            is CategoryEvent.DeleteCategory -> {
                viewModelScope.launch {
                    categoryUseCases.deleteUseCase(event.category)
                }
                updateCategoryNum()
            }
            is CategoryEvent.AddCategory -> {
                viewModelScope.launch {
                    categoryUseCases.addUseCase(event.category)
                }
                updateCategoryNum()
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

    private fun getCategories() {
        getCategoryJob?.cancel()

        // io
        viewModelScope.launch {  // https://www.youtube.com/watch?v=6dRwaXH2cYA

            categoryUseCases.getUseCase().collectLatest { categories ->
                withContext(Dispatchers.IO) {
                    val newCategories = categories.map { category ->
                        val job = async {
                            categoryUseCases.getEventCount(category)
                        }
                        category.eventNumber = job.await().first()
                        category // return
                    }

                    _state.value = state.value.copy(
                        categories = newCategories,
                        pickCategory = newCategories[0],
                        screenName = newCategories[0].title
                    )
                }
            }

        }
        //onFailure
    }

    private fun updateCategoryNum() {

        viewModelScope.launch {  // https://www.youtube.com/watch?v=6dRwaXH2cYA

            val newCategories = _state.value.categories.map { category ->
                val job = async {
                    categoryUseCases.getEventCount(category)
                }
                category.eventNumber = job.await().first()
                category // return
            }

            _state.value = state.value.copy(
                categories = newCategories,
            )


        }

    }
}


