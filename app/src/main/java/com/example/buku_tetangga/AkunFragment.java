package com.example.buku_tetangga;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.buku_tetangga.model.BookButangActivity.RakBuku;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AkunFragment extends Fragment {

    TextView nama, email, username;
    ImageView imageView;
    Button signout, btn_toAddBook;
    GoogleSignInClient mGoogleSignInClient;

    //butang logout
    private ImageButton btn_logout;
    OnLogoutListener logoutListener;

    //butang rak buku
    private ApiInterface apiInterface;

    public interface OnLogoutListener{
        public void logoutPerformed();
    }

    //butang rak buku
    class GridViewAdapter extends BaseAdapter {

        private List<RakBuku> listRakBuku;
        private Context context;

        public GridViewAdapter(Context context,List<RakBuku> listRakBuku){
            this.context = context;
            this.listRakBuku = listRakBuku;
        }

        @Override
        public int getCount() {
            return listRakBuku.size();
        }

        @Override
        public Object getItem(int pos) {
            return listRakBuku.get(pos);
        }

        @Override
        public long getItemId(int pos) {
            return pos;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            if(view==null)
            {
                view=LayoutInflater.from(context).inflate(R.layout.model_grid_rak_buku,viewGroup,false);
            }

            TextView judul_buku = view.findViewById(R.id.judul_buku);
            TextView penerbitBuku = view.findViewById(R.id.penerbitBuku);
            ImageView fotoBuku = view.findViewById(R.id.foto_buku);
            TextView stock = view.findViewById(R.id.jumlah_stock);

            final RakBuku rakBuku= listRakBuku.get(position);

//            judul_buku.setText(rakBuku.getJudul_buku());
//            penerbitBuku.setText(rakBuku.getPengarang());

            if(rakBuku.getFoto() != null && rakBuku.getFoto().length()>0)
            {
                Picasso.get().load(rakBuku.getFoto()).placeholder(R.drawable.cover_buku_1).into(fotoBuku);
            }else {
//                Toast.makeText(context, "Empty Image URL", Toast.LENGTH_LONG).show();
//                Picasso.get().load(R.drawable.ic_person_primary_24dp).into(fotoBuku);
            }

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(context, rakBuku.getJudul_buku(), Toast.LENGTH_SHORT).show();
                }
            });

            return view;
        }
    }

    private AkunFragment.GridViewAdapter adapter;
    private ExpandableHeightGridView mGridView;
    ProgressBar myProgressBar;

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

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String nama = prefs.getString("nama", "loading...");
        String username = prefs.getString("username", "loading...");

        this.nama.setText(nama);
        this.username.setText(username);

        //butang rak buku
        final ProgressBar myProgressBar= rootView.findViewById(R.id.myProgressBar);
        myProgressBar.setIndeterminate(true);
        myProgressBar.setVisibility(View.VISIBLE);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
//        Call<List<RakBuku>> call = apiInterface.getRakBuku(username);
//        call.enqueue(new Callback<List<RakBuku>>() {
//            @Override
//            public void onResponse(Call<List<RakBuku>> call, Response<List<RakBuku>> response) {
//                myProgressBar.setVisibility(View.GONE);
//                mGridView = (ExpandableHeightGridView) rootView.findViewById(R.id.mGridView);
//                adapter = new AkunFragment.GridViewAdapter(getActivity(),response.body());
//                mGridView.setNumColumns(2);
//                mGridView.setAdapter(adapter);
//                mGridView.setExpanded(true);
//            }
//            @Override
//            public void onFailure(Call<List<RakBuku>> call, Throwable throwable) {
//                System.out.println("RESPONSE===="+ throwable.getMessage());
//                myProgressBar.setVisibility(View.GONE);
//                Toast.makeText(getActivity(), throwable.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this.getActivity());
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            this.nama.setText(personName);
            this.username.setText(personId);
            email.setText(personEmail);
            Glide.with(this).load(personPhoto).into(imageView);
        }else {

            //butang logout
            btn_logout = (ImageButton) rootView.findViewById(R.id.btn_logout_main);
            String nama_user = VerifyActivity.prefConfig.readName();
            this.nama.setText(nama_user);

            btn_logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    logOut();
                }
            });
        }

        btn_toAddBook = rootView.findViewById(R.id.btn_toAddBook);
        btn_toAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekStatusUser(username);
            }
        });
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
    }

    private AlertDialog.Builder builder;

    public void tampilDialog() {

        builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle("Konfirmasi");
        builder.setMessage("Apakah Anda Telah Log Out!");
        builder.create().show();
    }

    private void cekStatusUser(String username){
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<StatusUser> call = apiInterface.cekStatusUser(username);
        call.enqueue(new Callback<StatusUser>() {
            @Override
            public void onResponse(Call<StatusUser> call, Response<StatusUser> response) {
                if (!response.body().getError()){
                    startActivity(new Intent(getActivity(), AddBookActivity.class));
                }
            }
            @Override
            public void onFailure(Call<StatusUser> call, Throwable throwable) {
                Toast.makeText(getActivity(), throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}

