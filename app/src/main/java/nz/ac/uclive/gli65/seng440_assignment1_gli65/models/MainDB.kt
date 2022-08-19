package nz.ac.uclive.gli65.seng440_assignment1_gli65.models

import androidx.room.Database
import androidx.room.RoomDatabase
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.dao.CategoryDao
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.dao.EventDao
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Category
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Event

@Database(entities = [Category::class, Event::class], version = 3)
abstract class MainDB : RoomDatabase() {

    abstract val categoryDao: CategoryDao
    abstract val eventDao: EventDao

    companion object {
        const val DATABASE_NAME = "assignment1_db"
    }

}