package com.example.buku_tetangga.adapters.keranjang;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buku_tetangga.R;
import com.example.buku_tetangga.model.book_butang_activity.Buku;
import com.example.buku_tetangga.model.keranjang.Penyedia;

import java.util.List;

public class RcvPenyediaAdapter extends RecyclerView.Adapter<RcvPenyediaAdapter.ItemViewHolder> {

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private List<Penyedia> itemList;

    public RcvPenyediaAdapter(List<Penyedia> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_keranjang_rcv_penyedia, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        Penyedia item = itemList.get(i);
        itemViewHolder.tvItemTitle.setText(item.getItemTitle());

        // Create layout manager with initial prefetch item count
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                itemViewHolder.rvSubItem.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        layoutManager.setInitialPrefetchItemCount(item.getSubItemList().size());

        // Create sub item view adapter
        RcvBukuInPenyediaAdapter subItemAdapter = new RcvBukuInPenyediaAdapter(item.getSubItemList());

        itemViewHolder.rvSubItem.setLayoutManager(layoutManager);
        itemViewHolder.rvSubItem.setAdapter(subItemAdapter);
        itemViewHolder.rvSubItem.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView tvItemTitle;
        private RecyclerView rvSubItem;

        ItemViewHolder(View itemView) {
            super(itemView);
            tvItemTitle = itemView.findViewById(R.id.tv_item_title);
            rvSubItem = itemView.findViewById(R.id.rcv_keranjang_buku_in_penyedia);
        }
    }
}
