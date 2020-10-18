package com.example.buku_tetangga.adapters.page_verify;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.buku_tetangga.Login;
import com.example.buku_tetangga.Register;

public class PagerAdapterVerify extends FragmentStatePagerAdapter {

    int mNoOfTabs;

    public PagerAdapterVerify(FragmentManager fm, int NumberOfTabs){
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                Login login = new Login();
                return login;
            case 1:
                Register register = new Register();
                return register;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
