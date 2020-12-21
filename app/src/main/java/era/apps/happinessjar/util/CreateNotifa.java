package era.apps.happinessjar.util;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.happiness.jar.AdsActvty;
import com.happiness.jar.NewHomeActivity;
import com.happiness.jar.R;


public class CreateNotifa {

    private Context context;
    private CreateNotifa(Context context){
        this.context=context;
    }

     private static com.happiness.jar.utli.viewHelper.CreateNotifa obj;

     public static synchronized com.happiness.jar.utli.viewHelper.CreateNotifa getInstance(Context context){
         if (null==obj){
             obj=new com.happiness.jar.utli.viewHelper.CreateNotifa(context);
         }

         return obj;
     }

    private int notifyID=0;
     public void  Notify(String title,int icon,String type){
         context.startActivity(new Intent(context, AdsActvty.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
         notifyID++;

         Intent intent = new Intent(context, NewHomeActivity.class);
         intent.setAction(type);
         intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
         PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
         String CHANNEL_ID = "HappinessJar";
         NotificationCompat.Builder notification =
                 new NotificationCompat.Builder(context, CHANNEL_ID);
         notification.setSmallIcon(icon);
         notification .setContentTitle(title);
         notification.setContentIntent(pendingIntent);
         notification.setAutoCancel(true);
         NotificationManager mNotificationManager =
                 (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
                NotificationChannel mchannel = new NotificationChannel(CHANNEL_ID, context.getString(R.string.app_name)
                        , NotificationManager.IMPORTANCE_HIGH);
                mNotificationManager.createNotificationChannel(mchannel);
            }
         mNotificationManager.notify(notifyID , notification.build());
     }



}
