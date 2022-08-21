package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
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
        getCetCategories()
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
            }
            is CategoryEvent.AddCategory -> {
                viewModelScope.launch {
                    categoryUseCases.addUseCase(event.category)
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
//            is CategoryEvent.UpdateEventCount -> {
//                test()
//            }


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

        viewModelScope.launch {
            categoryUseCases.getUseCase().collectLatest { categories ->
                delay(100L) //
                categories.map { category ->
                    categoryUseCases.getEventCount(category).onEach {
                        category.eventNumber = it
                    }.launchIn(viewModelScope)
                }
                _state.value = state.value.copy(
                    categories = categories,
                    pickCategory = categories[0],
                    screenName = categories[0].title
                )
            }

        }

//        getCategoryJob = categoryUseCases.getUseCase()
//            .onEach { categories ->
//                delay(2000L) // https://www.youtube.com/watch?v=6dRwaXH2cYA
//
//                categories.map { category ->
//                    categoryUseCases.getEventCount(category).onEach {
//                        category.eventNumber = it
//                    }.launchIn(viewModelScope)
//                }
//
//                _state.value = state.value.copy(
//                    categories = categories,
//                    pickCategory = categories[0],
//                    screenName = categories[0].title
//                )
//            }
//            .launchIn(viewModelScope)
    }

    private fun test() {
//        getCategoryCountJob?.cancel()
//        _state.value.categories.map { category ->
//            getCategoryCountJob = categoryUseCases.getEventCount(category).onEach {
//                category.eventNumber = it
//            }.launchIn(viewModelScope)
//        }
    }


}