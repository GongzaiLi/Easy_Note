package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.use_case

import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.use_case.category.AddCategoryUseCase
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.use_case.category.DeleteCategoryUseCase
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.use_case.event.DeleteEventUseCase
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.use_case.category.GetCategoriesUseCase
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.use_case.event.AddEventUseCase
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.use_case.event.GetEventsUseCase

data class UseCases(
    val getCategoriesUseCase: GetCategoriesUseCase,
    val deleteCategoryUseCase: DeleteCategoryUseCase,
    val getEventsUseCase: GetEventsUseCase,
    val deleteEventUseCase: DeleteEventUseCase,
    val addCategoryUseCase: AddCategoryUseCase,
    val addEventUseCase: AddEventUseCase,
)
