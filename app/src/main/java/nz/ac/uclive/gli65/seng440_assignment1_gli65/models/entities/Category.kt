package nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category(
    val title: String,
    val descriptor: String,
    val icon: String,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
)