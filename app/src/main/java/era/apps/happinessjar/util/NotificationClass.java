package era.apps.happinessjar.util;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import era.apps.happinessjar.ui.view.MainActivity;
import era.apps.happinessjar.R;

class NotificationClass {

    private static final NotificationClass obj = new NotificationClass();


    private NotificationClass() {
    }
    public static synchronized NotificationClass getInstance() {
        return obj;
    }



    private int notifyID=0;
    public void showNotification(Context context, String message ){
        if (message.contains("message")){

        }
        notifyID++;
        Intent intent = new Intent(context, MainActivity.class);
        intent.setAction(message);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        String CHANNEL_ID = "Happiness Jar";
        NotificationCompat.Builder notification =
                new NotificationCompat.Builder(context, CHANNEL_ID);
        notification.setSmallIcon(R.drawable.jam);
        notification .setContentTitle(message.replace("message",""));
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
