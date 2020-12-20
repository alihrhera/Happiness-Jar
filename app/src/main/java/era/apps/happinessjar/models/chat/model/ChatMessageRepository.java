package era.apps.happinessjar.models.chat.model;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ChatMessageRepository {
    private boolean isUser = false;

    private ChatMessageRepository(String userId) {
        reference = FirebaseDatabase.getInstance().getReference("Conversations/" + userId);
        Log.e("UserRefrance",reference.getKey());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                isUser = snapshot.exists();
                if (isUser) {
                    try {
                        Conversation m = snapshot.getValue(Conversation.class);
                        listMutableLiveData.postValue(m);
                    } catch (Exception e) {
                    }
                    return;
                }
                listMutableLiveData.postValue(null);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                listMutableLiveData.postValue(null);
            }
        });
    }

    private static ChatMessageRepository messageRepository;

    public static synchronized ChatMessageRepository getInstance(String userId) {
        if (messageRepository == null) {
            messageRepository = new ChatMessageRepository(userId);
        }
        return messageRepository;
    }

    private final DatabaseReference reference;
    private OnMessageSent onMessageSent;

    public void setOnMessageSent(OnMessageSent onMessageSent) {
        this.onMessageSent = onMessageSent;
    }

    void sendMessage(Conversation conversation) {
        if (isUser) {
            //COMPLETED  (1)  UPDATE CHAT MESSAGE
            Map<String, Object> map = new HashMap<>();
            map.put("lastTimeSend", Calendar.getInstance().getTimeInMillis());
            map.put("list", conversation.list);
            reference.updateChildren(map).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    if (onMessageSent != null) {
                        onMessageSent.OnSent();
                    }
                }
            });
            return;
        }
        reference.setValue(conversation).addOnCompleteListener(task -> {
            if (onMessageSent != null) {
                onMessageSent.OnSent();
            }
        });
        // COMPLETED (2)  ADD NEW CON
    }

    private final MutableLiveData<Conversation> listMutableLiveData = new MutableLiveData<>();

    public LiveData<Conversation> getChatMessage() {
        return listMutableLiveData;
    }


}
