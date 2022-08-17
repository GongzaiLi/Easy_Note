package nz.ac.uclive.gli65.seng440_assignment1_gli65.views.components



import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entities.Category
import nz.ac.uclive.gli65.seng440_assignment1_gli65.ui.theme.BlueLight
import nz.ac.uclive.gli65.seng440_assignment1_gli65.ui.theme.Nevada
import nz.ac.uclive.gli65.seng440_assignment1_gli65.ui.theme.WhiteSmoke

/**
 * Drawer Header
 */
@Composable
fun DrawerHeader() {


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(BlueLight)
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val color = Color.White
            val name = "ic_header_24" // todo fix
            Icon(
                painter = painterResource(id = getIconFromDrawable(name)),
                contentDescription = null,
                tint = color
            )
            Spacer(modifier = Modifier.width(50.dp))
            Text(
                text = "Header", // todo R.string
                modifier = Modifier.weight(1f),
                style = TextStyle(fontSize = 20.sp),
                color = color
            )
        }
    }
}

/**
 * Drawer Body
 */
@Composable
fun DrawerBody(
    categories: List<Category>,
    onClick: (Category) -> Unit
) {
    LazyColumn {// 是compose 对 RecyclerView 的回应
        items(categories) { category ->
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick(category) }
                .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = getIconFromDrawable(category.icon)),
                    contentDescription = category.contentDescriptor,
                    tint = Nevada
                )
                Spacer(modifier = Modifier.width(50.dp))
                Text(
                    text = category.title,
                    style = TextStyle(fontSize = 18.sp),
                    color = Nevada
                )
                Spacer(modifier = Modifier.width(110.dp))

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.width(40.dp).height(30.dp).clip(RectangleShape).background(WhiteSmoke)

                ) {
                    Text(
                        text = "110", // todo set up max number count
                        style = TextStyle(fontSize = 18.sp),
                        color = Nevada,
                        textAlign = TextAlign.Center,
                    )
                }

            }
        }
    }
}

// todo add Drawer footer