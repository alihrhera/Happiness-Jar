package era.apps.happinessjar.data.repository

import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import era.apps.happinessjar.data.locale.DataBaseHelper
import era.apps.happinessjar.data.locale.StoryDao
import era.apps.happinessjar.data.models.Story
import era.apps.happinessjar.data.networking.AppApi
import era.apps.happinessjar.util.callback.OnListLoad
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StoryRepository(application: Application) {
    private val dataBase = DataBaseHelper.getInstance(application)

    private var storyDao: StoryDao = dataBase.storyDao()
    var allStories: LiveData<List<Story>> = storyDao.allStory

    init {
        val t = Thread {
            if (storyDao.count == 0) {
                AppApi.getInstance().geStoriesList(object : OnListLoad {
                    override fun onLoad(list: List<Any>) {
                        for (s in list as List<Story>) {
                            GlobalScope.launch {
                                insert(s!!)
                            }
                        }
                        allStories = storyDao.allStory
                    }

                }
                )
            }
        }
        t.priority = 10
        t.start()
    }


//    fun getAllStories(): LiveData<List<Story>> {
//        return allStories
//    }


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(message: Story) {
        storyDao.insert(message)
    }


}