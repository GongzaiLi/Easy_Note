package nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.daos.CategoryDao
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entities.Category

class CategoryRepository(
    private val categoryDao: CategoryDao
) : ICategoryRepository {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insertCategory(category: Category): Long {
        return categoryDao.insert(category)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun updateCategory(category: Category) {
        return categoryDao.update(category)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun deleteCategory(category: Category) {
        return categoryDao.delete(category)
    }

    override fun getCategories(): LiveData<List<Category>> {
        return categoryDao.getAll()
    }

    override fun getCountCategory(): LiveData<Int> {
        return categoryDao.getCount()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun getCategoryById(categoryId: Long): Category? {
        return categoryDao.findById(categoryId)
    }
}