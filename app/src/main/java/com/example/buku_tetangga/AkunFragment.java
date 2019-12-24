package com.example.buku_tetangga;


import android.app.Activity;
import android.app.AlertDialog;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class AkunFragment extends Fragment {

    TextView nama, email;
    ImageView imageView;
    Button signout;
    GoogleSignInClient mGoogleSignInClient;


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
        email = rootView.findViewById(R.id.txtV_email);
        imageView = rootView.findViewById(R.id.imgv_profil);
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
            email.setText(personEmail);
            Glide.with(this).load(personPhoto).into(imageView);
        }

        return rootView;
    }

    private void signOut() {
        mGoogleSignInClient.signOut();
        tampilDialog();
        getActivity().finish();
        startActivity(new Intent(getActivity(), VerifyActivity.class));
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
