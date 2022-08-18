package nz.ac.uclive.gli65.seng440_assignment1_gli65.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.daos.CategoryDao
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.daos.EventDao
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entities.Category
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entities.Event

@Database(entities = [Event::class, Category::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun EventDao(): EventDao
    abstract fun CategoryDao(): CategoryDao

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
                    "Todo_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}