package era.apps.happinessjar.models

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import era.apps.happinessjar.MainActivity
import era.apps.happinessjar.R
import era.apps.happinessjar.util.DataManger


class SettingsFragment : Fragment() {

    lateinit var mContext: Context
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mContext = requireActivity()
        iniDialog()

        (activity as MainActivity).hidActionBar()
        val root = inflater.inflate(R.layout.fragment_settings, container, false)
        root.findViewById<View>(R.id.chatWithUs).setOnClickListener {
            showRegisterDialog()
        }

        root.findViewById<View>(R.id.ratUsOnPlay).setOnClickListener {
            ratUs()
        }

        root.findViewById<View>(R.id.howToAddWidgs).setOnClickListener {
            (activity as MainActivity).attachFragment(R.id.sliderFragment)
        }





        return root
    }

    lateinit var dialog: Dialog
    private fun showRegisterDialog() {
        val isReg = mContext
                .getSharedPreferences("info", 0).getBoolean("reg", false)
        if (!isReg) {
            try {
                dialog.show()
            } catch (e: Exception) {
                Log.e("Ex", e.message + " " + e.cause + " " + e.localizedMessage + " " + (dialog == null))
            }
            return
        }
        (activity as MainActivity).attachFragment(R.id.chatFragment)
    }

    private fun ratUs() {
        try {
            val url: String = DataManger.getInstance().getGooglePlayAppUrl(requireContext())
            val googlePlayIntent = Intent(Intent.ACTION_VIEW)
            googlePlayIntent.data = Uri.parse(url)
            startActivity(googlePlayIntent)
        } catch (e: Exception) {
            val url: String = DataManger.getInstance().getGooglePlayAppUrl(requireContext())
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browserIntent)
        }
    }

    private fun iniDialog() {
        dialog = object : Dialog(mContext) {
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                try {
                    setContentView(R.layout.dialog_get_info)
                    val width = (activity!!.resources.displayMetrics.widthPixels * 0.90).toInt()
//                    if (window == null) {
//                        return
//                    }
//                    // getWindow().setBackgroundDrawableResource(R.color.transparent);
                    window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
                    val getPhone = findViewById<EditText>(R.id.getPhone)
                    val getName = findViewById<EditText>(R.id.getName)
                    findViewById<View>(R.id.startChat).setOnClickListener {
                        if (getPhone.text.length <= 10) {
                            getPhone.error = "مطلوب"
                        }
                        if (getName.text.length <= 1) {
                            getName.error = "مطلوب"
                        }
                        context.getSharedPreferences("info", 0).edit().putString("chatId", getPhone.text.toString()).apply()
                        context.getSharedPreferences("info", 0).edit().putString("chatName", getName.text.toString()).apply()
                        context.getSharedPreferences("info", 0).edit().putBoolean("reg", true).apply()
                        (activity as MainActivity).attachFragment(R.id.chatFragment)
                        dismiss()
                    }

                } catch (e: Exception) {
                    Log.e("FromInsid", e.message + " " + e.cause + " " + e.localizedMessage + " " + (dialog == null))

                }
            }
        }
    }
}