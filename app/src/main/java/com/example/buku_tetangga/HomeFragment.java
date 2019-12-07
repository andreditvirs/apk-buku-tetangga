package com.example.buku_tetangga;


import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    // set card_of_home
    ViewPager viewPager;
    Adapter adapter2;
    List<Model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Set Slider
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        SliderView sliderView = (SliderView) rootView.findViewById(R.id.imageSlider);

        SliderAdapterExample adapter = new SliderAdapterExample(this.getActivity());

        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();

        // set card_of_home
        models = new ArrayList<>();
        models.add(new Model(R.drawable.brochure, "Brochure", "Ini adalah brosur, Ini adalah brosur, ini sungguh sebuah brosur anda tidak percaya? coba click saja ini"));
        models.add(new Model(R.drawable.sticker, "Stiker", "Ini adalah stiker"));
        models.add(new Model(R.drawable.poster, "Poster", "Ini adalah poster"));
        models.add(new Model(R.drawable.namecard, "Namecard", "Ini adalah kartu nama"));

        adapter2 = new Adapter(models, this.getActivity().getApplicationContext());

        viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter2);
        viewPager.setPadding(130, 0, 130, 0);

//        Integer[] colors_temp = {
//                getResources().getColor(R.color.color1),
//                getResources().getColor(R.color.color2),
//                getResources().getColor(R.color.color3),
//                getResources().getColor(R.color.color4)
//        };
//
//        colors = colors_temp;
//
//        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                if(position < (adapter2.getCount()-1) && position < (colors.length - 1)) {
//                    viewPager.setBackgroundColor(
//                            (Integer) argbEvaluator.evaluate(
//                                    positionOffset, colors[position], colors[position + 1])
//                    );
//                }else{
//                    viewPager.setBackgroundColor(colors[colors.length - 1]);
//                }
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });

        return rootView;
    }

}
