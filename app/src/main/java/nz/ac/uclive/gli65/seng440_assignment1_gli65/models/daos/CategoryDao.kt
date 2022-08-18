package nz.ac.uclive.gli65.seng440_assignment1_gli65.models.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entities.Category

@Dao
interface CategoryDao {

    @Insert
    suspend fun insert(category: Category): Long

    @Update
    suspend fun update(category: Category)

    @Delete
    suspend fun delete(category: Category)

    @Query("SELECT * FROM category")
    fun getAll(): LiveData<List<Category>>  // can be Flow


    @Query("SELECT COUNT(*) FROM category")
    fun getCount(): LiveData<Int>

    // todo not sure
    @Query("SELECT * FROM category WHERE id = :categoryId")
//    fun findById(categoryId: Long): LiveData<Category>
    suspend fun findById(categoryId: Long): Category?
}