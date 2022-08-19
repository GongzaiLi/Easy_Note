package nz.ac.uclive.gli65.seng440_assignment1_gli65.models.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Category

@Dao
interface CategoryDao {

    @Insert
    suspend fun insert(category: Category): Long

    @Update
    suspend fun update(category: Category)

    @Delete
    suspend fun delete(category: Category)

    @Query("SELECT * FROM category")
    fun getAll(): Flow<List<Category>>  // can be live data


    @Query("SELECT COUNT(*) FROM category")
    fun getCount(): Flow<Int>

    // todo not sure
    @Query("SELECT * FROM category WHERE id = :categoryId")
//    fun findById(categoryId: Long): LiveData<Category>
    fun findById(categoryId: Long): Flow<Category>?
}