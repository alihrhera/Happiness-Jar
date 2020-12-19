package era.apps.happinessjar.models.message.data_base;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;


@Dao
public interface MessageDao {
    @Insert   // insert message to database
    void insert(AppMessage message);

    @Update   // update message
    void update(AppMessage message);

    @Delete    // delete single message
    void delete(AppMessage message);

    // get all app message from database witch type = message
    @Query("Select * from Message where type='message'")
    LiveData<List<AppMessage>>getAllAppMessage();

    // get all app message from database witch type = whatsApp
    @Query("Select * from Message where type='WhatsApp'")
    LiveData<List<AppMessage>>getAllAppWhatsApp();

    // get all app message from database witch is liked by user
    @Query("Select * from Message where status=1")
    LiveData<List<AppMessage>>getAllLikesMessage();



}
