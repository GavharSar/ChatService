object Servise {
    private val chats = mutableMapOf<Int, Chat>()

    fun addMessage(userId: Int, message: Message) {
        chats.getOrPut(userId) { Chat() }.messages.add(message)
    }

    fun editMessage(userId: Int, message: Message) {
        if (!chats.contains(userId)) throw ChatNotException("No chat")
        chats[userId]?.messages?.find { it.idMessage == message.idMessage }?.copy() ?: throw MessageNotException("No message")
    }

    fun deliteMessage(userId: Int, idMessage: Int) {
        if (!chats.contains(userId)) {
            throw ChatNotException("No chat")
        } else if ((chats[userId]?.messages?.find { it.idMessage == idMessage }) == null) {
            throw MessageNotException("No message")
        } else if ((chats[userId]?.messages?.find { it.idMessage == idMessage && !it.deleted }) == null) {
            throw MessageDeletedException("You are trying to delete a deleted message")
        } else {
            chats[userId]?.messages?.find { it.idMessage == idMessage && !it.deleted }?.deleted = true
        }
    }

    fun deliteChat(userId: Int) {
        if (!chats.contains(userId)) throw ChatNotException("No chat")
        chats.remove(userId)
    }

    fun getlistMessagesChat(userId: Int): List<Message> =
        if (!chats.contains(userId)) {
            throw ChatNotException("No chat")
        } else {
            chats[userId]?.messages.orEmpty()
                .onEach { it.deleted = true }
        }

    fun getlistMessagesId(userId: Int, idMessage: Int): List<Message> =
        if (!chats.contains(userId)) {9
            throw ChatNotException("No chat")
        } else if ((chats[userId]?.messages?.find { it.idMessage == idMessage }) == null) {
            throw MessageNotException("There is no message you want to start reading messages from")
        } else {
            chats[userId]?.messages.orEmpty()
                .dropWhile { it.idMessage != idMessage }
                .onEach { it.read = true }
        }

    fun getlistMessagesCount(userId: Int, count: Int): List<String> =
        if (!chats.contains(userId)) {
            throw ChatNotException("No chat")
        } else {
            chats[userId]?.messages.orEmpty()
                .filterNot { it.deleted }
                .take(count)
                .onEach { it.read = true }
                .map { it.text }
        }

    fun getChats(): MutableCollection<Chat> = chats.values

    fun getUnreadChatsCount(): Int =
         chats.values.flatMap { it.messages }
            .count { !it.read }

    fun getlastMessagesChats(): List<String> =
        chats.values.map { it.messages.lastOrNull { message -> !message.deleted }?.text ?: "No message" }

    fun clear() {
        chats.clear()
    }
}


