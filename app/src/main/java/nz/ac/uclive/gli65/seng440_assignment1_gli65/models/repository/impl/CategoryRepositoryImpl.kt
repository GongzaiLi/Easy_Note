package nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repository.impl

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.dao.CategoryDao
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Category
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repository.ICategoryRepository


class CategoryRepositoryImpl(private val dao: CategoryDao) : ICategoryRepository {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insertCategory(category: Category): Long {
        return dao.insert(category)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun deleteCategory(category: Category) {
        dao.delete(category)
    }

    override fun getCategories(): Flow<List<Category>> {
        return dao.getAll()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override fun getCategoryById(categoryId: Long): Flow<Category>? {
        return getCategoryById(categoryId)
    }

}