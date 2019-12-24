package com.example.buku_tetangga;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment2 extends Fragment {

    private EditText name, user_name, user_password;
    private Button btn_register;

    public RegistrationFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_registration_fragment2, container, false);
        name = rootView.findViewById(R.id.txt_name);
        user_name = rootView.findViewById(R.id.txt_user_name);
        user_password = rootView.findViewById(R.id.txt_password);
        btn_register = rootView.findViewById(R.id.btn_register2);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performRegistration();
            }
        });
        return rootView;
    }

    public void performRegistration(){
        String name_register = name.getText().toString();
        String user_name_register = user_name.getText().toString();
        String user_password_register = user_password.getText().toString();
        Call<User> call = MainActivity2.apiInterface.performRegistration(name_register, user_name_register, user_password_register);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body().getResponse().equals("ok")){
                    MainActivity2.prefConfig.displayToast("Registration Succes...");
                }else if(response.body().getResponse().equals("exist")){
                    MainActivity2.prefConfig.displayToast("User Already Exist...");
                }else if(response.body().getResponse().equals("error")){
                    MainActivity2.prefConfig.displayToast("Something went wrong...");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        name.setText("");
        user_password.setText("");
        user_name.setText("");
    }

}
