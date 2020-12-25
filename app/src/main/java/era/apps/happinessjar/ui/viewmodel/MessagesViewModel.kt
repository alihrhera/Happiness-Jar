package era.apps.happinessjar.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import era.apps.happinessjar.data.models.AppMessage
import era.apps.happinessjar.data.repository.MessageRepository


class MessagesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MessageRepository = MessageRepository(application)
    val allAppMessage: LiveData<List<AppMessage>>
    val allAppWhatsApp: LiveData<List<AppMessage>>
    val allLikesMessage: LiveData<List<AppMessage>>

    fun like(message: AppMessage) {
        message.status = if (message.status == 1) 0 else 1

        repository.updateMessage(message)
    }

    init {
        allAppMessage = repository.allAppMessage
        allAppWhatsApp = repository.allAppWhatsApp
        allLikesMessage = repository.allLikesMessage
    }
}
