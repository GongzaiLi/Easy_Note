package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels

import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Category

sealed class CategoryEvent {

    data class DeleteCategory(val category: Category) : CategoryEvent()

    data class AddCategory(val category: Category) : CategoryEvent()

    //data class PickCategory(val categoryId: Int): CategoryEvent()

    //object GetCategories : CategoryEvent()

    // todo more event
}