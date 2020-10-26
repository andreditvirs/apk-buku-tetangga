package com.example.buku_tetangga.adapters.keranjang;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buku_tetangga.R;
import com.example.buku_tetangga.model.Buku;
import com.example.buku_tetangga.model.DetailBuku;
import com.example.buku_tetangga.model.Penyedia;
import com.example.buku_tetangga.model.RakBuku;

import java.util.List;

public class RcvBukuAdapter extends RecyclerView.Adapter<RcvBukuAdapter.ItemViewHolder> {

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    List<DetailBuku> detailBukus;

    public RcvBukuAdapter(List<DetailBuku> detailBukus) {
        this.detailBukus = detailBukus;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_keranjang_rcv_buku, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.tvItemTitle.setText(detailBukus.get(i).getBuku().getJudul());
    }

    @Override
    public int getItemCount() {
        return detailBukus.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView tvItemTitle;

        ItemViewHolder(View itemView) {
            super(itemView);
            tvItemTitle = itemView.findViewById(R.id.tv_item_title);
        }
    }
}
