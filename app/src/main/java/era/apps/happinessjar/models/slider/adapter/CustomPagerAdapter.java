package era.apps.happinessjar.models.slider.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import era.apps.happinessjar.R;
import era.apps.happinessjar.util.callback.OnItemClick;


public class CustomPagerAdapter extends PagerAdapter {
    private int[] modelList;


    public void setModelList(int[] modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return modelList.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup collection, int position) {

        LayoutInflater inflater = LayoutInflater.from(collection.getContext());
        View view = inflater.inflate(modelList[position], collection, false);
        if (position == modelList.length - 1) {
            view.findViewById(R.id.finishSlider).setOnClickListener(view1 -> onItemClick.OnClick(0));
        }
        collection.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup parant, int position, @NonNull Object view) {
        parant.removeView((View) view);
    }

}
