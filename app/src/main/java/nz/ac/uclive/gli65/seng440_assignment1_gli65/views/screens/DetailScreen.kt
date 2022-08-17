package nz.ac.uclive.gli65.seng440_assignment1_gli65.views.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import nz.ac.uclive.gli65.seng440_assignment1_gli65.R

@Composable
fun DetailScreen(name: String?) {

    Box(modifier = Modifier.fillMaxSize()) {
        Text(text =  stringResource(R.string.hello, name?:""))
    }  // contentAlignment = Alignment.Center

}
