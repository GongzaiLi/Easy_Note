package nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.daos.EventDao
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entities.Event

class EventRepository(
    private val eventDao: EventDao
) : IEventRepository {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insertEvent(event: Event): Long {
        return eventDao.insert(event)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun updateEvent(event: Event) {
        eventDao.update(event)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun deleteEvent(event: Event) {
        eventDao.delete(event)
    }

    override fun getEvents(): LiveData<List<Event>> {
        return eventDao.getAll()
    }

    override fun getCountEvent(): LiveData<Int> {
        return eventDao.getCount()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun getEventById(id: Long): Event? {
        return eventDao.findById(id)
    }

    override fun getEventByCategoryId(categoryId: Long): LiveData<List<Event>> {
        return eventDao.findByCategoryId(categoryId)
    }

    override fun getCountEventByCategoryId(categoryId: Long): LiveData<Int> {
        return eventDao.getCountByCategoryId(categoryId)
    }

}