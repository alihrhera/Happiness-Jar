package era.apps.happinessjar.data.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity(tableName = "Story") // room table name  for Message
public class Story {
    // create id  autoGenerate and PrimaryKey for the single message to catch it
    @JsonIgnoreProperties
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "Content")
    private  String msg;    // the content of the single Message

    private  String title;// the content of the single Message

    @ColumnInfo(name = "photoLink")
    private  String  url;


    public Story(String msg, String title, String url) {
        this.msg = msg;
        this.title = title;
        this.url = url;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return msg;
    }

    public String getTitle() {
        return title;
    }

    public String getPhotoLink() {
        return url;
    }

    public int getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }

    public String getUrl() {
        return url;
    }

    @Ignore
    public Story(){}
    @Ignore
    private  String  type,sent;

    public String getType() {
        return type;
    }

    public String getSent() {
        return sent;
    }
}
