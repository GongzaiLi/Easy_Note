package nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repository.impl

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.dao.EventDao
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Event
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repository.IEventRepository

class EventRepositoryImpl(
    private val dao: EventDao
) : IEventRepository {
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insert(event: Event): Long {
        return dao.insert(event)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun update(event: Event) {
        dao.update(event)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun delete(event: Event) {
        dao.delete(event)
    }

    override fun findAll(): Flow<List<Event>> {
        return dao.getAll()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun findById(id: Long): Event? {
        return dao.findById(id)
    }

    override fun getCount(): Flow<Int> {
        return dao.getCount()
    }

    override fun getEventByCategoryId(categoryId: Long): Flow<List<Event>> {
        return dao.findByCategoryId(categoryId)
    }

    override fun getCountEventByCategoryId(categoryId: Long): Flow<Int> {
        return dao.getCountByCategoryId(categoryId)
    }

}