package nz.ac.uclive.gli65.seng440_assignment1_gli65.views.screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import nz.ac.uclive.gli65.seng440_assignment1_gli65.R
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.entity.Category
import nz.ac.uclive.gli65.seng440_assignment1_gli65.ui.theme.Nevada
import nz.ac.uclive.gli65.seng440_assignment1_gli65.ui.theme.TextWhite
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.CategoryEvent
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.CategoryViewModel
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.component.ScreenTopBarRow
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.component.getIconFromDrawable
// todo Ui
@ExperimentalAnimationApi
@Composable
fun AddCategoryScreen(
    navController: NavHostController,
    categoryViewModel: CategoryViewModel = hiltViewModel()
) {
    AddCategoryScreenScaffold(navController = navController, categoryViewModel = categoryViewModel)
}

@Composable
fun AddCategoryScreenScaffold(
    navController: NavHostController,
    categoryViewModel: CategoryViewModel
) {
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
    ) {
        AddCategoryScreenBody(categoryViewModel, navController)
    }

}

@Composable
fun AddCategoryScreenBody(categoryViewModel: CategoryViewModel, navController: NavHostController) {
    // todo ui

    val categoryState = categoryViewModel.state.value


    Column {


        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Nevada)
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                textStyle = TextStyle(color = Color.Red),
                value = categoryState.categoryTitle,
                label = { Text(text = "Enter Category Title") }, // todo
                onValueChange = {
                    categoryViewModel.onEvent(CategoryEvent.UpdateCategoryTitle(it))
                },
                maxLines = 10,
            )

            IconPicker(categoryViewModel)
            //Spacer(modifier = Modifier.width(40.dp))


        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Nevada)
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .fillMaxWidth()
        ) {
            Button(onClick = {
                // todo fix ui and check title is null or not
                categoryViewModel.onEvent(
                    CategoryEvent.AddCategory(
                        Category(
                            title = categoryState.categoryTitle,
                            description = "some", // todo here
                            icon = categoryState.selectedIcon
                        )
                    )
                )
                navController.navigateUp()

                //Toast.makeText(applicationContext, "You clicked the Button.", Toast.LENGTH_LONG).show()
            }) {
                Text("Submit Request")
            }
        }
    }


}

@Composable
fun IconPicker(categoryViewModel: CategoryViewModel) {


    val isClick = rememberSaveable { mutableStateOf(false) }

    val categoryState = categoryViewModel.state.value

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Button(onClick = { isClick.value = !isClick.value }) {
            Icon(
                painter = painterResource(id = getIconFromDrawable(categoryState.selectedIcon)),
                contentDescription = null,
                tint = TextWhite,
                modifier = Modifier.size(22.dp)
            )
        }
        DropdownMenu(
            expanded = isClick.value,
            modifier = Modifier.fillMaxWidth(),
            onDismissRequest = {}) {
            categoryState.categoryIcons.forEach {
                DropdownMenuItem(onClick = {
                    isClick.value = !isClick.value
                    categoryViewModel.onEvent(CategoryEvent.PickIcon(it))
                }) {
                    Icon(
                        painter = painterResource(id = getIconFromDrawable(it)),
                        contentDescription = null,
                        tint = TextWhite,
                        modifier = Modifier.size(22.dp)
                    )

                }
            }
        }

    }


}