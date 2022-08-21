package nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Event


interface IEventRepository {

    suspend fun insert(event: Event): Long

    suspend fun update(event: Event)

    suspend fun delete(event: Event)

    fun findAll(): Flow<List<Event>>

    suspend fun findById(id: Long): Event?

    fun getCount(): Flow<Int>

    fun getEventByCategoryId(categoryId: Long): Flow<List<Event>>

    fun getCountEventByCategoryId(categoryId: Long): Flow<Int>

}