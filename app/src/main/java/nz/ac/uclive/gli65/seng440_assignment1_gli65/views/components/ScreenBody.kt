package nz.ac.uclive.gli65.seng440_assignment1_gli65.views.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entities.Event

@Composable
fun ScreenBody(
    events: List<Event>
) {
    LazyColumn {// 是compose 对 RecyclerView 的回应
        items(events) { event ->
            Card(event)
        }
    }
}