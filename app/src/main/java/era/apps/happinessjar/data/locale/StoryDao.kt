package era.apps.happinessjar.data.locale


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import era.apps.happinessjar.data.models.Story

@Dao
interface StoryDao {
    @Insert
    fun  // insert message to database
            insert(message: Story)

    // get all app message from database witch type = message
    @get:Query("Select * from Story")
    val allStory: LiveData<List<Story>>

    @get:Query("SELECT COUNT('id') FROM Story")
    val count: Int
}
