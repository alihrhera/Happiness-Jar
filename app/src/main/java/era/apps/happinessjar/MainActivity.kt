package era.apps.happinessjar

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import era.apps.happinessjar.util.database.MessagesViewModel

class  MainActivity : AppCompatActivity() {

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


    lateinit var messageViwModel:MessagesViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        messageViwModel=ViewModelProvider(this).get(MessagesViewModel::class.java)

        if (supportActionBar != null) {
            acBar = supportActionBar!!
        }


        val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHostFragment.navController

    }

    fun attachFragment(fragId: Int) {
        navController.navigate(fragId)

    }
    fun getViewModel(): MessagesViewModel {
        return messageViwModel
    }

}