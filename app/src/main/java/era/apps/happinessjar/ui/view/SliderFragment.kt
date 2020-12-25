package era.apps.happinessjar.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import era.apps.happinessjar.R
import era.apps.happinessjar.ui.adapter.CustomPagerAdapter
import era.apps.happinessjar.util.callback.OnItemClick


class SliderFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val root: View = inflater.inflate(R.layout.activity_slider, container, false)
        val getImabg = intArrayOf(R.layout.s_layout1, R.layout.s_layout2, R.layout.s_layout3, R.layout.s_layout4)
        val sliderPage: ViewPager = root.findViewById(R.id.slider1)
        val adapter = CustomPagerAdapter()
        adapter.setOnItemClick(OnItemClick {
            activity?.getSharedPreferences("info", 0)?.edit()
                    ?.putBoolean("firstTime", false)?.apply()
            (activity as MainActivity).attachFragment(R.id.navMessagesFragment)
        })
        adapter.setModelList(getImabg)
        sliderPage.adapter = adapter
        return root
    }
}
