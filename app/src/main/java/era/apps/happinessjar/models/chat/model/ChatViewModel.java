package era.apps.happinessjar.models.chat.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;




public class ChatViewModel extends AndroidViewModel {
    private final ChatMessageRepository repository;

    public ChatViewModel(@NonNull Application application) {
        super(application);
        String id = application.getSharedPreferences("info", 0).getString("chatId", "");
        repository = ChatMessageRepository.getInstance(id);
        allChatMessage = repository.getChatMessage();
    }

    private final LiveData<Conversation> allChatMessage;

    public LiveData<Conversation> getAllChatMessage() {
        return allChatMessage;
    }

    public void setOnMessageSent(OnMessageSent onMessageSent) {
        repository.setOnMessageSent(onMessageSent);
    }

    public void sendMessage(Conversation conversation) {
        repository.sendMessage(conversation);
    }

}
