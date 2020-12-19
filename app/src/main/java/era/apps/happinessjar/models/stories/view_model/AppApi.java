package era.apps.happinessjar.models.stories.view_model;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.List;

import era.apps.happinessjar.models.stories.callback.OnListLoad;
import era.apps.happinessjar.models.stories.data_base.Story;

public class AppApi {
    private static final AppApi obj = new AppApi();

    private AppApi() {
    }

    public static synchronized AppApi getInstance() {

        return obj;
    }

    public static final String URL="https://era-apps.com/";



    public void geStoriesList(OnListLoad onListLoad){
        AndroidNetworking.post(URL+"happiness_jar/getStories.php")
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsObjectList(Story.class, new ParsedRequestListener<List<Story>>() {
                    @Override
                    public void onResponse(List<Story> stories) {
                        // do anything with response
                        for (Story s : stories) {
                            Log.e("StoryImage",s.getPhotoLink());
                        }
                        onListLoad.onLoad(stories);
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.e("Error While",anError.getMessage().toString());
                    }
                });

    }





}
