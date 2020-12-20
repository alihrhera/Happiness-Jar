package era.apps.happinessjar.models.stories.data_base;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import era.apps.happinessjar.models.message.data_base.AppMessage;
import era.apps.happinessjar.models.message.data_base.MessageDao;

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
