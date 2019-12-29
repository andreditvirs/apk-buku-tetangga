package com.example.buku_tetangga;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Contact2> contact2s;
    private Adapter2 adapter2;
    private ApiInterface2 apiInterface2;
    ProgressBar progressBar;
    TextView search;
    String[] item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        progressBar = findViewById(R.id.prograss);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
//        fetchContact("users", "");

    }

    public void fetchContact(String type, String key){

        apiInterface2 = ApiClient2.getApiClient().create(ApiInterface2.class);

        Call<List<Contact2>> call = apiInterface2.getContact(type, key);
        call.enqueue(new Callback<List<Contact2>>() {
            @Override
            public void onResponse(Call<List<Contact2>> call, Response<List<Contact2>> response) {
                progressBar.setVisibility(View.GONE);
                contact2s = response.body();
                adapter2 = new Adapter2(contact2s, MainActivity2.this);
                recyclerView.setAdapter(adapter2);
                adapter2.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Contact2>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity2.this, "Error\n"+t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetchContact("users", query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fetchContact("users", newText);
                return false;
            }
        });
        return true;
    }

}
