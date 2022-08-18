package nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.time.OffsetDateTime

@Entity(tableName = "event")
data class Event(
    val title: String,
    val endData: String,
    val description: String,
    val categoryId: Long,
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    //val color: String, // labels
    // members
)