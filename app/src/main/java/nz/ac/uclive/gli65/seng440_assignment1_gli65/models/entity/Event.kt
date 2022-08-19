package nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event")
data class Event(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val timestamp: String,
    val description: String?,
    val categoryId: Long?, // todo not sure
    val color: Int,
) {
    // todo color
}