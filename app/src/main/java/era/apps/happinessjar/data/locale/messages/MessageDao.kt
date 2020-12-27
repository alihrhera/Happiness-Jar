package era.apps.happinessjar.data.locale.messages


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

import era.apps.happinessjar.data.models.AppMessage


@Dao
interface MessageDao {
    @Insert   // insert message to database
    fun insert( message:AppMessage)

    @Update   // update message
    fun update( message:AppMessage)

    @Delete    // delete single message
    fun delete( message:AppMessage)

    // get all app message from database witch type = message
    @Query("Select * from AppMessage where type='message'")
    fun getAllAppMessage():LiveData<List<AppMessage>>

    // get all app message from database witch type = whatsApp
    @Query("Select * from AppMessage where type='WhatsApp'")
    fun getAllAppWhatsApp() :LiveData<List<AppMessage>>

    // get all app message from database witch is liked by user
    @Query("Select * from AppMessage where status=1")
    fun getAllLikesMessage():LiveData<List<AppMessage>>


}
