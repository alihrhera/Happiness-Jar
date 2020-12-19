package era.apps.happinessjar.util;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import era.apps.happinessjar.util.database.Story;

public class DataManger {

    private static final DataManger obj = new DataManger();

    private DataManger() {
    }

    public static synchronized DataManger getInstance() {

        return obj;
    }


    public String getGooglePlayAppUrl(Context context) {
        return "https://play.google.com/store/apps/details?id=" +
                context.getPackageName();
    }





    private List<Story>storyList=new ArrayList();

    public List<Story> getStoryList() {
        return storyList;
    }

    public void setStoryList(List<Story> storyList) {
        this.storyList = storyList;
    }
}
