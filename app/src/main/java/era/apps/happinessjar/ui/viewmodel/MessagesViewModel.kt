package era.apps.happinessjar.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import era.apps.happinessjar.data.models.AppMessage
import era.apps.happinessjar.data.repository.MessageRepository
import kotlinx.coroutines.launch


class MessagesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MessageRepository = MessageRepository(application)
    val allAppMessage: LiveData<List<AppMessage>>
    val allAppWhatsApp: LiveData<List<AppMessage>>
    val allLikesMessage: LiveData<List<AppMessage>>
    init {
        allAppMessage = repository.getAllAppMessage()
        allAppWhatsApp = repository.getAllAppWhatsApp()
        allLikesMessage = repository.getAllLikesMessage()
    }


    fun like(message: AppMessage)=viewModelScope.launch {
        message.status = if (message.status == 1) 0 else 1
        repository.updateMessage(message)
    }

    fun  insertToDataBase(message: AppMessage)=viewModelScope.launch {
        repository.insert(message)
    }






}
