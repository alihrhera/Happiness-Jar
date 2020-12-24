package era.apps.happinessjar.models.chat.adapter;


import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import era.apps.happinessjar.R;
import era.apps.happinessjar.models.chat.model.ChatMessages;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {
    private final String id;

    public ChatAdapter(String id) {
        this.id = id;
    }


    private List<ChatMessages> dataList = new ArrayList<>();


    public void setDataList(List<ChatMessages> list) {
        dataList = list;
        Collections.sort(dataList, (o1, o2) -> o1.getTimeToSort().compareTo(o2.getTimeToSort()));
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.row_reciver_layout, null);
        if (viewType == 1) {
            view = View.inflate(parent.getContext(), R.layout.row_sender_layout, null);
        }
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ChatMessages messages = dataList.get(position);
        holder.msg.setText(messages.getMessages());
        Date date = new Date(messages.getTime());
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        String sTime = android.text.format.DateFormat.format("yyyy-MM-dd   hh:mm a", date).toString();
        // String time=date.toString()+"    " +c.get(Calendar.HOUR)+":"+c.get(Calendar.MINUTE);
        holder.time.setText(sTime);
        holder.isRead.setVisibility(View.GONE);
        if (getItemViewType(position) == 1 && messages.isRead()) {
            holder.isRead.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    @Override
    public int getItemViewType(int position) {
        int type = 0;
        if (dataList.get(position).getSenderId().equals(id)) {
            type = 1;
        }
        return type;
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView msg;//, textNum;
        TextView time;//, textNum;
        View isRead;//, textNum;

        MyViewHolder(@NonNull View view) {
            super(view);
            msg = view.findViewById(R.id.masge);
            time = view.findViewById(R.id.time);
            isRead = view.findViewById(R.id.isRead);
        }

    }


}
