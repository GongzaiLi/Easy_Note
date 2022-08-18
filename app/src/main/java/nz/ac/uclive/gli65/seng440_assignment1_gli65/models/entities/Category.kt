package nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category(
    @ColumnInfo var title: String,
    @ColumnInfo var descriptor: String,
    @ColumnInfo var icon: String,
) {
    @PrimaryKey(autoGenerate = true) var id: Long = 0
}