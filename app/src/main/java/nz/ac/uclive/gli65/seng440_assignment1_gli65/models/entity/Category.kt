package nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val description: String?,
    val icon: String,
) {
    companion object {
        val categoryIcon = listOf<String>("ic_all_type_24", "ic_life_24", "ic_star_24")
    }
}
