package era.apps.happinessjar.models.stories.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import era.apps.happinessjar.models.stories.data_base.Story;

public class StoriesViewModel extends AndroidViewModel {
    private LiveData<List<Story>> allStories;

    public StoriesViewModel(@NonNull Application application) {
        super(application);
        StoryRepository storyRepository = new StoryRepository(application);
        allStories      = storyRepository.getAllStories();
    }

    public LiveData<List<Story>> getAllStories() {
        return allStories;
    }




}
