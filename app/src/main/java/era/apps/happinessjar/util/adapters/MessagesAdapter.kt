package era.apps.happinessjar.util.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import era.apps.happinessjar.R
import era.apps.happinessjar.util.database.AppMessage

class MessagesAdapter : ListAdapter<AppMessage, MessagesAdapter.MessagesHolder>(SleepNightDiffCallback()) {

    class SleepNightDiffCallback : DiffUtil.ItemCallback<AppMessage>() {

        override fun areItemsTheSame(oldItem: AppMessage, newItem: AppMessage): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: AppMessage, newItem: AppMessage): Boolean {
            return oldItem.equals(newItem)
        }
    }

    var messageList = listOf<AppMessage>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagesHolder {
        return MessagesHolder.from(parent)
    }

    override fun getItemCount() = messageList.size

    override fun onBindViewHolder(holder: MessagesHolder, position: Int) {
        val item = messageList[position]
        holder.bind(item)
    }

     class MessagesHolder private constructor(itemView: View)
        : RecyclerView.ViewHolder(itemView) {
        //val sleepLength: TextView = itemView.findViewById(R.id.sleep_length)
        //val quality: TextView = itemView.findViewById(R.id.quality_string)
        //val qualityImage: ImageView = itemView.findViewById(R.id.quality_image)

        fun bind(item: AppMessage) {
            val res = itemView.context.resources
            /* sleepLength.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
            quality.text = convertNumericQualityToString(item.sleepQuality, res)
           qualityImage.setImageResource(when (item.sleepQuality) {
                0 -> R.drawable.ic_sleep_0
                1 -> R.drawable.ic_sleep_1
                2 -> R.drawable.ic_sleep_2
                3 -> R.drawable.ic_sleep_3
                4 -> R.drawable.ic_sleep_4
                5 -> R.drawable.ic_sleep_5
                else -> R.drawable.ic_sleep_active
            })*/
        }

        companion object {
            fun from(parent: ViewGroup): MessagesHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                        .inflate(R.layout.row_one_message, parent, false)

                return MessagesHolder(view)
            }
        }
    }
}