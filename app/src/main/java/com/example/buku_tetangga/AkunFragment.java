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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;


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

    //butang rak buku
    private ApiInterface2 apiInterfaceRakBuku;

    public interface OnLogoutListener{
        public void logoutPerformed();
    }

    //butang rak buku
//    class Spacecraft {
//        /*
//        INSTANCE FIELDS
//         */
//        @SerializedName("id")
//        private int id;
//        @SerializedName("name")
//        private String name;
//        @SerializedName("propellant")
//        private String propellant;
//        @SerializedName("imageurl")
//        private String imageURL;
//        @SerializedName("technologyexists")
//        private int technologyExists;
//
//        public Spacecraft(int id, String name, String propellant, String imageURL, int technologyExists) {
//            this.id = id;
//            this.name = name;
//            this.propellant = propellant;
//            this.imageURL = imageURL;
//            this.technologyExists = technologyExists;
//        }
//
//        /*
//         *GETTERS AND SETTERS
//         */
//        public int getId() {
//            return id;
//        }
//        public void setId(int id) {
//            this.id = id;
//        }
//        public String getName() {
//            return name;
//        }
//        public void setName(String name) {
//            this.name = name;
//        }
//        public String getPropellant() {
//            return propellant;
//        }
//
//        public String getImageURL() {
//            return imageURL;
//        }
//
//        public int getTechnologyExists() {
//            return technologyExists;
//        }
//
//        /*
//        TOSTRING
//         */
//        @Override
//        public String toString() {
//            return name;
//        }
//    }

//    interface MyAPIService {
//
//        @GET("/Oclemy/SampleJSON/338d9585/spacecrafts.json")
//        Call<List<ModalRakBuku>> getSpacecrafts();
//    }

//    static class RetrofitClientInstance {
//
//        private static Retrofit retrofit;
//        private static final String BASE_URL = "https://raw.githubusercontent.com/";
//
//        public static Retrofit getRetrofitInstance() {
//            if (retrofit == null) {
//                retrofit = new Retrofit.Builder()
//                        .baseUrl(BASE_URL)
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .build();
//            }
//            return retrofit;
//        }
//    }

    class GridViewAdapter extends BaseAdapter {

        private List<ModalRakBuku> spacecrafts;
        private Context context;

        public GridViewAdapter(Context context,List<ModalRakBuku> spacecrafts){
            this.context = context;
            this.spacecrafts = spacecrafts;
        }

        @Override
        public int getCount() {
            return spacecrafts.size();
        }

        @Override
        public Object getItem(int pos) {
            return spacecrafts.get(pos);
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

            TextView nameTxt = view.findViewById(R.id.nameTextView);
            TextView txtPropellant = view.findViewById(R.id.propellantTextView);
//            CheckBox chkTechExists = view.findViewById(R.id.myCheckBox);
            ImageView spacecraftImageView = view.findViewById(R.id.spacecraftImageView);
            TextView stock = view.findViewById(R.id.stockValue);

            final ModalRakBuku thisSpacecraft= spacecrafts.get(position);

            nameTxt.setText(thisSpacecraft.getName());
            txtPropellant.setText(thisSpacecraft.getPropellant());
            stock.setText(thisSpacecraft.setStock());
//            chkTechExists.setChecked( thisSpacecraft.getTechnologyExists()==1);
//            chkTechExists.setEnabled(false);





            if(thisSpacecraft.getImageURL() != null && thisSpacecraft.getImageURL().length()>0)
            {
                Picasso.get().load(thisSpacecraft.getImageURL()).placeholder(R.drawable.ic_person_primary_24dp).into(spacecraftImageView);
            }else {
                Toast.makeText(context, "Empty Image URL", Toast.LENGTH_LONG).show();
                Picasso.get().load(R.drawable.ic_person_primary_24dp).into(spacecraftImageView);
            }

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, thisSpacecraft.getName(), Toast.LENGTH_SHORT).show();
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
                }
            });
        }

        //butang rak buku
        final ProgressBar myProgressBar= rootView.findViewById(R.id.myProgressBar);
        myProgressBar.setIndeterminate(true);
        myProgressBar.setVisibility(View.VISIBLE);

        apiInterfaceRakBuku = ApiClient2.getApiClient().create(ApiInterface2.class);

        Call<List<ModalRakBuku>> call = apiInterfaceRakBuku.getSpacecrafts();
        call.enqueue(new Callback<List<ModalRakBuku>>() {

            @Override
            public void onResponse(Call<List<ModalRakBuku>> call, Response<List<ModalRakBuku>> response) {
                myProgressBar.setVisibility(View.GONE);
                mGridView = (ExpandableHeightGridView) rootView.findViewById(R.id.mGridView);
                adapter = new AkunFragment.GridViewAdapter(getActivity(),response.body());
                mGridView.setNumColumns(2);
                mGridView.setAdapter(adapter);
                mGridView.setExpanded(true);
            }
            @Override
            public void onFailure(Call<List<ModalRakBuku>> call, Throwable throwable) {
                myProgressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), throwable.getMessage(), Toast.LENGTH_LONG).show();
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
