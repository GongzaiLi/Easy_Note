package nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "event")
class Event(
    @ColumnInfo var title: String,
    @ColumnInfo var endData: LocalDateTime,
    @ColumnInfo var description: String,
    @ColumnInfo var categoryId : Long,
    //val color: String, // labels
    // members
) {
    @PrimaryKey(autoGenerate = true) var id: Long = 0
    override fun toString() = title
}