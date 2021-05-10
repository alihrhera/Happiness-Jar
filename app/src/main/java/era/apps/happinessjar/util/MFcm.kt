package era.apps.happinessjar.util

import android.appwidget.AppWidgetManager
import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import era.apps.happinessjar.data.models.AppMessage
import era.apps.happinessjar.data.networking.AppApi
import era.apps.happinessjar.data.repository.MessageRepository
import era.apps.happinessjar.ui.view.masseges.MessageWidget.Companion.sUpdateAppWidget
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MFcm : FirebaseMessagingService() {
    override fun onNewToken(s: String) {
        super.onNewToken(s)
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("DeviceTokens")
        myRef.child(s).setValue(s).addOnSuccessListener {
            AppApi.getInstance()
                    .notifyUserThereIsNewMessage(s, "ربنا معاك وبيجبر بخاطرك ديما و بيحبك ف تفائل ❤️", "message")
        }
        getSharedPreferences("info", 0)
                .edit().putString("fcm", s).apply()
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.e("Test Fcm ", remoteMessage.data.toString())
        if (remoteMessage.data["type"]!!.contains("ChatMessage")) {
            if (!DataManger.getInstance().isChatOpen) {
                NotificationClass.getInstance().showNotification(applicationContext, "chat" + "لديك رد على رسالتك ")
            }
        } else if (remoteMessage.data["type"]!!.contains("Admin")) {
//
            NotificationClass.getInstance().showNotification(applicationContext, "message" + " أهلا بيك ان شاء الله هيتم ارسال رسالتين بصوره يوميه  شكرا لاستخدامك برطمان السعاده :)")

        } else if (remoteMessage.data["type"]!!.toLowerCase().contains(("Message").toString().toLowerCase())) {
                val message: String = remoteMessage.data["Message"].toString()
                NotificationClass.getInstance().showNotification(applicationContext, "message"+message)
                applicationContext.getSharedPreferences("message", 0)
                        .edit().putString("WidgetMessage", message).apply()
                val appWidgetManager = AppWidgetManager.getInstance(applicationContext)
                val mAppWidgetId = applicationContext
                        .getSharedPreferences("msg", 0).getInt("widgId", 0)
                sUpdateAppWidget(applicationContext, appWidgetManager, mAppWidgetId)

                /* icon = R.drawable.logo;
                CreateNotifa.getInstance(getApplicationContext())
                        .Notify("صباح الخير رسالتك جاهزه", icon,type);
                MyRepository repo=new MyRepository(getApplication());
                MyRow row=new MyRow(0,"",messg,type,"");
                repo.Insert(row);*/
                val appMessage = AppMessage(0, message, "message")
                val repository = MessageRepository(application)

                GlobalScope.launch {
                    repository.insert(appMessage)
            }
        }
    }
}