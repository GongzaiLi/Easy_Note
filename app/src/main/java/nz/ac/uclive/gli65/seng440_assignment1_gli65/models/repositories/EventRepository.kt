package nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.daos.EventDao
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entities.Event

class EventRepository(private val eventDao: EventDao) {

    val getAll: LiveData<List<Event>> = eventDao.getAll()

    val numEvent: LiveData<Int> = eventDao.getCount()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(event: Event) {
        eventDao.insert(event)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(event: Event) {
        eventDao.update(event)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(event: Event) {
        eventDao.delete(event)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun findById(eventId: Long): LiveData<Event> {
        return eventDao.findById(eventId)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun findByCategoryId(categoryId: Long): LiveData<List<Event>> {
        return eventDao.findByCategoryId(categoryId)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun getCountByCategoryId(categoryId: Long): LiveData<Int> {
        return eventDao.getCountByCategoryId(categoryId)
    }

}