package nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entities

import androidx.lifecycle.LiveData
import java.time.LocalDateTime

// todo thinking about already due
class Event(
    val id: Int,
    val title: String,
    val endData: LocalDateTime,
    val description: String,
    val categoryId : Int,
    //val color: String, // labels
    // members
) {
}