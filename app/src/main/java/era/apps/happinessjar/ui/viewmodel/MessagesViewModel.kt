package era.apps.happinessjar.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import era.apps.happinessjar.data.models.AppMessage
import era.apps.happinessjar.data.repository.MessageRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MessagesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MessageRepository = MessageRepository(application)
    val allAppMessage: LiveData<List<AppMessage>> = repository.getAllAppMessage()
    val allAppWhatsApp: LiveData<List<AppMessage>> = repository.getAllAppWhatsApp()
    var allLikesMessage: LiveData<List<AppMessage>> = repository.getAllLikesMessage()


    fun like(message: AppMessage) = viewModelScope.launch {
        GlobalScope.launch {
            repository.updateMessage(message)
        }
//        allLikesMessage = repository.getAllLikesMessage()

    }

    fun insertToDataBase(message: AppMessage) = viewModelScope.launch {
        GlobalScope.launch {
            repository.insert(message)
        }
    }

    fun nullDelete() = viewModelScope.launch {
        GlobalScope.launch {
            repository.nullDelete()
        }
//        allLikesMessage = repository.getAllLikesMessage()

    }

}
