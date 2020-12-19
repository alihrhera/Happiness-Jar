package era.apps.happinessjar.models.message.data_base;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {AppMessage.class}, version = 1)
public abstract class MessageDataBaseHelper extends RoomDatabase {

    public abstract MessageDao messageDao();

    private static MessageDataBaseHelper instance;
    public static synchronized MessageDataBaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = Room.
                    databaseBuilder(context.getApplicationContext(), MessageDataBaseHelper.class, "Happiness")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }

}
