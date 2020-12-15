package era.apps.happinessjar

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.graphics.Outline
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import edmt.dev.testcurved.CurvedBNView
import era.apps.happinessjar.databinding.ActivityMainBinding
import era.apps.happinessjar.util.database.MessagesViewModel

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var acBar: ActionBar
    private lateinit var navController: NavController

    private lateinit var binding: ActivityMainBinding

    fun showActionBar() {
        acBar.show()
    }

    fun hidActionBar() {
        acBar.hide()
    }


    private lateinit var messageViwModel: MessagesViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        messageViwModel = ViewModelProvider(this).get(MessagesViewModel::class.java)

        if (supportActionBar != null) {
            acBar = supportActionBar!!
        }


        val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.mainFragmentContainer) as NavHostFragment
        navController = navHostFragment.navController


        binding.btomNaveView.setOnNavigationItemSelectedListener(this@MainActivity)
        binding.btomNaveView.inflateMenu(R.menu.bottom_menu)

        binding.btomNaveView.selectedItemId=R.id.home

    }

    fun attachFragment(fragId: Int) {
        navController.navigate(fragId)

    }

    fun getViewModel(): MessagesViewModel {
        return messageViwModel
    }


    @SuppressLint("RestrictedApi")
    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.itemId) {
            R.id.home -> {
                draw(6,binding.btomNaveView)
                binding.lineId.x = binding.btomNaveView.mFirstCurveControlPoint1.x.toFloat()
                binding.fab1.visibility = View.VISIBLE
                binding.fab2.visibility = View.GONE
                binding.fab3.visibility = View.GONE
                binding.fab4.visibility = View.GONE
                drawAnimation(binding.fab1)
                Toast.makeText(this, "Click", Toast.LENGTH_LONG).show()
            }
            R.id.fav -> {
                    binding.fab1.visibility = View.GONE
                binding.fab2.visibility = View.VISIBLE
                binding.fab3.visibility = View.GONE
                binding.fab4.visibility = View.GONE
                draw(2,binding.btomNaveView)

            }
            R.id.settings -> {
                draw(binding.btomNaveView)
                binding.fab1.visibility = View.GONE
                binding.fab2.visibility = View.GONE
                binding.fab3.visibility = View.VISIBLE
                binding.fab4.visibility = View.GONE
            }
            R.id.whatsApp -> {

            }
            R.id.stories -> {

            }
        }
        return true

    }

    private fun drawAnimation(fab1: FloatingActionButton) {
        val valueAnm = ValueAnimator.ofFloat(0.0f, 1.0f)
        valueAnm.duration = 1000
        valueAnm.addUpdateListener { }
    }

    fun draw(i: Int,bView: CurvedBNView) {

        bView.mFirstCurveStartPoint.set(
                bView.mNavigationBarWidth / i - bView.CURVE_CIRCLE_RADIUS * 2
                        - bView.CURVE_CIRCLE_RADIUS / 3, 0
        )
        bView.mFirstCurveEndPoint.set(
                bView.mNavigationBarWidth / i ,
                bView.CURVE_CIRCLE_RADIUS + bView.CURVE_CIRCLE_RADIUS/4)
        bView.mSecondCurveStartPoint=bView.mFirstCurveEndPoint
        bView.mSecondCurveEndPoint.set(

                bView.mNavigationBarWidth / i + bView.CURVE_CIRCLE_RADIUS * 2
                        + bView.CURVE_CIRCLE_RADIUS / 3, 0
        )

        bView.mFirstCurveControlPoint1.set( bView.mFirstCurveStartPoint.x+
                bView.CURVE_CIRCLE_RADIUS+ bView.CURVE_CIRCLE_RADIUS/4
                , bView.mFirstCurveStartPoint.y)

        bView.mSecondCurveStartPoint.set( bView.mFirstCurveEndPoint.x  -
                bView.CURVE_CIRCLE_RADIUS*2 + bView.CURVE_CIRCLE_RADIUS,
                bView.mFirstCurveEndPoint.y
        )


        bView.mSecondCurveControlPoint1.set( bView.mSecondCurveStartPoint.x
        + bView.CURVE_CIRCLE_RADIUS*2 - bView.CURVE_CIRCLE_RADIUS,
                bView.mSecondCurveStartPoint.y
        )



        bView.mSecondCurveControlPoint2.set(  bView.mSecondCurveEndPoint.x
        - (bView .CURVE_CIRCLE_RADIUS +   bView. CURVE_CIRCLE_RADIUS/4 ) ,
                bView.mSecondCurveEndPoint.y)






    }
    fun draw(bView: CurvedBNView) {

        bView.mFirstCurveStartPoint.set(
                bView.mNavigationBarWidth *10/12 - bView.CURVE_CIRCLE_RADIUS * 2
                        - bView.CURVE_CIRCLE_RADIUS / 3, 0
        )
        bView.mFirstCurveEndPoint.set(
                bView.mNavigationBarWidth *10/12,
                bView.CURVE_CIRCLE_RADIUS + bView.CURVE_CIRCLE_RADIUS/4)
        bView.mSecondCurveStartPoint=bView.mFirstCurveEndPoint
        bView.mSecondCurveEndPoint.set(

                bView.mNavigationBarWidth *10/12 + bView.CURVE_CIRCLE_RADIUS * 2
                        + bView.CURVE_CIRCLE_RADIUS / 3, 0
        )

        bView.mFirstCurveControlPoint1.set( bView.mFirstCurveStartPoint.x+
                bView.CURVE_CIRCLE_RADIUS+ bView.CURVE_CIRCLE_RADIUS/4
                , bView.mFirstCurveStartPoint.y)

        bView.mSecondCurveStartPoint.set( bView.mFirstCurveEndPoint.x  -
                bView.CURVE_CIRCLE_RADIUS*2 + bView.CURVE_CIRCLE_RADIUS,
                bView.mFirstCurveEndPoint.y
        )


        bView.mSecondCurveControlPoint1.set( bView.mSecondCurveStartPoint.x
                + bView.CURVE_CIRCLE_RADIUS*2 - bView.CURVE_CIRCLE_RADIUS,
                bView.mSecondCurveStartPoint.y
        )



        bView.mSecondCurveControlPoint2.set(  bView.mSecondCurveEndPoint.x
                - (bView .CURVE_CIRCLE_RADIUS +   bView. CURVE_CIRCLE_RADIUS/4 ) ,
                bView.mSecondCurveEndPoint.y)






    }


}