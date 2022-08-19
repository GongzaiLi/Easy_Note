package nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repository.impl

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.dao.CategoryDao
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Category
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repository.ICategoryRepository


class CategoryRepositoryImpl(private val dao: CategoryDao) : ICategoryRepository {

    override suspend fun insertCategory(category: Category): Long {
        return dao.insert(category)
    }

    override suspend fun updateCategory(category: Category) {
        dao.update(category)
    }

    override suspend fun deleteCategory(category: Category) {
        dao.delete(category)
    }

    override fun getCategories(): Flow<List<Category>> {
        return dao.getAll()
    }

    override fun getCountCategory(): Flow<Int> {
        return dao.getCount()
    }

    override suspend fun getCategoryById(categoryId: Long): Category? {
        return getCategoryById(categoryId)
    }

}