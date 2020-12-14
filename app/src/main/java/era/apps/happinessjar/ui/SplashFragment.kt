package era.apps.happinessjar.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import era.apps.happinessjar.MainActivity
import era.apps.happinessjar.R
import java.lang.Exception


class SplashFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).hidActionBar()
            object : Thread() {
                override fun run() {
                    super.run()
                    try {
                        sleep(1800)

                    } catch (e: Exception) {
                    }
                }
            }.start()
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }







}