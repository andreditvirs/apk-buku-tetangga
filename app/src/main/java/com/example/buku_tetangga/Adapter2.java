package com.example.buku_tetangga;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter2 extends RecyclerView.Adapter<Adapter2.MyViewHolder> {

    private List<Contact2> contact2s;
    private Context context;

    public Adapter2(List<Contact2> contact2s, Context context) {
        this.contact2s = contact2s;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item2, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.name.setText(contact2s.get(position).getName());
        holder.email.setText(contact2s.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return contact2s.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,email;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
        }
    }
}
