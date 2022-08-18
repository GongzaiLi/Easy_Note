package nz.ac.uclive.gli65.seng440_assignment1_gli65.models

import androidx.room.Database
import androidx.room.RoomDatabase
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.daos.CategoryDao
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.daos.EventDao
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entities.Category
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entities.Event

@Database(entities = [Category::class, Event::class], version = 3)
abstract class RoomDB : RoomDatabase() {
    abstract val categoryDao: CategoryDao
    abstract val eventDao: EventDao

    companion object {
        const val DATABASE_NAME = "event_db"
    }
    /*
     companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).createFromAsset("database/simple.db").build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
     */

}