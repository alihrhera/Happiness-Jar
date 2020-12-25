package era.apps.happinessjar.ui.adapter

import android.text.format.DateFormat
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import era.apps.happinessjar.R
import era.apps.happinessjar.data.models.ChatMessages
import java.sql.Date
import java.util.*
import java.util.Collections.sort

public class ChatAdapter(private val id: String) : RecyclerView.Adapter<ChatAdapter.MyViewHolder>() {
    private var dataList: List<ChatMessages> = ArrayList()

    fun setDataList(list: List<ChatMessages>) {
        dataList = list
        sort(dataList) { o1: ChatMessages, o2: ChatMessages -> o1.timeToSort.compareTo(o2.timeToSort) }
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = View.inflate(parent.context, R.layout.row_reciver_layout, null)
        if (viewType == 1) {
            view = View.inflate(parent.context, R.layout.row_sender_layout, null)
        }
        return MyViewHolder(view)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val messages = dataList[position]
        holder.msg.text = messages.messages
        val date = Date(messages.time)
        val c = Calendar.getInstance()
        c.time = date
        val sTime = DateFormat.format("yyyy-MM-dd   hh:mm a", date).toString()
        // String time=date.toString()+"    " +c.get(Calendar.HOUR)+":"+c.get(Calendar.MINUTE);
        holder.time.text = sTime
        holder.isRead.visibility = View.GONE
        if (getItemViewType(position) == 1 && messages.isRead) {
            holder.isRead.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int = dataList.size

    override fun getItemViewType(position: Int): Int {
        if (dataList[position].senderId == id) {
            return 1
        }
        return 0
    }

     class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val msg
                : TextView = view.findViewById(R.id.masge)
         val time
                : TextView = view.findViewById(R.id.time)
         val isRead
                : View = view.findViewById(R.id.isRead)

     }

}
