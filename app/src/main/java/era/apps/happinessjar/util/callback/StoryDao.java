package era.apps.happinessjar.util.callback;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import era.apps.happinessjar.util.database.AppMessage;
import era.apps.happinessjar.util.database.Story;


@Dao
public interface StoryDao {
    @Insert   // insert message to database
    void insert(Story message);
    // get all app message from database witch type = message
    @Query("Select * from Story  ")
    LiveData<List<Story>>getAllStory();

}
