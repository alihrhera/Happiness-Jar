package era.apps.happinessjar.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.database.*
import era.apps.happinessjar.data.models.ChatMessages
import era.apps.happinessjar.data.models.Conversation
import era.apps.happinessjar.data.networking.AppApi
import era.apps.happinessjar.util.DataManger
import era.apps.happinessjar.util.callback.OnMessageSent
import java.util.*
import kotlin.collections.ArrayList

internal class ChatMessageRepository private constructor(userId: String) {
    private var isUser = false
    private val reference: DatabaseReference = FirebaseDatabase.getInstance().getReference("Conversations/$userId")

    fun sendMessage(conversation: Conversation) {
        if (isUser) {
            val map: MutableMap<String, Any> = HashMap()
            map["lastTimeSend"] = Calendar.getInstance().timeInMillis
            map["list"] = conversation.list
            reference.updateChildren(map).addOnCompleteListener { task: Task<Void?> ->
                if (task.isSuccessful) {
                    AppApi.getInstance().notifyUserThereIsNewMessage(DataManger.getInstance().adminKey, conversation.list[conversation.list.size - 1].messages, "ChatMessage")
                }
            }
            return
        }

        reference.updateChildren(mapOf("list" to conversation.list))
    }

    fun updateMessage(conversation: Conversation) {
        val map = mapOf("list" to conversation.list)
        reference.updateChildren(map)
    }

    private val listMutableLiveData = MutableLiveData<Conversation>()
    val chatMessage: LiveData<Conversation>
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
        val q = reference.child("list").limitToLast(DataManger.lastChatValue)
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                isUser = snapshot.exists()
                if (isUser) {
                    try {
                        val m = snapshot.getValue(Conversation::class.java)
                        reference.removeEventListener(this)
                        val q = reference.child("list").limitToLast(DataManger.lastChatValue)
                        q.addValueEventListener(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                if (snapshot.exists()) {
                                    val list: MutableList<ChatMessages> = ArrayList()
                                    for (s in snapshot.children) {
                                        val message = s.getValue(ChatMessages::class.java)
                                        list.add(message!!)
                                    }
                                    m?.list = list
                                    listMutableLiveData.postValue(m!!)

                                }


                            }

                            override fun onCancelled(error: DatabaseError) {

                            }
                        })
//                        listMutableLiveData.postValue(m!!)
                    } catch (ignored: Exception) {
                    }
                    return
                }
                listMutableLiveData.postValue(Conversation())
            }

            override fun onCancelled(error: DatabaseError) {
                listMutableLiveData.postValue(Conversation())
            }
        })
    }
}
