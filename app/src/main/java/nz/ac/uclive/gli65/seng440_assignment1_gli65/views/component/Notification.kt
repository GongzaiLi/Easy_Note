package nz.ac.uclive.gli65.seng440_assignment1_gli65.views.component

import android.content.Context
import android.provider.Settings.Global.getString
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import nz.ac.uclive.gli65.seng440_assignment1_gli65.R


fun titleNotification(context: Context, title: String) : Boolean{


    val isTitleNull = title.isBlank()

    val message: String =
        if (isTitleNull)
            context.resources.getString(R.string.null_title_notification)

        else
            String.format(context.resources.getString(R.string.save_toast_message), title)


    Toast.makeText(
        context,
        message,
        Toast.LENGTH_SHORT
    ).show()

    return isTitleNull
}