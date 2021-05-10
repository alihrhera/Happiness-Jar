package era.apps.happinessjar.util;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import era.apps.happinessjar.util.callback.OnItemClick;

public class DataManger {

    private static final DataManger obj = new DataManger();
    public static int lastChatValue=50;
    private DataManger() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("AdminToken");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot s:snapshot.getChildren()){
                        AdminKey=s.getValue(String.class);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static synchronized DataManger getInstance() {

        return obj;
    }

    public String getAdminKey() {
        return AdminKey;
    }

    private String AdminKey="";

    public String getGooglePlayAppUrl(Context context) {
        return "https://play.google.com/store/apps/details?id=" +
                context.getPackageName();
    }

    OnItemClick appStatues;

    public void setAppStatues(OnItemClick appStatues) {
        this.appStatues = appStatues;
    }

    public void loading() {
        appStatues.onClick(true);
    }

    public void normal() {
        appStatues.onClick(false);
    }


    private boolean isChatOpen = false;

    public boolean isChatOpen() {
        return isChatOpen;
    }

    public void setChatOpen(boolean chatOpen) {
        isChatOpen = chatOpen;
    }
}
