package era.apps.happinessjar.util.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import era.apps.happinessjar.util.callback.MessageDao;
import era.apps.happinessjar.util.callback.StoryDao;

@Database(entities = {AppMessage.class}, version = 1)
abstract class DataBaseHelper extends RoomDatabase {

    public abstract MessageDao messageDao();

    private static DataBaseHelper messageInstance;
    public static synchronized DataBaseHelper getInstance(Context context) {
        if (messageInstance == null) {
            messageInstance = Room.
                    databaseBuilder(context.getApplicationContext()
                            , DataBaseHelper.class, "Message")
                    .fallbackToDestructiveMigration().build();
        }
        return messageInstance;
    }

    public abstract StoryDao storyDao();
    private static DataBaseHelper storyInstance;
    public static synchronized DataBaseHelper getStoryInstance(Context context) {
        if (storyInstance == null) {
            storyInstance = Room.
                    databaseBuilder(context.getApplicationContext()
                            , DataBaseHelper.class, "Message")
                    .fallbackToDestructiveMigration().build();
        }
        return storyInstance;
    }

}
