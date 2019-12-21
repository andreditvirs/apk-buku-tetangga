package com.example.buku_tetangga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Navbar extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private HomeFragment homeFragment;
    private KeranjangFragment keranjangFragment;
    private RiwayatFragment riwayatFragment;
    private AkunFragment akunFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        //Set BottomNavigationView
        mMainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav);

        homeFragment = new HomeFragment();
        keranjangFragment = new KeranjangFragment();
        riwayatFragment = new RiwayatFragment();
        akunFragment = new AkunFragment();

        setFragment(homeFragment);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()) {
                    case R.id.nav_beranda:
                        setFragment(homeFragment);
                        return true;
                    case R.id.nav_sewa:
                        setFragment(keranjangFragment);
                        return true;
                    case R.id.nav_riwayat:
                        setFragment(riwayatFragment);
                        return true;
                    case R.id.nav_akun:
                        setFragment(akunFragment);
                        return true;
                    default: return false;
                }
            }
        });
    }

    private void setFragment(androidx.fragment.app.Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }

    public void toAddBook(View view) {
        startActivity(new Intent(this, AddBookActivity.class));
        Animatoo.animateSlideUp(this);
    }
}
