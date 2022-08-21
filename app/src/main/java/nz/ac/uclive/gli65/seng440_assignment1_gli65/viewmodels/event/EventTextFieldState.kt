package nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.event

data class EventTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true,
)