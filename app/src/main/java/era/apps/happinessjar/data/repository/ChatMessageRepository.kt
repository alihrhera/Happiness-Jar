package era.apps.happinessjar.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.database.*
import era.apps.happinessjar.data.models.Conversation
import era.apps.happinessjar.data.networking.AppApi
import era.apps.happinessjar.util.DataManger
import era.apps.happinessjar.util.callback.OnMessageSent
import java.util.*

internal class ChatMessageRepository private constructor(userId: String) {
    private var isUser = false
    private val reference: DatabaseReference = FirebaseDatabase.getInstance().getReference("Conversations/$userId")
    private var onMessageSent: OnMessageSent? = null
    fun setOnMessageSent(onMessageSent: OnMessageSent?) {
        this.onMessageSent = onMessageSent
    }

    fun sendMessage(conversation: Conversation) {
        if (isUser) {
            //COMPLETED  (1)  UPDATE CHAT MESSAGE

            val map: MutableMap<String, Any> = HashMap()
            map["lastTimeSend"] = Calendar.getInstance().timeInMillis
            map["list"] = conversation.list
            reference.updateChildren(map).addOnCompleteListener { task: Task<Void?> ->
                if (task.isSuccessful) {
                    AppApi.getInstance().notifyUserThereIsNewMessage(DataManger.getInstance().adminKey, conversation.list[conversation.list.size - 1].messages, "ChatMessage")
                    if (onMessageSent != null) {
                        onMessageSent!!.onSent()
                    }
                }
            }
            return
        }
        reference.setValue(conversation).addOnCompleteListener {
            if (onMessageSent != null) {
                onMessageSent!!.onSent()
            }
        }
        // COMPLETED (2)  ADD NEW CON
    }

    fun updateMessage(conversation: Conversation) {

        val map=mapOf("list" to conversation.list)
        reference.updateChildren(map).addOnCompleteListener { task: Task<Void?> ->
            if (task.isSuccessful) {
                /*  AppApi.getInstance().notifyUserThereIsNewMessage(conversation.getFcmToken()
                          , conversation.getList().get(conversation.getList().size() - 1)
                                  .getMessages(), "ChatMessage");*/

                if (onMessageSent != null) {
                    onMessageSent!!.onSent()
                }
            }
        }
    }

    private val listMutableLiveData = MutableLiveData<Conversation?>()
    val chatMessage: LiveData<Conversation?>
        get() = listMutableLiveData

    companion object {
        private var messageRepository: ChatMessageRepository? = null
        @Synchronized
        fun getInstance(userId: String): ChatMessageRepository? {
            if (messageRepository == null) {
                messageRepository = ChatMessageRepository(userId)
            }
            return messageRepository
        }
    }

    init {
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                isUser = snapshot.exists()
                if (isUser) {
                    try {
                        val m = snapshot.getValue(Conversation::class.java)
                        listMutableLiveData.postValue(m)
                    } catch (ignored: Exception) {
                    }
                    return
                }
                listMutableLiveData.postValue(null)
            }

            override fun onCancelled(error: DatabaseError) {
                listMutableLiveData.postValue(null)
            }
        })
    }
}
