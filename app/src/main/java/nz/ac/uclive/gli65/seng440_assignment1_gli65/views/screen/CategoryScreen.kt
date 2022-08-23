package nz.ac.uclive.gli65.seng440_assignment1_gli65.views.screen

import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import nz.ac.uclive.gli65.seng440_assignment1_gli65.R
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Category
import nz.ac.uclive.gli65.seng440_assignment1_gli65.ui.theme.*
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.CategoryEvent
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.CategoryViewModel
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.component.ScreenTopBarRow
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.component.getIconFromDrawable
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.component.titleNotification

@ExperimentalAnimationApi
@Composable
fun CategoryScreen(
    navController: NavController,
    categoryViewModel: CategoryViewModel = hiltViewModel()
) {
    CategoryScreenScaffold(navController, categoryViewModel)
}

@Composable
fun CategoryScreenScaffold(navController: NavController, categoryViewModel: CategoryViewModel) {

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
            AddManagerCategory(categoryViewModel)
        },
    ) {
        CategoryScreenBody(categoryViewModel)
    }
}

@Composable
fun CategoryScreenBody(categoryViewModel: CategoryViewModel) {

    val categoryState = categoryViewModel.state.value

    LazyColumn {
        items(categoryState.categories) { category ->
            val color: Color = LightBlue  //
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
                    tint = Nevada,
                    modifier = Modifier.size(22.dp)
                )
                Spacer(modifier = Modifier.width(40.dp))
                Text(
                    text = category.title,
                    style = TextStyle(fontSize = 22.sp),
                    color = Nevada,
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
    val context = LocalContext.current
    val showingDialog = remember { mutableStateOf(false) }
    val deleteMessage = stringResource(id = R.string.delete_toast_message)
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
                        Toast.makeText(
                            context,
                            deleteMessage,
                            Toast.LENGTH_SHORT
                        ).show()
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
                    Text(stringResource(id = R.string.cancel_button), color = Nevada)
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
            tint = DarkGray,
        )
    }
}

@Composable
fun AddManagerCategory(categoryViewModel: CategoryViewModel) {

    val showingDialog = remember { mutableStateOf(false) }
    val categoryState = categoryViewModel.state.value

    val context = LocalContext.current


    if (showingDialog.value) {
        AlertDialog(
            onDismissRequest = {
                showingDialog.value = false
            },
            title = {
                Text(
                    text = stringResource(id = R.string.add_category),
                    style = TextStyle(fontSize = 22.sp),
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 8.dp, bottom = 10.dp)
                )
            },
            text = {
                AddCategoryScreenBody(categoryViewModel)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        val title = categoryState.categoryTitle
                        if (!titleNotification(context, title)) {
                            showingDialog.value = false
                            categoryViewModel.onEvent(
                                CategoryEvent.AddCategory(
                                    Category(
                                        title = categoryState.categoryTitle,
                                        icon = categoryState.selectedIcon
                                    )
                                )
                            )
                        }
                    },
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Text(stringResource(id = R.string.save_button), color = Color.Green)
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
                    Text(stringResource(id = R.string.cancel_button), color = Nevada)
                }
            })
    }
    FloatingActionButton(onClick = {
        showingDialog.value = true


        //navController.navigate(Screen.AddCategoryScreen.route)
    }) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null,
            tint = Color.White
        )
    }


}


@Composable
fun AddCategoryScreenBody(categoryViewModel: CategoryViewModel) {

    val categoryState = categoryViewModel.state.value

    val isClick = remember { mutableStateOf(true) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        IconPicker(categoryViewModel)

        OutlinedTextField(
            textStyle = TextStyle(color = Nevada),
            value = categoryState.categoryTitle,
            label = {
                if (isClick.value) {
                    Text(
                        text = "Enter Category Title",
                        color = Nevada
                    )
                }
            },
            onValueChange = {
                isClick.value = it.isBlank()
                categoryViewModel.onEvent(CategoryEvent.UpdateCategoryTitle(it))
            },
            maxLines = 10,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            ),
            singleLine = true
        )

        //Spacer(modifier = Modifier.width(40.dp))
    }


}

@Composable
fun IconPicker(categoryViewModel: CategoryViewModel) {


    val isClick = rememberSaveable { mutableStateOf(false) }


    val categoryState = categoryViewModel.state.value


    Column(
    ) {
        if (!isClick.value) {
            IconButton(onClick = { isClick.value = !isClick.value }) {
                Icon(
                    painter = painterResource(id = getIconFromDrawable(categoryState.selectedIcon)),
                    contentDescription = null,
                    tint = Nevada,
                    modifier = Modifier.size(22.dp)
                )
            }
        } else {
            LazyRow {
                items(categoryState.categoryIcons) {
                    IconButton(onClick = {
                        isClick.value = !isClick.value
                        categoryViewModel.onEvent(CategoryEvent.PickIcon(it))
                    }) {
                        Icon(
                            painter = painterResource(id = getIconFromDrawable(it)),
                            contentDescription = null,
                            tint = Nevada,
                            modifier = Modifier.size(22.dp)
                        )
                    }

                }
            }

        }
    }
}




