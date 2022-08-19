package nz.ac.uclive.gli65.seng440_assignment1_gli65.models.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Event

@Dao
interface EventDao {

    @Insert
    suspend fun insert(event: Event): Long

    @Update
    suspend fun update(event: Event)

    @Delete
    suspend fun delete(event: Event)

    @Query("SELECT * FROM event")
    fun getAll(): Flow<List<Event>>

    @Query("SELECT COUNT(*) FROM event")
    fun getCount(): Flow<Int> // why nor suspend

    @Query("SELECT * FROM event WHERE id  = :id")
    suspend fun findById(id: Long): Flow<Event>?

    @Query("SELECT * FROM event WHERE categoryId  = :categoryId")
    fun findByCategoryId(categoryId: Long): Flow<List<Event>>


    @Query("SELECT COUNT(*) FROM event WHERE categoryId  = :categoryId")
    fun getCountByCategoryId(categoryId: Long): LiveData<Int> // why nor suspend
}