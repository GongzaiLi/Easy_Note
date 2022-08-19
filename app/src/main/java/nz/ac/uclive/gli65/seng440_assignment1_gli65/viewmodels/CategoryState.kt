package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels

import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Category

data class CategoryState(
    val categories: List<Category> = emptyList(),
    //val categoryId : Int = 1, // todo all
)