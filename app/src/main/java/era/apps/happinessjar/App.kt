package era.apps.happinessjar

import android.app.Application
import com.androidnetworking.AndroidNetworking
import com.jacksonandroidnetworking.JacksonParserFactory


 class App :Application(){
    override fun onCreate() {
        super.onCreate()
        AndroidNetworking.initialize(applicationContext);
        AndroidNetworking.setParserFactory(JacksonParserFactory())

    }
}