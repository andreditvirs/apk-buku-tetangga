package com.example.buku_tetangga;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.String.valueOf;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Register.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Register#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Register extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextInputEditText name, user_name, user_password, reuser_password, user_mail, user_notelp;

    private Button btn_register;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Register() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Register.
     */
    // TODO: Rename and change types and number of parameters
    public static Register newInstance(String param1, String param2) {
        Register fragment = new Register();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_register, container, false);
        name = rootView.findViewById(R.id.txtIET_name_reg);
        user_name = rootView.findViewById(R.id.txtIET_user_name_reg);
        user_password = rootView.findViewById(R.id.txtIET_user_pass_reg);
        reuser_password = rootView.findViewById(R.id.txtIET_reuser_pass_reg);
        user_mail = rootView.findViewById(R.id.txtIET_email);
        user_notelp = rootView.findViewById(R.id.txtIET_notelp);
        btn_register = rootView.findViewById(R.id.btn_register_main);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkDataEntered(name, user_name, user_password)){
                    int max = 999;
                    int min = 101;
                    String rand = String.valueOf((int)(Math.random() * (max - min + 1) + min));
                    String id = user_name.getText().toString() + rand;

                    sendVoiceCallOTP(id);

                    String nama = name.getText().toString();
                    String username = user_name.getText().toString();
                    String userpass = user_password.getText().toString();
                    String email = user_mail.getText().toString();
                    String notelp = user_notelp.getText().toString();

                    Intent i = new Intent(getActivity(), RegisterUserOtpActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("username", username);
                    bundle.putString("userpass", userpass);
                    bundle.putString("notelp", notelp);
                    bundle.putString("email", email);
                    bundle.putString("nama", nama);
                    bundle.putString("id", id);
                    i.putExtras(bundle);
                    getActivity().finish();
                    startActivity(i);

                    name.setText("");
                    user_password.setText("");
                    user_name.setText("");
                }else{
                    VerifyActivity.prefConfig.displayToast("Silahkan mengisikan formulir secara lengkap");
                }
            }
        });
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private boolean checkDataEntered(TextInputEditText name, TextInputEditText user_name, TextInputEditText user_password /*TextInputEditText email*/) {
        if(isEmpty(name, user_name, user_password)){
            VerifyActivity.prefConfig.displayToast("Silahkan mengisikan formulir secara lengkap");
            return false;
        }else if (isEmpty(name)) {
            name.setError("Silahkan memasukkan nama");
            return false;
        }else if(isEmpty(user_name)){
            user_name.setError("Silahkan memasukkan username");
            return false;
        }else if(isEmpty(user_password)){
            user_password.setError("Silahkan memasukkan username");
            return false;
        }
//        else if(!isEmail(email)){
//            email.setError("Silahkan memasukkan email yang benar");
//        }
        return true;
    }

    boolean isEmpty(TextInputEditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }



    boolean isEmpty(TextInputEditText text1, TextInputEditText text2, TextInputEditText text3){
        if(isEmpty(text1) && isEmpty(text2) && isEmpty(text3)){
            return true;
        }
        return false;
    }

//    boolean isEmail(TextInputEditText text) {
//        CharSequence email = text.getText().toString();
//        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
//    }

    private void sendVoiceCallOTP(String id){
        String accept = "application/json";
        String xapikey = "2tyFWgY7nA9KfKrl1brGPOhkzECC4HwF";
        String contentType = "application/json";

        String notelp = user_notelp.getText().toString();
        VoicecallOtp voicecallOtp = new VoicecallOtp(notelp, 4);
        ApiVoicecallOtp apiVoicecallOtp = ApiClientVoicecallOtp.getRetrofitInstance().create(ApiVoicecallOtp.class);
        Call<VoicecallOtp> call = apiVoicecallOtp.sendOTP(accept, xapikey, contentType, voicecallOtp, id);
        call.enqueue(new Callback<VoicecallOtp>() {
            @Override
            public void onResponse(Call<VoicecallOtp> call, Response<VoicecallOtp> response) {
                System.out.println("================"+response);
            }

            @Override
            public void onFailure(Call<VoicecallOtp> call, Throwable t) {
                System.out.println("++++++++++++"+t.getLocalizedMessage());
            }
        });
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
