package era.apps.happinessjar.util.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import era.apps.happinessjar.util.callback.MessageDao;
import era.apps.happinessjar.util.callback.StoryDao;

@Database(entities = {AppMessage.class,Story.class}, version = 1)
abstract class DataBaseHelper extends RoomDatabase {

    public abstract MessageDao messageDao();
    public abstract StoryDao storyDao();

    private static DataBaseHelper instance;
    public static synchronized DataBaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = Room.
                    databaseBuilder(context.getApplicationContext(), DataBaseHelper.class, "Happiness")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }

   /* private static DataBaseHelper storyInstance;
    public static synchronized DataBaseHelper getStoryInstance(Context context) {
        if (storyInstance == null) {
            storyInstance = Room.
                    databaseBuilder(context.getApplicationContext()
                            , DataBaseHelper.class, "Message")
                    .fallbackToDestructiveMigration().build();
        }
        return storyInstance;
    }
    */
}
