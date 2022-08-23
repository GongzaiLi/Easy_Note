package nz.ac.uclive.gli65.seng440_assignment1_gli65.views.component


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nz.ac.uclive.gli65.seng440_assignment1_gli65.R
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Category
import nz.ac.uclive.gli65.seng440_assignment1_gli65.ui.theme.*

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
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val color = Color.White
            Icon(
                painter = painterResource(id = getIconFromDrawable("ic_header_24")),
                contentDescription = null,
                tint = color
            )
            Spacer(modifier = Modifier.width(40.dp))
            Text(
                text = stringResource(R.string.drawer_header),
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
    onClick: (Category) -> Unit,
    onFooterClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .background(LightBlue)
    ) {// 是compose 对 RecyclerView 的回应
        items(categories) { category ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onClick(category)
                    }
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = getIconFromDrawable(category.icon)),
                    contentDescription = category.description,
                    tint = Nevada
                )
                Spacer(modifier = Modifier.width(40.dp))
                Text(
                    text = category.title,
                    style = TextStyle(fontSize = 18.sp),
                    color = Nevada,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(0.4f)
                )
                Spacer(modifier = Modifier.weight(0.1f))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .width(40.dp)
                        .height(30.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(WhiteSmoke)
                        .weight(0.1f)

                ) {
                    Text(
                        text = category.eventNumber.toString(),
                        style = TextStyle(fontSize = 15.sp),
                        color = Nevada,
                        textAlign = TextAlign.Center,
                    )
                }
                Spacer(modifier = Modifier.weight(0.1f))

            }
        }

        item {
            DrawerFooter(onFooterClick)
        }
    }
}

@Composable
fun DrawerFooter(onClick: () -> Unit) {
    Divider(color = DeepGray, thickness = 1.dp)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = getIconFromDrawable("ic_category_edit_24")),
            contentDescription = null,
            tint = DarkGray
        )
        Spacer(modifier = Modifier.width(40.dp))
        Text(
            text = stringResource(R.string.category_edit),
            style = TextStyle(fontSize = 18.sp),
            color = DarkGray,
            modifier = Modifier
                .align(Alignment.CenterVertically)
        )
    }
    Divider(color = LightGray, thickness = 1.dp)
}