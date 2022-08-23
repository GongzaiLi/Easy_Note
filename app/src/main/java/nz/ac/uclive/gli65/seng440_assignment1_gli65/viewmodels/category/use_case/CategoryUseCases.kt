package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.category.use_case

import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.category.use_case.*

data class CategoryUseCases(
    val getUseCase: GetUseCase,
    val deleteUseCase: DeleteUseCase,
    val addUseCase: AddUseCase,
    val getEventCount: GetEventCount,
    //val pickCategory: PickCategoryUseCase,
)