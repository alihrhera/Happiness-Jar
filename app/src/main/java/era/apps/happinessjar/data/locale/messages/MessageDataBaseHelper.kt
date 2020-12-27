package era.apps.happinessjar.data.locale.messages


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import era.apps.happinessjar.data.models.AppMessage

/*
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
@Database(entities = arrayOf(AppMessage::class), version = 1)
 abstract class MessageDataBaseHelper : RoomDatabase() {

    abstract fun messageDao(): MessageDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: MessageDataBaseHelper? = null

        fun getInstance(context: Context): MessageDataBaseHelper {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        MessageDataBaseHelper::class.java,
                        "Happiness"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }


}
