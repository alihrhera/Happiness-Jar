package era.apps.happinessjar.models.message

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import era.apps.happinessjar.MainActivity
import era.apps.happinessjar.R
import era.apps.happinessjar.models.message.adapter.MessagesAdapter
import era.apps.happinessjar.models.message.data_base.AppMessage
import era.apps.happinessjar.util.callback.OnItemClick

// show WhatsApp Messages

class WhatsAppMessagesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root=inflater.inflate(R.layout.fragment_whats_app_massges, container, false)
        val adapter = MessagesAdapter()
        val model= (activity as MainActivity).getMessageViwModel()

        model .allAppWhatsApp.observe(activity as MainActivity,
                object : Observer<List<AppMessage>> {
                    override fun onChanged(list: List<AppMessage>?) {
                        run {
                            let {
                                adapter.submitList(list as MutableList<AppMessage>)
                            }
                        }
                    }
                })



        adapter.onLike = OnItemClick { item ->
            run {
                model.like(item as AppMessage)
            }
        }
        adapter.onSave = OnItemClick { item ->
            run {

            }
        }
        adapter.onShare = OnItemClick { item ->
            run {

            }
        }



        val showAllWhatsAppMessage:RecyclerView=root.findViewById(R.id.showAllWhatsAppMessage)
        showAllWhatsAppMessage.layoutManager=LinearLayoutManager(context)
        showAllWhatsAppMessage.adapter=adapter



        return root

    }


}