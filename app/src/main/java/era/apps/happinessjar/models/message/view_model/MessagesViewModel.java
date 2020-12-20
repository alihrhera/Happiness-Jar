package era.apps.happinessjar.models.message.view_model;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import era.apps.happinessjar.models.message.data_base.AppMessage;

public class MessagesViewModel extends AndroidViewModel {
    private MessageRepository repository;
    private LiveData<List<AppMessage>> allAppMessage;
    private LiveData<List<AppMessage>> allAppWhatsApp;
    private LiveData<List<AppMessage>> allLikesMessage;


    public MessagesViewModel(@NonNull Application application) {
        super(application);
        repository = new MessageRepository(application);
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
        message.setStatus(message.getStatus()==1?0:1);
        repository.updateMessage(message);

    }


}
