package com.example.buku_tetangga;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.example.buku_tetangga.VerifyActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class AkunFragment extends Fragment {

    TextView nama, email, username;
    ImageView imageView;
    Button signout;
    GoogleSignInClient mGoogleSignInClient;

    //butang logout
    private ImageButton btn_logout;
    OnLogoutListener logoutListener;

    public interface OnLogoutListener{
        public void logoutPerformed();
    }


    public AkunFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_akun, container, false);

        //set google sign
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this.getActivity(), gso);

        nama = rootView.findViewById(R.id.txtV_nama);
        username = rootView.findViewById(R.id.txtV_username);
        email = rootView.findViewById(R.id.txtV_email);
        imageView = (ImageView) rootView.findViewById(R.id.imgv_profil);
        signout = (Button) rootView.findViewById(R.id.btn_signout);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    // ...
                    case R.id.btn_signout:
                        signOut();
                        break;
                    // ...
                }

            }
        });
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this.getActivity());
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            nama.setText(personName);
            username.setText(personId);
            email.setText(personEmail);
            Glide.with(this).load(personPhoto).into(imageView);
        }else {

            //butang logout
            btn_logout = (ImageButton) rootView.findViewById(R.id.btn_logout_main);
            String nama_user = VerifyActivity.prefConfig.readName();
            nama.setText(nama_user);

            btn_logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    logOut();
//                logoutListener.logoutPerformed();
                }
            });
        }
        return rootView;
    }

    private void signOut() {
        mGoogleSignInClient.signOut();
        tampilDialog();
        getActivity().finish();
        startActivity(new Intent(getActivity(), VerifyActivity.class));
    }

    private void logOut(){
        VerifyActivity verifyActivity = new VerifyActivity();
        verifyActivity.logoutPerformed();
        getActivity().finish();
        startActivity(new Intent(getActivity(), VerifyActivity.class));
    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
//        Activity activity = (Activity) context;
//        logoutListener = (OnLogoutListener) activity;
    }

    private AlertDialog.Builder builder;

    public void tampilDialog() {

        builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle("Konfirmasi");
        builder.setMessage("Apakah Anda Telah Log Out!");
        builder.create().show();

    }

}
