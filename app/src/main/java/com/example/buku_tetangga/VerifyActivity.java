package com.example.buku_tetangga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;

import com.example.buku_tetangga.activities.SearchAddBookActivity;

public class VerifyActivity extends AppCompatActivity implements Login.OnFragmentInteractionListener,Register.OnFragmentInteractionListener, Login.OnLoginFormActivityListener, AkunFragment.OnLogoutListener{

    public static PrefConfig prefConfig;
    public static ApiInterface apiInterface;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Register"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager)findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //set api sign and log
        prefConfig = new PrefConfig(this);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
            if(savedInstanceState != null){
                return;
            }
            if(prefConfig.readLoginStatus()) {
                startActivity(new Intent(this, Navbar.class));
            }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void performRegister() {
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
////                new RegistrationFragment2()).addToBackStack(null).commit();
        viewPager.setCurrentItem(1);
    }

    @Override
    public void performLogin(String name) {
        prefConfig.writeName(name);
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                new WelcomeFragment2()).commit();
        startActivity(new Intent(this, Navbar.class));
    }

    @Override
    public void logoutPerformed() {
        prefConfig.writeLoginStatus(false);
        prefConfig.writeName("User");
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                new LoginFragment2()).commit();
    }

    public void toLupaPassword(View view) {
        finish();
        startActivity(new Intent(this, ForgetPasswordActivity.class));
        Animatoo.animateSlideLeft(this);
    }

}
