package era.apps.happinessjar.models.stories.view_model;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import era.apps.happinessjar.models.stories.data_base.StoryDataBaseHelper;
import era.apps.happinessjar.models.stories.data_base.StoryDao;
import era.apps.happinessjar.models.stories.data_base.Story;

public class StoryRepository {
    /*
     * */
    private final StoryDao storyDao;
    private LiveData<List<Story>> allStories;

    public StoryRepository(Application application) {
        StoryDataBaseHelper storyData = StoryDataBaseHelper.getInstance(application);
        storyDao = storyData.storyDao();
        allStories = storyDao.getAllStory();
        Thread t = new Thread(() -> {

            if (storyDao.getCount() == 0) {

                AppApi.getInstance().geStoriesList(list -> {
                            for (Story s : ((List<Story>) list)) {
                                Insert(s);
                            }
                            allStories = storyDao.getAllStory();
                        }
                );
            }
        });
        t.setPriority(10);
        t.start();

    }


    public LiveData<List<Story>> getAllStories() {
        return allStories;
    }

    private void Insert(Story story) {
        new StoryOpp(storyDao).execute(story);
    }


    private static class StoryOpp extends AsyncTask<Story, Void, Void> {
        private final StoryDao s;

        private StoryOpp(StoryDao dao) {
            s = dao;
        }

        @Override
        protected Void doInBackground(Story... appMessages) {
            s.insert(appMessages[0]);
            return null;
        }
    }


}
