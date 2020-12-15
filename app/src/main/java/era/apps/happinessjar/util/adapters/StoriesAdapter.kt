package era.apps.happinessjar.util.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import era.apps.happinessjar.R
import era.apps.happinessjar.util.database.AppMessage
import era.apps.happinessjar.util.database.Story

class StoriesAdapter : ListAdapter<Story, StoriesAdapter.StoriesViewHolder>(SleepNightDiffCallback()) {

    class SleepNightDiffCallback : DiffUtil.ItemCallback<Story>() {

        override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean {
            return oldItem.equals(newItem)
        }
    }

    var messageList = listOf<AppMessage>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoriesViewHolder {
        return StoriesViewHolder.from(parent)
    }

    override fun getItemCount() = messageList.size

    override fun onBindViewHolder(holder: StoriesViewHolder, position: Int) {
        val item = messageList[position]
        holder.bind(item)
    }

     class StoriesViewHolder private constructor(itemView: View)
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
            fun from(parent: ViewGroup): StoriesViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                        .inflate(R.layout.row_one_message, parent, false)

                return StoriesViewHolder(view)
            }
        }
    }
}