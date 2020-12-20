package era.apps.happinessjar.models.chat;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

import era.apps.happinessjar.R;
import era.apps.happinessjar.models.chat.adapter.ChatAdapter;
import era.apps.happinessjar.models.chat.model.ChatMessages;
import era.apps.happinessjar.models.chat.model.Conversation;

public class ChatFragment extends Fragment {


    public ChatFragment() {
        // Required empty public constructor
    }

    private EditText getMsg;
    private String myId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_chat, container, false);
        RecyclerView allChat = root.findViewById(R.id.chat);
        allChat.setLayoutManager(new LinearLayoutManager(getContext()));
        Context context = getContext();
        myId = context.getSharedPreferences("msg", 0).getString("chatId", "");
        ChatAdapter adapter = new ChatAdapter(myId);
        allChat.setAdapter(adapter);
        getMsg = root.findViewById(R.id.getMsg);

        Conversation con = new Conversation();
        root.findViewById(R.id.sendMsg).setOnClickListener(v -> {
            if (getMsg.getText().length() > 0) {
                if (!getMsg.getText().toString().equals(" ")) {
                    ChatMessages ms = new ChatMessages();
                    ms.setSenderId(myId);
                    ms.setMessages(getMsg.getText().toString());
                    ms.setTime(Calendar.getInstance().getTimeInMillis());
                    if (con.getList() == null) {
                        con.setList(new ArrayList<>());
                    }
                    con.getList().add(ms);
                    con.setLastTimeSend(ms.getTime());
                    //sendMsg(con);
                }
            }
        });
        return root;
    }


}