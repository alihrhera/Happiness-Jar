package era.apps.happinessjar.models.chat.model;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Conversation {
    String id;
    String name;
    String fcmToken="";
    long lastTimeSend;


    @Exclude
    public Date getTimeToSort(){
        return new Date(lastTimeSend);
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }



    public long getLastTimeSend() {
        return lastTimeSend;
    }

    public void setLastTimeSend(long lastTimeSend) {
        this.lastTimeSend = lastTimeSend;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    List<ChatMessages>list=new ArrayList<>();
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ChatMessages> getList() {
        return list;
    }

    public void setList(List<ChatMessages> list) {
        this.list = list;
    }
}

