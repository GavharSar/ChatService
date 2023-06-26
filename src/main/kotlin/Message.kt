data class Message(
    val idMessage: Int,
    val text: String,
    var read: Boolean = false,
    val incoming: Boolean = true,
    var deleted: Boolean = false
)