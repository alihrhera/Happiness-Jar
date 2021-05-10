package era.apps.happinessjar.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import era.apps.happinessjar.data.models.Conversation
import era.apps.happinessjar.data.repository.ChatMessageRepository
import era.apps.happinessjar.util.callback.OnMessageSent


class ChatViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ChatMessageRepository
    private val allChatMessage: LiveData<Conversation>

    fun getAllChatMessage():LiveData<Conversation>{
        return allChatMessage
    }


    fun sendMessage(conversation: Conversation) {
        repository.sendMessage(conversation)
    }

    fun update(conversation: Conversation) {
        repository.updateMessage(conversation)
    }

    init {
        val id = application.getSharedPreferences("info", 0).getString("chatId", "")
        repository = ChatMessageRepository.getInstance(id!!)!!
        allChatMessage = repository.chatMessage
    }
}