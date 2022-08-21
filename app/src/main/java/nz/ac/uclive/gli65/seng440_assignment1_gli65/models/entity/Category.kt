package nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val description: String?,
    val icon: String,
) {
    @Ignore
    var eventNumber: Int = 0
}
