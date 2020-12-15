package era.apps.happinessjar.util.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "Story") // room table name  for Message
public class Story {
    // create id  autoGenerate and PrimaryKey for the single message to catch it
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String content;    // the content of the single Message
    private String title;// the content of the single Message
    private String photoLink;


    public Story(String content, String title, String photoLink) {
        this.content = content;
        this.title = title;
        this.photoLink = photoLink;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public int getId() {
        return id;
    }
}
