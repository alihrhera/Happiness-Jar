package era.apps.happinessjar

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import era.apps.happinessjar.databinding.ActivityMainBinding
import era.apps.happinessjar.models.message.view_model.MessagesViewModel
import era.apps.happinessjar.util.DataManger
import era.apps.happinessjar.util.callback.OnItemClick


class MainActivity : AppCompatActivity() {

    private lateinit var acBar: ActionBar
    private lateinit var navController: NavController

    private lateinit var binding: ActivityMainBinding

    private lateinit var messageViwModel: MessagesViewModel

    fun showBottom() {
        binding.bLay.parentView.visibility = View.VISIBLE
    }

    fun hidBottom() {
        binding.bLay.parentView.visibility = View.GONE
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        DataManger.getInstance().setAppStatues(status)
        DataManger.getInstance().normal()
        messageViwModel = ViewModelProvider(this).get(MessagesViewModel::class.java)
        if (supportActionBar != null) {
            acBar = supportActionBar!!
        }
        val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.mainFragmentContainer) as NavHostFragment
        navController = navHostFragment.navController
        container = findViewById(R.id.container)

        val setting = findViewById<RelativeLayout>(R.id.bottom_settings)
        val stories = findViewById<RelativeLayout>(R.id.bottom_stories)
        val like = findViewById<RelativeLayout>(R.id.bottom_like)
        val whatsApp = findViewById<RelativeLayout>(R.id.bottom_whatsApp)
        val messages = findViewById<RelativeLayout>(R.id.bottom_message)

        //bottomDefaultViewStatus()
        setting.setOnClickListener {
            run {
                bottomDefaultViewStatus(it as RelativeLayout)
                attachFragment(R.id.settingsFragment)
            }

        }
        stories.setOnClickListener {
            run {
                bottomDefaultViewStatus(it as RelativeLayout)
                attachFragment(R.id.navStoriesFragment)

            }
        }
        like.setOnClickListener {
            run {
                bottomDefaultViewStatus(it as RelativeLayout)
                attachFragment(R.id.navAllLikedMessagesFragment)

            }
        }
        whatsApp.setOnClickListener {
            run {
                bottomDefaultViewStatus(it as RelativeLayout)
                attachFragment(R.id.navWhatsAppMessagesFragment)

            }
        }
        messages.setOnClickListener {
            run {
                bottomDefaultViewStatus(it as RelativeLayout)
                attachFragment(R.id.navMessagesFragment)

            }
        }
    }

    private var fragID = 0
    fun attachFragment(fragId: Int) {
        if (fragID != fragId) {
            navController.navigate(fragId)
            fragID = fragId
        }
    }

    fun getMessageViwModel(): MessagesViewModel {
        return messageViwModel
    }


    fun showActionBar() {
        acBar.show()
    }

    fun hidActionBar() {
        acBar.hide()
    }

    private lateinit var container: LinearLayout
    var crId = 0
    private fun bottomDefaultViewStatus(view: RelativeLayout) {
        if (crId != view.id) {
            val h = view.height
            var pram = LinearLayout.LayoutParams(0, h)
            for (index in 0 until (container).childCount) {
                val nextChild: RelativeLayout = ((container).getChildAt(index) as RelativeLayout)
                nextChild.setPadding(0, 0, 0, 0)
                pram.weight = 1f
                nextChild.layoutParams = pram
            }
            pram = LinearLayout.LayoutParams(0, view.height - 10)
            view.setPadding(0, 0, 0, 5)
            view.elevation = 10f
            pram.weight = 1f
            view.layoutParams = pram
            crId = view.id
        }

    }


    val status = object : OnItemClick {
        override fun OnClick(item: Any?) {
            if (item as Boolean) {
                binding.loading.visibility = View.VISIBLE

                return
            }
            binding.loading.visibility = View.GONE


        }
    }

    override fun onBackPressed() {
        if (binding.loading.visibility == View.VISIBLE) {
            binding.loading.visibility = View.GONE
            return
        }
        super.onBackPressed()
    }
}