package nz.ac.uclive.gli65.seng440_assignment1_gli65.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import nz.ac.uclive.gli65.seng440_assignment1_gli65.R
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entities.Event
import nz.ac.uclive.gli65.seng440_assignment1_gli65.ui.theme.LightRed
import nz.ac.uclive.gli65.seng440_assignment1_gli65.ui.theme.Nevada
import nz.ac.uclive.gli65.seng440_assignment1_gli65.ui.theme.TextWhite
import java.time.LocalDateTime
import java.time.Month
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

@Composable
fun Card(event: Event) {
    val color: Color = LightRed  //
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(text = event.title, style = MaterialTheme.typography.h2)


            Row(verticalAlignment = Alignment.CenterVertically) {
                val name = "ic_time_24" // todo fix
                Icon(
                    painter = painterResource(id = getIconFromDrawable(name)),
                    contentDescription = null,
                    tint = TextWhite,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    text = getDateTime(getLocalDateTime(event.endData)),
                    style = MaterialTheme.typography.body1,
                    color = TextWhite
                ) //
            }
            // todo time icon

        }
        // todo set up color
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