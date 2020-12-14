package era.apps.happinessjar

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {

    private lateinit var acBar: ActionBar
    private lateinit var navController: NavController




     fun showActionBar()
    {
        acBar.show()
    }

     fun hidActionBar()
    {
        acBar.hide()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (supportActionBar != null) {
            acBar = supportActionBar!!
        }


        val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHostFragment.navController

    }

    fun attachFragment(fragId: Int) {
        // navigate to a anew fragment by it's id
        navController.navigate(fragId)

    }


}