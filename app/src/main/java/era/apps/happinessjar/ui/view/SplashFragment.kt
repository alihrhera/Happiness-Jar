package era.apps.happinessjar.ui.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import era.apps.happinessjar.R


class SplashFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).hidActionBar()
        (activity as MainActivity).hidBottom()

        val first =
                activity?.getSharedPreferences("info", 0)?.getBoolean("firstTime", true)


        Handler(Looper.getMainLooper()).postDelayed({
            if (first!!) {
                (activity as MainActivity).attachFragment(R.id.sliderFragment)
            }else {
                (activity as MainActivity).attachFragment(R.id.navMessagesFragment)
            }

        }, 1000)





        return inflater.inflate(R.layout.fragment_splash, container, false)
    }


}