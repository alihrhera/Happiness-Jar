package era.apps.happinessjar.util;


import android.appwidget.AppWidgetManager;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.happiness.jar.R;
import com.happiness.jar.utli.RoomDataBase.MyRepository;
import com.happiness.jar.utli.RoomDataBase.MyRow;
import com.happiness.jar.utli.viewHelper.NewAppWidget;


public class Myfcm extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String TAG = "Myfcm";

        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Message data payload: " + remoteMessage.getData());
            String  type=remoteMessage.getData().get("type");
            assert type != null;
            int icon;

            if (type.contains("stories")){
                boolean notify= getApplicationContext().getSharedPreferences("mesg",0).getBoolean("storyNotify",true);
                if (notify) {
                    icon = R.drawable.ic_clossbook;
                    getApplicationContext().getSharedPreferences("msg", 0).edit().putBoolean("getStory", true).apply();
                    CreateNotifa.getInstance(getApplicationContext()).Notify("حدوته قبل النوم ", icon, type);
                }
            }else if(type.equals("Message")){
                String messg=remoteMessage.getData().get("messg");

                getApplicationContext().getSharedPreferences("msg",0)
                        .edit().putString("WidgeMsg",messg).apply();
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
                int mAppWidgetId = getApplicationContext().getSharedPreferences("msg",0).getInt("widgId",0);
                NewAppWidget.updateAppWidget(getApplicationContext(), appWidgetManager, mAppWidgetId);
                NewAppWidget.updateCurrentWidget(getApplicationContext(),messg);
                icon = R.drawable.logo;
                CreateNotifa.getInstance(getApplicationContext())
                        .Notify("صباح الخير رسالتك جاهزه", icon,type);
                 MyRepository repo=new MyRepository(getApplication());
                 MyRow row=new MyRow(0,"",messg,type,"");
                 repo.Insert(row);

            }else if (type.contains("Admin")){
                String messg=remoteMessage.getData().get("messg");
                icon = R.drawable.logo;
                String msg="اهلا وسهلا بيك نورتنا هيتبعتلك كل يوم رساله ان شاء الله تفرحك وتحفزك " ;

                if (messg.contains("Admin")){
                    messg=messg.replace("Admin:","");
                    msg=messg;
                }

                CreateNotifa.getInstance(getApplicationContext())
                        .Notify(msg, icon,"Message");
            }else if (type.contains("Chat")){
                String messg=remoteMessage.getData().get("messg");
                icon = R.drawable.logo;
                String msg="ف رساله ليك فى الشات " ;

                CreateNotifa.getInstance(getApplicationContext())
                        .Notify(msg, icon,"Chat");
            }

        }

        if (remoteMessage.getNotification() != null) {

            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
    }



}
