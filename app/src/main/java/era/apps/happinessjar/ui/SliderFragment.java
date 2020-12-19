package era.apps.happinessjar.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import era.apps.happinessjar.MainActivity;
import era.apps.happinessjar.R;
import era.apps.happinessjar.util.adapters.CustomPagerAdapter;


public class SliderFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_slider, container, false);
        int[] getImabg = {R.layout.s_layout1, R.layout.s_layout2, R.layout.s_layout3, R.layout.s_layout4};
        ViewPager sliderPage = root.findViewById(R.id.slider1);
        CustomPagerAdapter adapter = new CustomPagerAdapter();
        adapter.setOnItemClick(item -> {
            getActivity().getSharedPreferences("info", 0).edit()
                    .putBoolean("firstTime", false).apply();
            ((MainActivity) getActivity()).attachFragment(R.id.navMessagesFragment);

        });
        adapter.setModelList(getImabg);
        sliderPage.setAdapter(adapter);

        return root;
    }


}
