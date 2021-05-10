package era.apps.happinessjar.data.locale


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import era.apps.happinessjar.data.models.AppMessage
import era.apps.happinessjar.data.models.Story

/*
JAVA Code
@Database(entities = {AppMessage.class}, version = 1)
public abstract class MessageDataBaseHelper extends RoomDatabase {

public abstract MessageDao messageDao();

private static MessageDataBaseHelper instance;
public static synchronized MessageDataBaseHelper getInstance(Context context) {
    if (instance == null) {
        instance = Room.
                databaseBuilder(context.getApplicationContext(), MessageDataBaseHelper.class,
                        "Happiness")
                .fallbackToDestructiveMigration().build();
    }
    return instance;
}

}*/
@Database(entities = [AppMessage::class,Story::class], version = 1)
 abstract class DataBaseHelper : RoomDatabase() {

    abstract fun messageDao(): MessageDao
    abstract fun storyDao(): StoryDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: DataBaseHelper? = null

        fun getInstance(context: Context): DataBaseHelper {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        DataBaseHelper::class.java,
                        "Happiness").build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }


}
