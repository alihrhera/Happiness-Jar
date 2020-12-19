package era.apps.happinessjar.ui.message

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
import era.apps.happinessjar.util.adapters.MessagesAdapter
import era.apps.happinessjar.util.callback.OnItemClick
import era.apps.happinessjar.util.database.AppMessage

// show Message of the app

class MessagesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root=inflater.inflate(R.layout.fragment_massges, container, false)

        (activity as MainActivity).showBottom()

        val adapter = MessagesAdapter()
        val model=(activity as MainActivity).getViewModel()
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


        model.allAppMessage.observe(activity as MainActivity,
                object : Observer<List<AppMessage>> {
                    override fun onChanged(list: List<AppMessage>?) {
                        run {
                            let {
                                adapter.submitList(list as MutableList<AppMessage>)
                            }
                        }
                    }
                }
        )


        val showAllMessage: RecyclerView =root.findViewById(R.id.showAllMessage)
        showAllMessage.layoutManager= LinearLayoutManager(context)
        showAllMessage.adapter=adapter



        return root
    }


}