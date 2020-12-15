package era.apps.happinessjar.util.database;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import era.apps.happinessjar.util.callback.MessageDao;
import era.apps.happinessjar.util.callback.StoryDao;

class StoryRepository {
    /*
     * */
    private StoryDao storyDao;
    private LiveData<List<Story>> allStories;

    public StoryRepository(Application application) {
        DataBaseHelper storyData = DataBaseHelper.getInstance(application);
        storyDao = storyData.storyDao();
        allStories = storyDao.getAllStory();


    }





    public LiveData<List<Story>> getAllStories() {
        return allStories;
    }

    private void Insert(Story story) {
        new StoryOpp(storyDao).execute(story);
    }


    private static class StoryOpp extends AsyncTask<Story, Void, Void> {
        private StoryDao messageDao;

        private StoryOpp(StoryDao dao) {
            messageDao = dao;
        }

        @Override
        protected Void doInBackground(Story... appMessages) {
            messageDao.insert(appMessages[0]);
            return null;
        }
    }


}
