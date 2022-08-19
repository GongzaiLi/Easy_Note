package nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Category


interface ICategoryRepository { // Repository

    suspend fun insertCategory(category: Category): Long

    suspend fun updateCategory(category: Category)

    suspend fun deleteCategory(category: Category)

    fun getCategories(): Flow<List<Category>>

    fun getCountCategory(): Flow<Int>

    fun getCategoryById(categoryId: Long): Flow<Category>?
}