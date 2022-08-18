package nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repositories

import androidx.lifecycle.LiveData
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entities.Event

interface IEventRepository {

    suspend fun insertEvent(event: Event): Long

    suspend fun updateEvent(event: Event)

    suspend fun deleteEvent(event: Event)

    fun getEvents(): LiveData<List<Event>>

    fun getCountEvent(): LiveData<Int>

    suspend fun getEventById(id: Long): Event?

    fun getEventByCategoryId(categoryId: Long): LiveData<List<Event>>

    fun getCountEventByCategoryId(categoryId: Long): LiveData<Int>
}