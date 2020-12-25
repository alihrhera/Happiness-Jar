package era.apps.happinessjar.data.repository;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import era.apps.happinessjar.data.locale.messages.MessageDao;
import era.apps.happinessjar.data.models.AppMessage;
import era.apps.happinessjar.data.locale.messages.MessageDataBaseHelper;

public class MessageRepository {
    /*
    * MessageRepository.class will control all opp of data base
    * here will check if data base are updated will show from room data base
    * else will get data from api and insert to room app
    *
    * */
    private MessageDao messageDao;
    private LiveData<List<AppMessage>> allAppMessage;
    private LiveData<List<AppMessage>> allAppWhatsApp;
    private LiveData<List<AppMessage>> allLikesMessage;

    public MessageRepository(Application application) {
        MessageDataBaseHelper dataBase = MessageDataBaseHelper.getInstance(application);
        messageDao = dataBase.messageDao();
        allAppMessage = messageDao.getAllAppMessage();
        allAppWhatsApp = messageDao.getAllAppWhatsApp();
        allLikesMessage = messageDao.getAllLikesMessage();

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



    public void insertToDataBase(AppMessage message) {
        new MessageOpp(messageDao).execute(message);
    }
    public void updateMessage(AppMessage appMessage) {
        new MessageOpp(messageDao,false).execute(appMessage);
    }


    private static class MessageOpp extends AsyncTask<AppMessage, Void, Void> {
        private MessageDao messageDao;
        private boolean delete;

        private MessageOpp(MessageDao dao, boolean delete) {
            this.delete = delete;
            messageDao = dao;
            insert = false;
        }

        private boolean insert = false;

        private MessageOpp(MessageDao dao) {
            insert = true;
            messageDao = dao;
        }

        @Override
        protected Void doInBackground(AppMessage... appMessages) {
            if (insert) {
                messageDao.insert(appMessages[0]);
                return null;
            }
            if (!delete) {
                messageDao.update(appMessages[0]);
                return null;
            }
            messageDao.delete(appMessages[0]);
            return null;


        }
    }


}
