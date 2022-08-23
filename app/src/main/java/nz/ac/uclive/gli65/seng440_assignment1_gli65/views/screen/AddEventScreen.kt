package nz.ac.uclive.gli65.seng440_assignment1_gli65.views.screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.CategoryEvent
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.CategoryViewModel
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.event.EventEvent
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.event.EventViewModel
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.component.EventTextField
import nz.ac.uclive.gli65.seng440_assignment1_gli65.views.component.titleNotification
import java.time.LocalDateTime

@ExperimentalAnimationApi
@Composable
fun AddEventScreen(
    navController: NavController,
    eventColor: Int,
    categoryId: Long,
    eventViewModel: EventViewModel = hiltViewModel(),
    categoryViewModel: CategoryViewModel = hiltViewModel<CategoryViewModel>(),
) {
    val eventState = eventViewModel.state.value
    val eventTitleState = eventViewModel.eventTitle.value
    val eventDescriptionState = eventViewModel.eventDescription.value
    val context = LocalContext.current


    val backGroundAnimation = remember {
        Animatable(
            Color(if (eventColor != -1) eventColor else eventState.selectedColor)
        )
    }

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    // todo fix and refactor
    LaunchedEffect(key1 = true) {
        eventViewModel.eventFlow.collectLatest { event ->
            when (event) {
                is EventViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is EventViewModel.UiEvent.SaveEvent -> {
                    navController.navigateUp()

                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {

                val title = eventTitleState.text

                if (!titleNotification(context, title)) {
                    eventViewModel.onEvent(
                        EventEvent.AddEvent(
                            LocalDateTime.now().toString(),
                            categoryId
                        )
                    )
                    categoryViewModel.onEvent(CategoryEvent.UpdateEventCount)
                }


            }) {
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = null,
                    tint = Color.White
                )

            }
        },
        scaffoldState = scaffoldState
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backGroundAnimation.value)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                eventState.colors.forEach { color ->
                    val colorInt = color.toArgb()
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .shadow(15.dp, RoundedCornerShape(10.dp))
                            .clip(RoundedCornerShape(10.dp))
                            .background(color)
                            .border(
                                width = 3.dp,
                                color = if (eventState.selectedColor == colorInt) {
                                    Color.Black
                                } else Color.Transparent,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .clickable {
                                scope.launch {
                                    backGroundAnimation.animateTo(
                                        targetValue = Color(colorInt),
                                        animationSpec = tween(
                                            durationMillis = 100
                                        )
                                    )
                                }
                                eventViewModel.onEvent(EventEvent.ChangeColor(colorInt))
                            }
                    ) // todo could be change
                }
            }


            EventTextField(
                text = eventTitleState.text,
                hint = eventTitleState.hint,
                onValueChange = {
                    eventViewModel.onEvent(EventEvent.EnteredTitle(it))
                },
                onFocusChange = {
                    eventViewModel.onEvent(EventEvent.ChangeTitleFocus(it))
                },
                isHintVisible = eventTitleState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.h5
            )

            Spacer(modifier = Modifier.height(16.dp))

            EventTextField(
                text = eventDescriptionState.text,
                hint = eventDescriptionState.hint,
                onValueChange = {
                    eventViewModel.onEvent(EventEvent.EnteredDescription(it))
                },
                onFocusChange = {
                    eventViewModel.onEvent(EventEvent.ChangeDescriptionFocus(it))
                },
                isHintVisible = eventDescriptionState.isHintVisible,
                singleLine = false,
                textStyle = MaterialTheme.typography.body1,
                modifier = Modifier.fillMaxHeight()
            )


        }

    }


}