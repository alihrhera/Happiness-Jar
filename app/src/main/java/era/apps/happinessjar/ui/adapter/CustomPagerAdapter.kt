package era.apps.happinessjar.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import era.apps.happinessjar.R
import era.apps.happinessjar.util.callback.OnItemClick


class CustomPagerAdapter : PagerAdapter() {
    private lateinit var modelList: IntArray
    fun setModelList(modelList: IntArray) {
        this.modelList = modelList
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return modelList.size
    }

    override fun isViewFromObject(view: View, o: Any): Boolean {
        return view === o
    }

    private var onItemClick: OnItemClick? = null
    fun setOnItemClick(onItemClick: OnItemClick?) {
        this.onItemClick = onItemClick
    }

    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(collection.context)
        val view = inflater.inflate(modelList[position], collection, false)
        if (position == modelList.size - 1) {
            view.findViewById<View>(R.id.finishSlider).setOnClickListener {
            onItemClick!!.onClick(0) }
        }
        collection.addView(view)
        return view
    }

    override fun destroyItem(parant: ViewGroup, position: Int, view: Any) {
        parant.removeView(view as View)
    }
}
