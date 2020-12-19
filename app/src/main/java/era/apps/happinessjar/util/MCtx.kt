package era.apps.happinessjar.util

import android.app.Application
import com.androidnetworking.AndroidNetworking
import com.jacksonandroidnetworking.JacksonParserFactory


 class MCtx :Application(){
    override fun onCreate() {
        super.onCreate()
        AndroidNetworking.initialize(applicationContext);
        AndroidNetworking.setParserFactory(JacksonParserFactory())

    }
}