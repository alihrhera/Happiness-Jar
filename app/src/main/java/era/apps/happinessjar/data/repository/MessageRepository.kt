package era.apps.happinessjar.data.repository;


import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import era.apps.happinessjar.data.locale.messages.MessageDao
import era.apps.happinessjar.data.locale.messages.MessageDataBaseHelper
import era.apps.happinessjar.data.models.AppMessage

public class MessageRepository (application: Application){
    /*
    * MessageRepository.class will control all opp of data base
    * here will check if data base are updated will show from room data base
    * else will get data from api and insert to room app
    *
    * */
    private val messageDao: MessageDao
    private val allAppMessage   : LiveData<kotlin.collections.List<AppMessage>>
    private val allAppWhatsApp  : LiveData<kotlin.collections.List<AppMessage>>
    private val allLikesMessage : LiveData<kotlin.collections.List<AppMessage>>

    init {
        val dataBase = MessageDataBaseHelper.getInstance(application!!)
        messageDao = dataBase.messageDao()
        allAppMessage = messageDao.getAllAppMessage()
        allAppWhatsApp = messageDao.getAllAppWhatsApp()
        allLikesMessage = messageDao.getAllLikesMessage()
    }

    


    fun getAllAppMessage(): LiveData<List<AppMessage>>{
        return allAppMessage
    }

    fun getAllAppWhatsApp(): LiveData<List<AppMessage>> {
        return allAppWhatsApp
    }

    fun getAllLikesMessage(): LiveData<List<AppMessage>> {
        return allLikesMessage
    }
    //fun insertToDataBase(message:AppMessage)







    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(message: AppMessage) {
        messageDao.insert(message)
    }


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateMessage(message: AppMessage) {
        messageDao.update(message)
    }




}
