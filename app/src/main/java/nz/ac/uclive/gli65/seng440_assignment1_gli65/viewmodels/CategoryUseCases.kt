package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels

import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.use_case.*

data class CategoryUseCases(
    val getUseCase: GetUseCase,
    val deleteUseCase: DeleteUseCase,
    val addUseCase: AddUseCase,
    val getEventCount: GetEventCount,
    //val pickCategory: PickCategoryUseCase,
)