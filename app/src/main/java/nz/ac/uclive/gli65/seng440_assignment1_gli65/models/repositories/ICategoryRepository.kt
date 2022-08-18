package nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repositories

import androidx.lifecycle.LiveData
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entities.Category

interface ICategoryRepository {

    suspend fun insertCategory(category: Category): Long

    suspend fun updateCategory(category: Category)

    suspend fun deleteCategory(category: Category)

    fun getCategories(): LiveData<List<Category>>

    fun getCountCategory(): LiveData<Int>

    suspend fun getCategoryById(categoryId: Long): Category?

}