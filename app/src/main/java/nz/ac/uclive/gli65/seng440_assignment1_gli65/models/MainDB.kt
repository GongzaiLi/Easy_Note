package nz.ac.uclive.gli65.seng440_assignment1_gli65.models

import androidx.room.Database
import androidx.room.RoomDatabase
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.dao.CategoryDao
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Category

@Database(entities = [Category::class], version = 1)
abstract class MainDB : RoomDatabase() {

    abstract val categoryDao: CategoryDao

    companion object {
        const val DATABASE_NAME = "assignment1_db"
    }

}