package nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.daos.CategoryDao
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entities.Category

class CategoryRepository(private val categoryDao: CategoryDao) {

    val getAll: LiveData<List<Category>> = categoryDao.getAll()

    val numCategory: LiveData<Int> = categoryDao.getCount()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(category: Category) {
        categoryDao.insert(category)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(category: Category) {
        categoryDao.update(category)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(category: Category) {
        categoryDao.delete(category)
    }

//    @Suppress("RedundantSuspendModifier")
//    @WorkerThread
//    fun findById(categoryId: Long): LiveData<Category> {
//        return categoryDao.findById(categoryId)
//    }

}