package era.apps.happinessjar.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import era.apps.happinessjar.R
import era.apps.happinessjar.util.callback.OnItemClick
import era.apps.happinessjar.data.models.AppMessage



class MessagesAdapter : ListAdapter<AppMessage, MessagesAdapter.MessagesHolder>(MessageDiffCallback()) {


    class MessageDiffCallback : DiffUtil.ItemCallback<AppMessage>() {

        override fun areItemsTheSame(oldItem: AppMessage, newItem: AppMessage): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: AppMessage, newItem: AppMessage): Boolean {
            return newItem == oldItem
        }
    }

    lateinit var onLike: OnItemClick
    lateinit var onSave: OnItemClick
    lateinit var onShare: OnItemClick


    var messageList = listOf<AppMessage>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun submitList(list: MutableList<AppMessage>?) {
        super.submitList(list)
        messageList=list as List<AppMessage>
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagesHolder {
        return MessagesHolder.from(parent)
    }

    override fun getItemCount() = messageList.size

    override fun onBindViewHolder(holder: MessagesHolder, position: Int) {

        val item = messageList[position]
        holder.bind(item)
        holder.like.setOnClickListener {
            onLike.onClick(item)

        }
        holder.save.setOnClickListener {
            onSave.onClick(item)

        }
        holder.share.setOnClickListener {
            onShare.onClick(item)

        }
        holder.parent.setCardBackgroundColor(returnColor(holder.itemView.context))

    }

    var colorCode = 0
    private fun returnColor(context: Context): Int {
        var color: Int = context.resources.getColor(R.color.color0)
        when (colorCode) {
            0 -> {
                colorCode += 1
                color = context.resources.getColor(R.color.color0)

            }
            1 -> {
                colorCode += 1
                color = context.resources.getColor(R.color.color1)

            }
            2 -> {
                colorCode += 1
                color = context.resources.getColor(R.color.color2)

            }
            3 -> {
                colorCode += 1
                color = context.resources.getColor(R.color.color3)

            }
            4 -> {
                colorCode += 1
                color = context.resources.getColor(R.color.color4)

            }
            5 -> {
                colorCode = 0
                color = context.resources.getColor(R.color.color5)

            }

        }
        return color

    }


    class MessagesHolder private constructor(itemView: View)
        : RecyclerView.ViewHolder(itemView) {

        val parent: CardView = itemView.findViewById(R.id.parentCard)
        private val messageContent: TextView = itemView.findViewById(R.id.messageContent)
        val like: ImageView = itemView.findViewById(R.id.like)
        val save: ImageView = itemView.findViewById(R.id.saveAsPhoto)
        val share: ImageView = itemView.findViewById(R.id.share)

        fun bind(item: AppMessage) {
            messageContent.text = item.content
            like.setImageResource(R.drawable.ic_not_like)
            if (item.isLiked){
                like.setImageResource(R.drawable.ic_like)

            }
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