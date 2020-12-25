package era.apps.happinessjar.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import era.apps.happinessjar.data.models.Story
import era.apps.happinessjar.data.repository.StoryRepository

class StoriesViewModel(application: Application) : AndroidViewModel(application) {
    val allStories: LiveData<List<Story>>

    init {
        val storyRepository = StoryRepository(application)
        allStories = storyRepository.allStories
    }
}