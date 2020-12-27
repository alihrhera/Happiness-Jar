package era.apps.happinessjar.data.locale.stories


import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import era.apps.happinessjar.data.locale.messages.MessageDao

import era.apps.happinessjar.data.models.Story

@Database(entities = arrayOf(Story::class), version = 1)
abstract class StoryDataBaseHelper : RoomDatabase() {

    abstract fun storyDao(): StoryDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: StoryDataBaseHelper? = null

        fun getInstance(context: Context): StoryDataBaseHelper {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        StoryDataBaseHelper::class.java,
                        "Happiness"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }


}


