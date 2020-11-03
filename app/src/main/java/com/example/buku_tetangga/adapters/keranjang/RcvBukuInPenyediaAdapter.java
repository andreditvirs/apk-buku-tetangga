package com.example.buku_tetangga.adapters.keranjang;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buku_tetangga.R;
import com.example.buku_tetangga.model.keranjang.BukuInPenyedia;

import java.util.List;

public class RcvBukuInPenyediaAdapter extends RecyclerView.Adapter<RcvBukuInPenyediaAdapter.SubItemViewHolder> {

    private List<BukuInPenyedia> subItemList;

    RcvBukuInPenyediaAdapter(List<BukuInPenyedia> subItemList) {
        this.subItemList = subItemList;
    }

    @NonNull
    @Override
    public SubItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_keranjang_rcv_buku_in_penyedia, viewGroup, false);
        return new SubItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubItemViewHolder subItemViewHolder, int i) {
        BukuInPenyedia subItem = subItemList.get(i);
        subItemViewHolder.tvSubItemTitle.setText(subItem.getSubItemTitle());
    }

    @Override
    public int getItemCount() {
        return subItemList.size();
    }

    class SubItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvSubItemTitle;

        SubItemViewHolder(View itemView) {
            super(itemView);
            tvSubItemTitle = itemView.findViewById(R.id.tv_sub_item_title);
        }
    }
}