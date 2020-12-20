package era.apps.happinessjar.models.chat.model;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatMessageRepository {
    private boolean isUser = false;

    public ChatMessageRepository(String userId) {
        reference = FirebaseDatabase.getInstance().getReference("Chats/" + userId);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                isUser = snapshot.exists();
                if (isUser) {
                    List<ChatMessages> list = new ArrayList<>();
                    for (DataSnapshot s : snapshot.getChildren()) {
                        ChatMessages m = s.getValue(ChatMessages.class);
                        list.add(m);
                    }
                    listMutableLiveData.postValue(list);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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

    DatabaseReference reference;

    void sendMessage(Conversation conversation) {
        if (isUser) {
            //TODO UPDATE CHAT MESSAGE
            return;
        }
        // TODO ADD NEW CON
    }

    private MutableLiveData<List<ChatMessages>> listMutableLiveData = new MutableLiveData<>();

    public LiveData<List<ChatMessages>> getChatMessage() {

        return listMutableLiveData;
    }


}
