package com.example.buku_tetangga;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/*
 * Copyright (C) 2018 Levi Rizki Saputra (levirs565@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Created by LEVI on 22/09/2018.
 */
public class BantuanAdapter extends ExpandableRecyclerViewAdapter<PaketBantuanViewHolder, BantuanViewHolder> {
    public BantuanAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public PaketBantuanViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.expandable_recyclerview_paket_bantuan, parent, false);
        return new PaketBantuanViewHolder(v);
    }

    @Override
    public BantuanViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.expandable_recyclerview_bantuan, parent, false);
        return new BantuanViewHolder(v);
    }

    @Override
    public void onBindChildViewHolder(BantuanViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final Bantuan bantuan = (Bantuan) group.getItems().get(childIndex);
        holder.bind(bantuan);
    }

    @Override
    public void onBindGroupViewHolder(PaketBantuanViewHolder holder, int flatPosition, ExpandableGroup group) {
        final PaketBantuan paketBantuan = (PaketBantuan) group;
        holder.bind(paketBantuan);
    }
}

