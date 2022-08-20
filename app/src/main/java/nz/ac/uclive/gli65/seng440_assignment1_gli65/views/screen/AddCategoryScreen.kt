package nz.ac.uclive.gli65.seng440_assignment1_gli65.views.screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import nz.ac.uclive.gli65.seng440_assignment1_gli65.R
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Category
import nz.ac.uclive.gli65.seng440_assignment1_gli65.ui.theme.LightRed
import nz.ac.uclive.gli65.seng440_assignment1_gli65.ui.theme.TextWhite
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.CategoryEvent
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.CategoryViewModel
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.component.ScreenTopBarRow
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.component.getIconFromDrawable

@ExperimentalAnimationApi
@Composable
fun AddCategoryScreen(
    navController: NavController,
    categoryViewModel: CategoryViewModel = hiltViewModel()
) {
    AddCategoryScreenScaffold(navController, categoryViewModel)
}

@Composable
fun AddCategoryScreenScaffold(navController: NavController, categoryViewModel: CategoryViewModel) {

    Scaffold(
        topBar = {
            ScreenTopBarRow(
                "ic_arrow_back_24",
                stringResource(R.string.category_edit),
                onClick = {
//                    navController.popBackStack() // cannot go back
//                    navController.navigate(Screen.HomeScreen.route)
                    navController.navigateUp()
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = {/* todo to create todo */ }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add", // todo description
                    tint = Color.White
                )
            }
        },
    ) {
        AddCategoryScreenBody(categoryViewModel)
    }
}

@Composable
fun AddCategoryScreenBody(categoryViewModel: CategoryViewModel) {

    val categoryState = categoryViewModel.state.value

    LazyColumn {
        items(categoryState.categories) { category ->
            val color: Color = LightRed  //
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(color)
                    .padding(horizontal = 10.dp, vertical = 10.dp)
                    .fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = getIconFromDrawable(category.icon)),
                    contentDescription = null,
                    tint = TextWhite,
                    modifier = Modifier.size(22.dp)
                )
                Spacer(modifier = Modifier.width(40.dp))
                Text(
                    text = category.title,
                    style = TextStyle(fontSize = 22.sp),
                    color = TextWhite,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(0.8f)
                )
                if (category.title != "All" && category.id != 1L) {
                    DeleteCategoryAlertDialog(categoryViewModel, category)
                }

            }
        }
    }
}

@Composable
fun DeleteCategoryAlertDialog(categoryViewModel: CategoryViewModel, category: Category) {
    val showingDialog = remember { mutableStateOf(false) }
    if (showingDialog.value) {
        AlertDialog(
            onDismissRequest = {
                showingDialog.value = false
            },
            title = {
                Text(
                    text = stringResource(id = R.string.category_confirm_delete),
                    style = TextStyle(fontSize = 22.sp),
                    color = Color.Black,
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        categoryViewModel.onEvent(CategoryEvent.DeleteCategory(category))
                        showingDialog.value = false
                    },
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Text(stringResource(id = R.string.delete_button), color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showingDialog.value = false
                    },
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Text(stringResource(id = R.string.cancel_button), color = Color.Green)
                }
            }
        )
    }
    IconButton(
        onClick = { showingDialog.value = true },
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = null,
            tint = TextWhite,
        )
    }
}


