package era.apps.happinessjar.util.database;


import android.animation.ObjectAnimator;
import android.view.View;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Message") // room table name  for Message
public   class AppMessage {
    // create id  autoGenerate and PrimaryKey for the single message to catch it
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String content;    // the content of the single Message
    private int status;        // the status of the single message is Liked or No
    private String type;       // we have 2 type of message whatsApp Message and the App Message

    public AppMessage(int status, String content, String type) {
        this.content = content;
        this.status = status;
        this.type = type;



           }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public int getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public boolean isLiked() {
        return status == 1;
    }
}
