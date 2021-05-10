package era.apps.happinessjar.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import era.apps.happinessjar.R
import era.apps.happinessjar.data.models.Story
import era.apps.happinessjar.ui.adapter.StoriesAdapter
import era.apps.happinessjar.ui.viewmodel.StoriesViewModel


class StoriesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_stories, container, false)

        model = (activity as MainActivity).storyViewModel
        val adapter = StoriesAdapter()


        model.allStories.observe(activity as MainActivity,
                object : Observer<List<Story>> {
                    override fun onChanged(list: List<Story>) {
                        run {
                            let {
                                adapter.submitList(list as MutableList<Story>)
                                adapter.notifyDataSetChanged()
                            }
                        }
                    }
                })


        val showAllStories: RecyclerView = root.findViewById(R.id.showAllStories)
        showAllStories.layoutManager = LinearLayoutManager(context)
        showAllStories.adapter = adapter


        return root


    }

    private lateinit var model: StoriesViewModel

}