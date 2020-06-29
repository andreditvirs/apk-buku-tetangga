package com.example.buku_tetangga;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapterRakbuku extends FragmentStatePagerAdapter {

    int mNoOfTabs;
    private String rakbuku_id;

    public PagerAdapterRakbuku(FragmentManager fm, int NumberOfTabs, String rakbuku_id){
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
        this.rakbuku_id = rakbuku_id;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0:
                DeskripsiBukuFragment deskripsiBukuFragment = new DeskripsiBukuFragment();
                deskripsiBukuFragment.setRakbuku_id(rakbuku_id);
                return deskripsiBukuFragment;
            case 1:
                DetilBukuFragment detilBukuFragment = new DetilBukuFragment();
                detilBukuFragment.setRakbuku_id(rakbuku_id);
                return detilBukuFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
