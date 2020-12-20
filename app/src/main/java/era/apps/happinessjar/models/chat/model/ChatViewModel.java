package era.apps.happinessjar.models.chat.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import era.apps.happinessjar.models.message.data_base.AppMessage;

public class ChatViewModel extends AndroidViewModel {

    public ChatViewModel(@NonNull Application application) {
        super(application);
        ChatMessageRepository repository=new ChatMessageRepository("");

    }

    private void sendMsg(Conversation conversation) {

    }
    private OnMessageSent onMessageSent;
    private LiveData<List<ChatMessages>> allChatMessage;









}
