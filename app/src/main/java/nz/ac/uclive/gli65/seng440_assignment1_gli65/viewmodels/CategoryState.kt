package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels

import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Category

data class CategoryState(
    val categories: List<Category> = emptyList(),
    val pickCategory: Category? = null, // todo all
    val screenName: String = "",

    val categoryIcons: List<String> = listOf(
        "ic_favorite_24",
        "ic_life_24",
        "ic_star_24",
        "ic_anchor_24",
        "ic_airplane_ticket_24",
        "ic_breakfast_dining_24"
    ),

    val selectedIcon: String = "ic_favorite_24",

    val categoryTitle: String = "",


)