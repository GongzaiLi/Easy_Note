package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels

import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Category

data class CategoryState(
    val categories: List<Category> = emptyList(),
    val pickCategory: Category? = null, // todo all
    val screenName: String = ""
)