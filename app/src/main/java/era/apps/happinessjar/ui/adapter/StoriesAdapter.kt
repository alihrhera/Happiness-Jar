package era.apps.happinessjar.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import era.apps.happinessjar.R
import era.apps.happinessjar.data.models.Story

class StoriesAdapter : ListAdapter<Story, StoriesAdapter.StoriesViewHolder>(SleepNightDiffCallback()) {

    class SleepNightDiffCallback : DiffUtil.ItemCallback<Story>() {

        override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean {
            return oldItem == newItem
        }
    }

    private var storyList: MutableList<Story> = ArrayList()


    override fun submitList(list: MutableList<Story>?) {
        storyList = list!!

        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoriesViewHolder {
        return StoriesViewHolder.from(parent)
    }

    override fun getItemCount() = storyList.size

    override fun onBindViewHolder(holder: StoriesViewHolder, position: Int) {

        val item = storyList[position]
        holder.bind(item)
    }

    class StoriesViewHolder private constructor(itemView: View)
        : RecyclerView.ViewHolder(itemView) {
        //val sleepLength: TextView = itemView.findViewById(R.id.sleep_length)
        private val storyTitle: TextView = itemView.findViewById(R.id.story_title)
        private val storyImage: ImageView = itemView.findViewById(R.id.story_image)

        fun bind(item: Story) {

            storyTitle.text = item.title
            if (item.photoLink.length > 10) {
                Picasso.get().load(item.photoLink.replace(" ","")).centerCrop().fit().into(storyImage)
                return
            }
            Picasso.get().load(R.drawable.story).centerCrop().fit().into(storyImage)

        }

        companion object {
            fun from(parent: ViewGroup): StoriesViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                        .inflate(R.layout.row_one_stories, parent, false)

                return StoriesViewHolder(view)
            }
        }
    }
}