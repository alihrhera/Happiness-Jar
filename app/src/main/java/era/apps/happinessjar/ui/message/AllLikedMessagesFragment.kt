package era.apps.happinessjar.ui.message

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import era.apps.happinessjar.MainActivity
import era.apps.happinessjar.R
import era.apps.happinessjar.util.adapters.MessagesAdapter

// show Liked  Message of the app

class AllLikedMessagesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val adapter = MessagesAdapter()
        (activity as MainActivity).getViewModel().allLikesMessage.observe(activity as MainActivity, { list ->
            run {
                let {
                    adapter.submitList(list)
                }
            }
        })

        return inflater.inflate(R.layout.fragment_liked_massges, container, false)
    }

}