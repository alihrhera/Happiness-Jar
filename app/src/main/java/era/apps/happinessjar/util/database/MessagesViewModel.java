package era.apps.happinessjar.util.database;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MessagesViewModel extends AndroidViewModel {
    private MessageRepository repository;
    private LiveData<List<AppMessage>> allAppMessage;
    private LiveData<List<AppMessage>> allAppWhatsApp;
    private LiveData<List<AppMessage>> allLikesMessage;
    private LiveData<List<Story>> allStories;


    public MessagesViewModel(@NonNull Application application) {
        super(application);
        repository = new MessageRepository(application);
        StoryRepository storyRepository = new StoryRepository(application);

        allStories      = storyRepository.getAllStories();
        allAppMessage   = repository.getAllAppMessage();
        allAppWhatsApp  = repository.getAllAppWhatsApp();
        allLikesMessage = repository.getAllLikesMessage();
    }

    public LiveData<List<AppMessage>> getAllAppMessage() {
        return allAppMessage;
    }

    public LiveData<List<AppMessage>> getAllAppWhatsApp() {
        return allAppWhatsApp;
    }

    public LiveData<List<AppMessage>> getAllLikesMessage() {
        return allLikesMessage;
    }

    public void like(AppMessage message) {
        repository.updateMessage(message);
    }


    public LiveData<List<Story>> getAllStories() {
        return allStories;
    }
}
