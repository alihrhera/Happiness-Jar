package era.apps.happinessjar.data.repository;



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

import era.apps.happinessjar.data.models.Conversation;
import era.apps.happinessjar.data.networking.AppApi;
import era.apps.happinessjar.util.DataManger;
import era.apps.happinessjar.util.callback.OnMessageSent;

public class _ChatMessageRepository {
    private boolean isUser = false;

    private _ChatMessageRepository(String userId) {
        reference = FirebaseDatabase.getInstance().getReference("Conversations/" + userId);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                isUser = snapshot.exists();
                if (isUser) {
                    try {
                        Conversation m = snapshot.getValue(Conversation.class);
                        listMutableLiveData.postValue(m);

                    } catch (Exception ignored) {
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

    private static _ChatMessageRepository messageRepository;

    public static synchronized _ChatMessageRepository getInstance(String userId) {
        if (messageRepository == null) {
            messageRepository = new _ChatMessageRepository(userId);
        }
        return messageRepository;
    }

    private final DatabaseReference reference;
    private OnMessageSent onMessageSent;

    public void setOnMessageSent(OnMessageSent onMessageSent) {
        this.onMessageSent = onMessageSent;
    }

    public void sendMessage(Conversation conversation) {
        if (isUser) {
            //COMPLETED  (1)  UPDATE CHAT MESSAGE
            Map<String, Object> map = new HashMap<>();
            map.put("lastTimeSend", Calendar.getInstance().getTimeInMillis());
            map.put("list", conversation.getList());
            reference.updateChildren(map).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    AppApi.getInstance().notifyUserThereIsNewMessage(DataManger.getInstance().getAdminKey()
                            , conversation.getList()
                                    .get(conversation.getList().size() - 1).getMessages(), "ChatMessage");
                    if (onMessageSent != null) {

                        onMessageSent.onSent();
                    }
                }
            });
            return;
        }
        reference.setValue(conversation).addOnCompleteListener(task -> {
            if (onMessageSent != null) {
                onMessageSent.onSent();
            }
        });
        // COMPLETED (2)  ADD NEW CON
    }


    public void updateMessage(Conversation conversation) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", conversation.getList());
        reference.updateChildren(map).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
              /*  AppApi.getInstance().notifyUserThereIsNewMessage(conversation.getFcmToken()
                        , conversation.getList().get(conversation.getList().size() - 1)
                                .getMessages(), "ChatMessage");*/
                if (onMessageSent != null) {
                    onMessageSent.onSent();
                }
            }
        });
    }

    private final MutableLiveData<Conversation> listMutableLiveData = new MutableLiveData<>();

    public LiveData<Conversation> getChatMessage() {
        return listMutableLiveData;
    }


}
