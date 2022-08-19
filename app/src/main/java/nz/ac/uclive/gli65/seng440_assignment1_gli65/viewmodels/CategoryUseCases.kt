package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels

import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.use_case.AddUseCase
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.use_case.DeleteUseCase
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.use_case.GetUseCase
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.use_case.PickCategoryUseCase

data class CategoryUseCases(
    val getUseCase: GetUseCase,
    val deleteUseCase: DeleteUseCase,
    val addUseCase: AddUseCase,
    //val pickCategory: PickCategoryUseCase,
)