package nz.ac.uclive.gli65.seng440_assignment1_gli65.views.component

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import nz.ac.uclive.gli65.seng440_assignment1_gli65.R
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Event
import nz.ac.uclive.gli65.seng440_assignment1_gli65.ui.theme.DarkGray
import nz.ac.uclive.gli65.seng440_assignment1_gli65.ui.theme.LightRed
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.category.use_case.CategoryEvent
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.category.use_case.CategoryViewModel
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.event.EventEvent
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.event.EventViewModel
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.Screen
import java.time.LocalDateTime
import java.time.format.TextStyle
import java.util.*

@ExperimentalMaterialApi
@Composable
fun EventBody(
    events: List<Event>,
    navController: NavController,
    eventViewModel: EventViewModel,
    categoryViewModel: CategoryViewModel
) {
    LazyColumn {
        itemsIndexed(
            items = events, key = { _, listItem -> listItem.hashCode() }
        ) { _, event ->
            val state = rememberDismissState(
                confirmStateChange = {
                    if (it == DismissValue.DismissedToStart) {
                        eventViewModel.onEvent(EventEvent.DeleteEvent(event))
                        categoryViewModel.onEvent(CategoryEvent.UpdateEventCount)
                    }
                    true
                }
            )
            SwipeToDismiss(
                state = state,
                background = {
                    val color = when (state.dismissDirection) {
                        DismissDirection.StartToEnd -> Color.Transparent
                        DismissDirection.EndToStart -> LightRed
                        null -> Color.Transparent
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = color)
                            .padding(10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = Color.White,
                            modifier = Modifier.align(
                                Alignment.CenterEnd
                            )
                        )
                    }
                },
                dismissContent = {
                    Card(event, navController)
                },
                directions = setOf(DismissDirection.EndToStart)
            )
            Divider()

        }
    }

//    LazyColumn {// 是compose 对 RecyclerView 的回应
//        items(events) { event ->
//            Card(event, navController)
//        }
//    }
}

@Composable
fun Card(event: Event, navController: NavController) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(event.color))
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate(Screen.AddEventScreen.route + "?eventId=${event.id}&eventColor=${event.color}")
            }
    ) {
        Column {
            Text(text = event.title, style = MaterialTheme.typography.h2, color = DarkGray)


            Row(verticalAlignment = Alignment.CenterVertically) {
                val name = "ic_time_24" // todo fix
                Icon(
                    painter = painterResource(id = getIconFromDrawable(name)),
                    contentDescription = null,
                    tint = DarkGray,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    text = getDateTime(getLocalDateTime(event.timestamp)),
                    style = MaterialTheme.typography.body1,
                    color = DarkGray
                )
            }
        }
        ShareButton(event.title, event.description ?: "")

    }
}

@Composable
fun getDateTime(data: LocalDateTime): String {
    //"Aug 24, 2022 at 12:00 PM"
    val month: String = data.month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
    val day: String = formatNumber(data.dayOfMonth)
    val year: String = data.year.toString()
    val hour: String = formatNumber(data.hour)
    val min: String = formatNumber(data.minute)

    return stringResource(R.string.due_date, month, day, year, hour, min)
}

fun formatNumber(number: Int): String = if (number < 10) "0$number" else "$number"

fun getLocalDateTime(dateString: String): LocalDateTime {
    return LocalDateTime.parse(dateString)
}

fun toDateString(date: LocalDateTime): String {
    return date.toString()
}

@Composable
fun ShareButton(subject: String, text: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null) //title or some
    val context = LocalContext.current

    IconButton(
        onClick = {
            context.startActivity(shareIntent)
        },
    ) {
        Icon(
            imageVector = Icons.Default.Share,
            contentDescription = null,
            tint = DarkGray,
        )
    }

}