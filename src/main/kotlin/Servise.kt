object Servise {
    private val chats = mutableMapOf<Int, Chat>()

    fun addMessage(userId: Int, message: Message) {
        chats.getOrPut(userId) { Chat() }.messages.add(message)
    }

    fun editMessage(userId: Int, message: Message) { // не получается передать message как аргумент в copy()
        chats.get(userId)?.messages?.find { it.idMessage == message.idMessage }?.copy() ?: "No message"
    }

    fun deliteMessage(userId: Int, idMessage: Int) {
        chats.get(userId)?.messages?.find { it.idMessage == idMessage && !it.deleted }?.deleted = true
    }

    fun deliteChat(userId: Int) {
        chats.remove(userId)
    }

    fun getlistMessagesChat(userId: Int): List<Message>? {
        val chat = chats.get(userId)?.messages?.filterNotNull()
        chat?.forEach { it.deleted = true }
        return chat
    }

    fun getlistMessagesId(userId: Int, idMessage: Int): MutableList<Message>? { //не смогла понять,
        // как при использовании лямбды отдать список сообщений, начиная с сообщения,
        // айди которого передан в параметры?  как отметить сообщения прочитанными?
        chats.get(userId)?.messages?.subList(messages.indexOf<Any> { message ->
            message.idMessage == idMessage
        }, messages.lastIndex)
//        for (chat in chats) {
//            if (chat.idOwner == id) {
//                for (message in directMessages) {
//                    message.read = true
//                    if (message.idMessage == idMessage) {
//                        return directMessages.subList(directMessages.indexOf(message), directMessages.lastIndex)
//                    }
//                }
//            }
//        }
//        println("Чат с данным пользователем отсуствует")
//        return emptyList()
    }

    fun getlistMessagesCount(userId: Int, count: Int): List<String> { // не смогла найти метод, чтобы получить список сообщений
        // указав количество сообщений
        // как отметить сообщения прочитанными?
        chats.get(userId)?.messages?.dropWhile(messages.lastIndex - count) { message ->  !message.deleted }?.text ?: "No message"
    } // метод не работает






    fun getChats(): MutableCollection<Chat> {
        return chats.values
    }

    fun getUnreadChatsCount(): Int { // как сделать сообщения прочитаннами message.read = true
        chats.values.map { it.messages.count { message -> message.read == false }
        }
    }

        fun getlastMessagesChats(): List<String> =
            chats.values.map { it.messages.lastOrNull { message -> !message.deleted }?.text ?: "No message" }
    }


