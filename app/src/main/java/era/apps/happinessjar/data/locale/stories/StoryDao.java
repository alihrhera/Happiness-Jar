package era.apps.happinessjar.data.locale.stories;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import era.apps.happinessjar.data.models.Story;


@Dao
public interface StoryDao {
    @Insert   // insert message to database
    void insert(Story message);
    // get all app message from database witch type = message
    @Query("Select * from Story")
    LiveData<List<Story>>getAllStory();


    @Query("SELECT COUNT('id') FROM Story")
    int getCount();






}
