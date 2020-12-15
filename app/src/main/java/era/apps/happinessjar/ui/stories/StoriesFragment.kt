package era.apps.happinessjar.ui.stories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import era.apps.happinessjar.MainActivity
import era.apps.happinessjar.R
import era.apps.happinessjar.util.adapters.StoriesAdapter


class StoriesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
            val adapter = StoriesAdapter()
        (activity as MainActivity).getViewModel()
                .allStories.observe(activity as MainActivity, { list ->
            run {
                let {
                    adapter.submitList(list)
                }
            }
        })
        return inflater.inflate(R.layout.fragment_whats_app_massges, container, false)

    }


}