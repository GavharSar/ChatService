import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ServiseTest {
    private val chats = mutableMapOf<Int, Chat>(1 to Chat(messages = mutableListOf<Message>()), 2 to Chat(messages = mutableListOf()))

    @Before
    fun clearTest() { Servise.clear() }

    @Test
    fun addMessage() {
        Servise.addMessage(1, message = Message(1, "Hello"))
    }

    @Test(expected = ChatNotException::class)
    fun editMessageChatNot() {
        Servise.addMessage(1, message = Message(1, "Hello"))
        Servise.editMessage(3, message = Message(1, "Ok"))
    }

    @Test(expected = MessageNotException::class)
    fun editMessageMessageNot() {
        Servise.addMessage(1, message = Message(1, "Hello"))
        Servise.editMessage(1, message = Message(2, "Ok"))
    }

    @Test
    fun editMessage() {
        Servise.addMessage(1, message = Message(1, "By"))
        Servise.editMessage(1, message = Message(1, "Ok"))
    }

    @Test(expected = ChatNotException::class)
    fun deliteMessageChatNot() {
        Servise.addMessage(1, message = Message(1, "By"))
        Servise.deliteMessage(3,1)
    }

    @Test(expected = MessageNotException::class)
    fun deliteMessageMessageNot() {
        Servise.addMessage(1, message = Message(1, "By"))
        Servise.deliteMessage(1,2)
    }

    @Test(expected = MessageDeletedException::class)
    fun deliteMessageMessageDelite() {
        Servise.addMessage(1, message = Message(1, "By", deleted = true))
        Servise.deliteMessage(1,1)
    }

    @Test
    fun deliteMessage() {
        Servise.addMessage(1, message = Message(1, "By"))
        Servise.deliteMessage(1, 1)
    }


    @Test(expected = ChatNotException::class)
    fun deliteChatNot() {
        Servise.addMessage(1, message = Message(1, "Hello"))
        Servise.deliteChat(3)
    }

    @Test
    fun deliteChat() {
        Servise.addMessage(1, message = Message(1, "Hello"))
        Servise.deliteChat(1)
    }

    @Test
    fun getlistMessagesChat() {
        Servise.addMessage(1, message = Message(1, "By"))
        Servise.addMessage(1, message = Message(2, "Ok"))
        Servise.getlistMessagesChat(1)
    }

    @Test(expected = ChatNotException::class)
    fun getlistMessagesChatNotChat() {
        Servise.addMessage(1, message = Message(1, "By"))
        Servise.addMessage(1, message = Message(2, "Ok"))
        Servise.getlistMessagesChat(3)
    }

    @Test(expected = ChatNotException::class)
    fun getlistMessagesIdChatNot() {
        Servise.addMessage(1, message = Message(1, "By"))
        Servise.addMessage(1, message = Message(2, "Ok"))
        Servise.getlistMessagesId(3, 2)
    }

    @Test(expected = MessageNotException::class)
    fun getlistMessagesIdMessageNot() {
        Servise.addMessage(1, message = Message(1, "By"))
        Servise.addMessage(1, message = Message(2, "Ok"))
        Servise.getlistMessagesId(1, 3)
    }

    @Test
    fun getlistMessagesId() {
        Servise.addMessage(1, message = Message(1, "By"))
        Servise.addMessage(1, message = Message(2, "Ok"))
        Servise.getlistMessagesId(1, 2)
    }

    @Test(expected = ChatNotException::class)
    fun getlistMessagesCountChatNot() {
        Servise.addMessage(1, message = Message(1, "By"))
        Servise.addMessage(1, message = Message(2, "Ok"))
        Servise.getlistMessagesCount(3, 2)
    }

    @Test
    fun getlistMessagesCount() {
        Servise.addMessage(1, message = Message(1, "By", deleted = true))
        Servise.addMessage(1, message = Message(2, "Ok"))
        Servise.getlistMessagesCount(1, 2)
    }
}