package com.example.buku_tetangga;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.buku_tetangga.model.book_butang_activity.Buku;

public class PagerAdapterRakbuku extends FragmentStatePagerAdapter {

    int mNoOfTabs;
    private Buku buku;

    public PagerAdapterRakbuku(FragmentManager fm, int NumberOfTabs, Buku buku){
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
        this.buku = buku;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0:
                Bundle bundle = new Bundle();
                bundle.putString("deskripsi", buku.getDeskripsi());
                DeskripsiBukuFragment deskripsiBukuFragment = new DeskripsiBukuFragment();
                deskripsiBukuFragment.setArguments(bundle);
                return deskripsiBukuFragment;
            case 1:
                DetilBukuFragment detilBukuFragment = new DetilBukuFragment();
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
