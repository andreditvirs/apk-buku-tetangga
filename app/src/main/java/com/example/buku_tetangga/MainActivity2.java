package com.example.buku_tetangga;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity2 extends AppCompatActivity implements LoginFragment2.OnLoginFormActivityListener {

    public static PrefConfig prefConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //set api sign and log
        prefConfig = new PrefConfig(this);
        if (findViewById(R.id.fragment_container)!= null){
            if(savedInstanceState != null){
                return;
            }
            if(prefConfig.readLoginStatus()){
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new WelcomeFragment()).commit();
            }else{
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new LoginFragment2()).commit();
            }

        }
    }

    @Override
    public void performRegister() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new RegistrationFragment2()).addToBackStack(null).commit();
    }

    @Override
    public void performLogin(String name) {

    }
}
