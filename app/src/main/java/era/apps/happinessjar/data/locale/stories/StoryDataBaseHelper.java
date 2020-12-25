package era.apps.happinessjar.data.locale.stories;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import era.apps.happinessjar.data.models.Story;

@Database(entities = {Story.class}, version = 1)
public abstract class StoryDataBaseHelper extends RoomDatabase {

    public abstract StoryDao storyDao();

    private static StoryDataBaseHelper instance;
    public static synchronized StoryDataBaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = Room.
                    databaseBuilder(context.getApplicationContext(), StoryDataBaseHelper.class, "Happiness")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }

}
