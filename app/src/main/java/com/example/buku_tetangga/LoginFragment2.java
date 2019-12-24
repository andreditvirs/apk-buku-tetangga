package com.example.buku_tetangga;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment2 extends Fragment {

    private TextView regText;
    OnLoginFormActivityListener loginFormActivityListener;

    private EditText user_name, user_pass;
    private Button btn_login;

    public interface OnLoginFormActivityListener{
        public void performRegister();
        public void performLogin(String name);
    }

    public LoginFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login2, container, false);
        regText = rootView.findViewById(R.id.txtV_regText);
        user_name = rootView.findViewById(R.id.user_name_login);
        user_pass = rootView.findViewById(R.id.user_pass_login);
        btn_login = rootView.findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });

        regText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginFormActivityListener.performRegister();
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        loginFormActivityListener = (OnLoginFormActivityListener) activity;
    }

    private void performLogin(){
        String user_name_login = user_name.getText().toString();
        String user_pass_login = user_pass.getText().toString();

        Call<User> call = MainActivity2.apiInterface.performLogin(user_name_login, user_pass_login);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().getResponse().equals("ok")){
                    MainActivity2.prefConfig.writeLoginStatus(true);
                    loginFormActivityListener.performLogin(response.body().getName());
                }else if (response.body().getResponse().equals("failed")){
                    MainActivity2.prefConfig.displayToast("Login Failed...");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        user_name.setText("");
        user_pass.setText("");
    }
}
